package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.link.LinkStatus;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.molecule.Molecule;
import be.howest.ti.mars.logic.domain.molecule.MoleculesSummary;
import be.howest.ti.mars.logic.domain.notifications.ShipNotification;
import be.howest.ti.mars.logic.domain.notifications.SystemNotification;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.PricePlan;
import be.howest.ti.mars.logic.domain.users.User;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConvertorSQL {

    protected User sqlToUser(ResultSet rs) throws SQLException{
        return new User(rs.getString("uid"), rs.getString("firstname"), rs.getString("lastname"), PricePlan.valueOf(rs.getString("pricePlan")));
    }

    protected Item sqlToItem(ResultSet rs) throws SQLException{
        if (rs.getString("mapSummary") == null){
            return new Item(rs.getString("uid"), rs.getString("name"));
        }
        return new Item(rs.getString("uid"), rs.getString("name"), getMoleculesSummary(rs), rs.getTimestamp("sendTime").toLocalDateTime());
    }

    protected Transporter sqlToTransporter(ResultSet rs, Building building) throws SQLException{
        return new Transporter(rs.getString("uid"), rs.getString("name"), new Size(rs.getDouble("height"), rs.getDouble("length"), rs.getDouble("width")), building, rs.getString("ip"));
    }

    protected Link sqlToLink(ResultSet rs, Transporter senderT, Transporter receiverT, User senderU, User receiverU, Item item) throws SQLException{
        return new Link(rs.getString("uid"), senderU, receiverU, senderT, receiverT, LinkStatus.valueOf(rs.getString("linkStatus")), item);
    }

    protected ShipNotification sqlToShipNotification(ResultSet rs, User receiver) throws SQLException {
        return new ShipNotification(rs.getString("title"), rs.getTimestamp("expireTime").toLocalDateTime(), rs.getString("message"), rs.getTimestamp("createDate").toLocalDateTime(), receiver);
    }

    protected SystemNotification sqlToSystemNotification(ResultSet rs) throws SQLException{
        return new SystemNotification(rs.getString("title"), rs.getTimestamp("expireTime").toLocalDateTime(), rs.getString("message"), rs.getTimestamp("createDate").toLocalDateTime());
    }

    private MoleculesSummary getMoleculesSummary(ResultSet rs) throws SQLException{
        return new MoleculesSummary(getSummary(rs), getMolecules(rs), new Size(rs.getDouble("height"), rs.getDouble("length"), rs.getDouble("width")));
    }

    private Map<String, Integer> getSummary(ResultSet rs) throws SQLException {
        JsonObject jsonObject = new JsonObject(rs.getString("mapSummary")).getJsonObject("summary");
        Map<String , Integer> result = new HashMap<>();

        for (Map.Entry<String, Object> entry : jsonObject.getMap().entrySet()){
            result.put(entry.getKey(), (Integer) entry.getValue());
        }

        return result;
    }

    private Set<Molecule> getMolecules(ResultSet rs) throws SQLException{
        JsonArray jsonArray = new JsonArray(rs.getString("molecules"));
        Set<Molecule> molecules = new HashSet<>();

        for (int i = 0; i < jsonArray.size(); i++){
            JsonObject jsonObject = jsonArray.getJsonObject(i);
            Molecule molecule = new Molecule(jsonObject.getInteger("id"), jsonObject.getString("type"), jsonObject.getInteger("xPosition"), jsonObject.getInteger("yPosition"));
            molecules.add(molecule);
        }

        return molecules;
    }

}