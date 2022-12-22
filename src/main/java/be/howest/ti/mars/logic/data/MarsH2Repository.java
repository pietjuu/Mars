package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.notifications.ShipNotification;
import be.howest.ti.mars.logic.domain.notifications.SystemNotification;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.exceptions.RepositoryException;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
This is only a starter class to use an H2 database.
In this start project there was no need for a Java interface MarsRepository.
Please always use interfaces when needed.

To make this class useful, please complete it with the topics seen in the module OOA & SD
 */

public class MarsH2Repository implements MarsRepositories{
    private static final Logger LOGGER = Logger.getLogger(MarsH2Repository.class.getName());
    private final Server dbWebConsole;
    private final String username;
    private final String password;
    private final String url;
    private final ConvertorSQL convertorSQL;

    public MarsH2Repository(String url, String username, String password, int console) {
        try {
            this.convertorSQL = new ConvertorSQL();
            this.username = username;
            this.password = password;
            this.url = url;
            this.dbWebConsole = Server.createWebServer(
                    "-ifNotExists",
                    "-webPort", String.valueOf(console)).start();
            LOGGER.log(Level.INFO, "Database web console started on port: {0}", console);
            this.generateData();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "DB configuration failed", ex);
            throw new RepositoryException("Could not configure MarsH2repository");
        }
    }

    public void cleanUp() {
        if (dbWebConsole != null && dbWebConsole.isRunning(false))
            dbWebConsole.stop();

        try {
            Files.deleteIfExists(Path.of("./db-05.mv.db"));
            Files.deleteIfExists(Path.of("./db-05.trace.db"));
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Database cleanup failed.", e);
            throw new RepositoryException("Database cleanup failed.");
        }
    }

    public void generateData() {
        try {
            executeScript("db-create.sql");
            executeScript("db-populate.sql");
        } catch (IOException | SQLException ex) {
            LOGGER.log(Level.SEVERE, "Execution of database scripts failed.", ex);
        }
    }

    private void executeScript(String fileName) throws IOException, SQLException {
        String createDbSql = readFile(fileName);
        try (
                Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement(createDbSql)
        ) {
            stmt.executeUpdate();
        }
    }

    private String readFile(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inputStream == null)
            throw new RepositoryException("Could not read file: " + fileName);

        return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO MarsUsers (uid, firstname, lastname, pricePlan) VALUES (?,?,?,?)")){

            preparedStatement.setString(1, user.getId());
            preparedStatement.setString(2, user.getFirstname());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setString(4, user.getPricePlan().toString());

            preparedStatement.execute();
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - addUser()");
            throw new RepositoryException("There went something wrong with the DB! - addUser()");
        }
    }

    @Override
    public Set<User> getUsers() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM MarsUsers")){
            Set<User> users = new HashSet<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                users.add(convertorSQL.sqlToUser(resultSet));
            }

            return users;
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - getUsers()");
            throw new RepositoryException("There went something wrong with the DB! - getUsers()");
        }
    }

    @Override
    public User getUser(String userID) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM MarsUsers WHERE uid=?")){
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertorSQL.sqlToUser(resultSet);

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - getUsers()");
            throw new RepositoryException("There went something wrong with the DB! - getUsers()");
        }
    }

    @Override
    public void deleteUser(String userID) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM MarsUsers WHERE uid = ?")){
            preparedStatement.setString(1, userID);

            preparedStatement.execute();
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - deleteUsers()");
            throw new RepositoryException("There went something wrong with the DB! - deleteUsers()");
        }
    }

    @Override
    public Blacklist getShippertBlacklist() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Blacklist where userID='-1'")){
            Blacklist blacklist = new Blacklist();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                blacklist.addItem(this.getItem(resultSet.getString("itemID")));
            }

            return blacklist;
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - getShippertBlacklist()");
            throw new RepositoryException("There went something wrong with the DB! - getShippertBlacklist()");
        }
    }

    @Override
    public UserBlacklist getUserBlacklist(String userID) {
        return null;
    }

    @Override
    public void createUserBlacklist(String userID) {

    }

    @Override
    public void addItemToUserBlacklist(Item item, String userID) {

    }

    @Override
    public void removeItemToUserBlacklist(Item item, String userID) {

    }

    @Override
    public boolean isUserBlackListExist(String userID) {
        return false;
    }

    @Override
    public Set<Transporter> getTransporters() {
        return null;
    }

    @Override
    public void addTransporter(Transporter transporter) {

    }

    @Override
    public Transporter getTransporter(String transporterID) {
        return null;
    }

    @Override
    public void updateTransporter(Transporter transporter) {

    }

    @Override
    public void deleteTransporter(Transporter transporter) {

    }

    @Override
    public void addBuilding(Building building) {

    }

    @Override
    public void removeBuilding(Building building) {

    }

    @Override
    public Building getBuilding(String buildingID) {
        return null;
    }

    @Override
    public Building getBuildingFromCoordinates(Coordinates coordinates) {
        return null;
    }

    @Override
    public Set<Link> getAllLinks() {
        return null;
    }

    @Override
    public Link getLink(String linkID) {
        return null;
    }

    @Override
    public void addLink(Link link) {

    }

    @Override
    public void deleteLink(Link link) {

    }

    @Override
    public List<ShipNotification> getShipNotifications() {
        return null;
    }

    @Override
    public List<SystemNotification> getSystemNotifications() {
        return null;
    }

    @Override
    public void addShipNotification(ShipNotification notification) {

    }

    @Override
    public void addSystemNotification(SystemNotification notification) {

    }

    protected void addItem(Item item){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES (?,?,?,?,?,?,?,?)")){

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            preparedStatement.setString(3, Json.encode(item.getMolecules()));
            preparedStatement.setString(4, Json.encode(item.getMolecules().getMolecules()));
            preparedStatement.setDouble(5, item.getSize().getHeight());
            preparedStatement.setDouble(6, item.getSize().getLength());
            preparedStatement.setDouble(7, item.getSize().getWidth());
            preparedStatement.setTimestamp(8,  java.sql.Timestamp.valueOf(item.getSendTime()));

            preparedStatement.execute();
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - addUser()");
            throw new RepositoryException("There went something wrong with the DB! - addUser()");
        }
    }

    protected Item getItem(String itemID){
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Items WHERE uid=?")){
            preparedStatement.setString(1, itemID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertorSQL.sqlToItem(resultSet);

        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error - getUsers()");
            throw new RepositoryException("There went something wrong with the DB! - getItem()");
        }
    }
}
