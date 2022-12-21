package be.howest.ti.mars.logic.controller;

import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.molecule.Molecule;
import be.howest.ti.mars.logic.domain.molecule.MoleculesSummary;
import be.howest.ti.mars.logic.domain.transporter.Size;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.exceptions.TransporterException;
import be.howest.ti.mars.web.exceptions.TransporterAPIException;
import be.howest.ti.mars.web.external.TransporterAPI;
import io.vertx.core.json.JsonObject;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Transport Controller
 * Used for communication between web (transporters api) and logic
 */
public class TransporterController {

    public Coordinates getTransporterLocation(Transporter transporter){
        TransporterAPI transporterAPI = new TransporterAPI(transporter);
        JsonObject jsonObject = transporterAPI.getLocation();

        return new Coordinates(jsonObject.getFloat("latitude"), jsonObject.getFloat("longitude"));
    }

    public boolean getTransporterStatus(Transporter transporter){
        TransporterAPI transporterAPI = new TransporterAPI(transporter);
        JsonObject jsonObject = transporterAPI.getStatus();

        return jsonObject.getBoolean("ready");
    }

    public MoleculesSummary getTransporterScan(Transporter transporter){
        try{
            TransporterAPI transporterAPI = new TransporterAPI(transporter);
            JsonObject jsonObject = transporterAPI.getScan();

            return new MoleculesSummary(getSummaryMolecules(jsonObject), getAllMolecules(jsonObject), getSize(jsonObject));
        } catch (TransporterAPIException e){
            if (e.getStatusCode() == 404){
                throw new TransporterAPIException(404, "Transporter unavailable!");
            }
            throw new TransporterException(e.getMessage());
        }

    }

    public MoleculesSummary sendItemInTransporter(Transporter transporter){
        try {
            TransporterAPI transporterAPI = new TransporterAPI(transporter);
            JsonObject jsonObject = transporterAPI.addSend();

            return new MoleculesSummary(getSummaryMolecules(jsonObject), getAllMolecules(jsonObject), getSize(jsonObject));
        } catch (TransporterAPIException e){
            if (e.getStatusCode() == 404){
                throw new TransporterAPIException(404, "Transporter unavailable!");
            }
            throw new TransporterException(e.getMessage());
        }

    }

    private Set<Molecule> getAllMolecules(JsonObject jsonObject){
        Set<Molecule> set = new HashSet<>();

        for (int i = 0; i < jsonObject.getJsonArray("molecules").size(); i++){
            JsonObject moleculeJSON = jsonObject.getJsonArray("molecules").getJsonObject(i);
            JsonObject moleculeJSONPosition = moleculeJSON.getJsonObject("position");
            Molecule molecule = new Molecule(moleculeJSON.getInteger("id"), moleculeJSON.getString("molecule"), moleculeJSONPosition.getInteger("x"), moleculeJSONPosition.getInteger("y"));
            set.add(molecule);
        }

        return set;
    }

    private Map<String , Integer> getSummaryMolecules(JsonObject jsonObject){
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < jsonObject.getJsonArray("summary").size(); i++){
            JsonObject moleculeJSON = jsonObject.getJsonArray("summary").getJsonObject(i);
            map.put(moleculeJSON.getString("molecule"), moleculeJSON.getInteger("count"));
        }

        return map;
    }

    private Size getSize(JsonObject jsonObject){
        JsonObject object = jsonObject.getJsonObject("size");
        return new Size(object.getDouble("depth"), object.getDouble("length"), object.getDouble("width"));
    }

}
