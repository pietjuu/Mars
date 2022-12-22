package be.howest.ti.mars.logic.data;

import be.howest.ti.mars.logic.domain.blacklist.Blacklist;
import be.howest.ti.mars.logic.domain.blacklist.UserBlacklist;
import be.howest.ti.mars.logic.domain.items.Item;
import be.howest.ti.mars.logic.domain.link.Link;
import be.howest.ti.mars.logic.domain.location.Building;
import be.howest.ti.mars.logic.domain.location.Coordinates;
import be.howest.ti.mars.logic.domain.location.TypeOfLocation;
import be.howest.ti.mars.logic.domain.notifications.ShipNotification;
import be.howest.ti.mars.logic.domain.notifications.SystemNotification;
import be.howest.ti.mars.logic.domain.transporter.Transporter;
import be.howest.ti.mars.logic.domain.users.User;
import be.howest.ti.mars.logic.exceptions.RepositoryException;
import io.vertx.core.json.Json;
import org.h2.tools.Server;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
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
            System.out.println(e);
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
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Blacklist where userID=?")){
            UserBlacklist userBlacklist = new UserBlacklist(userID);
            preparedStatement.setString(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                userBlacklist.addItem(this.getItem(resultSet.getString("itemID")));
            }

            return userBlacklist;
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getUserBlacklist()");
            throw new RepositoryException("There went something wrong with the DB! - getUserBlacklist()");
        }
    }

    @Override
    public void createUserBlacklist(String userID) {
        LOGGER.log(Level.FINEST, "creating userBlacklist.");
        LOGGER.log(Level.FINEST, userID);
    }

    @Override
    public void addItemToUserBlacklist(Item item, String userID) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Blacklist (itemID, userID) VALUES (?,?)")){

            this.addItem(item);

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, userID);

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addItemToUserBlacklist()");
            throw new RepositoryException("There went something wrong with the DB! - addItemToUserBlacklist()");
        }
    }

    @Override
    public void removeItemToUserBlacklist(Item item, String userID) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Blacklist WHERE userID = ? AND itemID = ?")){
            preparedStatement.setString(1, userID);
            preparedStatement.setString(2, item.getId());

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - removeItemToUserBlacklist()");
            throw new RepositoryException("There went something wrong with the DB! - removeItemToUserBlacklist()");
        }
    }

    @Override
    public boolean isUserBlackListExist(String userID) {
        return true;
    }

    @Override
    public Set<Transporter> getTransporters() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Transporters")){
            Set<Transporter> transporters = new HashSet<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                transporters.add(convertorSQL.sqlToTransporter(resultSet, getBuilding(resultSet.getString("buildingID"))));
            }

            return transporters;
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getTransporters()");
            throw new RepositoryException("There went something wrong with the DB! - getTransporters()");
        }
    }

    @Override
    public void addTransporter(Transporter transporter) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Transporters (uid, name, height, length, width, buildingID, ip) VALUES (?,?,?,?,?,?,?)")){
            preparedStatement.setString(1, transporter.getId());
            preparedStatement.setString(2, transporter.getName());
            preparedStatement.setDouble(3, transporter.getSize().getHeight());
            preparedStatement.setDouble(4, transporter.getSize().getLength());
            preparedStatement.setDouble(5, transporter.getSize().getWidth());
            preparedStatement.setString(6, transporter.getBuilding().getId());
            preparedStatement.setString(7, transporter.getIp());

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addTransporter()");
            throw new RepositoryException("There went something wrong with the DB! - addTransporter()");
        }
    }

    @Override
    public Transporter getTransporter(String transporterID) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Transporters WHERE uid = ?")){
            preparedStatement.setString(1, transporterID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertorSQL.sqlToTransporter(resultSet, getBuilding(resultSet.getString("buildingID")));
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getTransporter()");
            throw new RepositoryException("There went something wrong with the DB! - getTransporter()");
        }
    }

    @Override
    public void updateTransporter(Transporter transporter) {
        deleteTransporter(transporter);
        addTransporter(transporter);
    }

    @Override
    public void deleteTransporter(Transporter transporter) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Transporters WHERE uid = ?")){
            preparedStatement.setString(1, transporter.getId());
            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - deleteTransporter()");
            throw new RepositoryException("There went something wrong with the DB! - deleteTransporter()");
        }
    }

    @Override
    public void addBuilding(Building building) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Buildings (uid, typeOfLocation, longitude, latitude) VALUES (?,?,?,?)")){
            preparedStatement.setString(1, building.getId());
            preparedStatement.setString(2, building.getTypeOfLocation().toString());
            preparedStatement.setFloat(3, building.getCoordinates().getLongitude());
            preparedStatement.setFloat(4, building.getCoordinates().getLatitude());

            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addBuilding()");
            throw new RepositoryException("There went something wrong with the DB! - addBuilding()");
        }

    }

    @Override
    public void removeBuilding(Building building) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Buildings WHERE uid = ?")){
            preparedStatement.setString(1, building.getId());
            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - removeBuilding()");
            throw new RepositoryException("There went something wrong with the DB! - deleteTransporter()");
        }
    }

    @Override
    public Building getBuilding(String buildingID) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Buildings WHERE uid = ?")){
            preparedStatement.setString(1, buildingID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Building(resultSet.getString("uid"), TypeOfLocation.valueOf(resultSet.getString("typeOfLocation")), new Coordinates(resultSet.getFloat("longitude"), resultSet.getFloat("latitude")));
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getTransporters()");
            throw new RepositoryException("There went something wrong with the DB! - getTransporters()");
        }
    }

    @Override
    public Building getBuildingFromCoordinates(Coordinates coordinates) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Buildings WHERE longitude = ? AND latitude = ?")){
            preparedStatement.setFloat(1, coordinates.getLongitude());
            preparedStatement.setFloat(2, coordinates.getLatitude());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return new Building(resultSet.getString("uid"), TypeOfLocation.valueOf(resultSet.getString("typeOfLocation")), new Coordinates(resultSet.getFloat("longitude"), resultSet.getFloat("latitude")));
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getBuildingFromCoordinates()");
            throw new RepositoryException("There went something wrong with the DB! - getBuildingFromCoordinates()");
        }
    }

    @Override
    public Set<Link> getAllLinks() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Links")){
            Set<Link> links = new HashSet<>();
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                links.add(convertorSQL.sqlToLink(resultSet,
                                this.getTransporter(resultSet.getString("senderTransporter")),
                                this.getTransporter(resultSet.getString("receiverTransporter")),
                                this.getUser(resultSet.getString("senderUser")),
                                this.getUser(resultSet.getString("receiverUser")),
                                this.getItem(resultSet.getString("item"))
                                ));
            }

            return links;
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getAllLinks()");
            throw new RepositoryException("There went something wrong with the DB! - getAllLinks()");
        }
    }

    @Override
    public Link getLink(String linkID) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Links WHERE uid = ?")){
            preparedStatement.setString(1, linkID);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            return convertorSQL.sqlToLink(resultSet,
                    this.getTransporter(resultSet.getString("senderTransporter")),
                    this.getTransporter(resultSet.getString("receiverTransporter")),
                    this.getUser(resultSet.getString("senderUser")),
                    this.getUser(resultSet.getString("receiverUser")),
                    this.getItem(resultSet.getString("item")));

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getLink()");
            throw new RepositoryException("There went something wrong with the DB! - getAllLinks()");
        }
    }

    @Override
    public void addLink(Link link) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Links (uid, senderTransporter, receiverTransporter, senderUser, receiverUser , linkStatus, item) VALUES (?,?,?,?,?,?,?")){
            preparedStatement.setString(1, link.getId());
            preparedStatement.setString(2, link.getSender().getId());
            preparedStatement.setString(3, link.getSender().getId());
            preparedStatement.setString(4, link.getSenderUser().getId());
            preparedStatement.setString(5, link.getReceiverUser().getId());
            preparedStatement.setString(6, link.getLinkStatus().toString());
            preparedStatement.setString(7, link.getItem().getId());

            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getLink()");
            throw new RepositoryException("There went something wrong with the DB! - getAllLinks()");
        }
    }

    @Override
    public void deleteLink(Link link) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("DELETE FROM Links WHERE uid = ?")){
            preparedStatement.setString(1, link.getId());
            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - deleteLink()");
            throw new RepositoryException("There went something wrong with the DB! - deleteLink()");
        }
    }

    @Override
    public List<ShipNotification> getShipNotifications() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Notifications WHERE type = ?")){
            List<ShipNotification> shipNotifications = new ArrayList<>();
            preparedStatement.setString(1, "SHIP");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                 shipNotifications.add(convertorSQL.sqlToShipNotification(resultSet, this.getUser(resultSet.getString("receiver"))));
            }

            return shipNotifications;
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getShipNotifications()");
            throw new RepositoryException("There went something wrong with the DB! - getShipNotifications()");
        }
    }

    @Override
    public List<SystemNotification> getSystemNotifications() {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Notifications WHERE type = ?")){
            List<SystemNotification> systemNotifications = new ArrayList<>();
            preparedStatement.setString(1, "SYSTEM");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                systemNotifications.add(convertorSQL.sqlToSystemNotification(resultSet));
            }

            return systemNotifications;
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - getShipNotifications()");
            throw new RepositoryException("There went something wrong with the DB! - getShipNotifications()");
        }
    }

    @Override
    public void addShipNotification(ShipNotification notification) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Notifications (type, title, expireTime, message, createDate, receiver) VALUES (?,?,?,?,?,?")){
            preparedStatement.setString(1, "SHIP");
            preparedStatement.setString(2, notification.getTitle());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(notification.getExpireTime()));
            preparedStatement.setString(4, notification.getMessage());
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(notification.getTime()));
            preparedStatement.setString(6, notification.getReceiver().getId());

            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addShipNotification()");
            throw new RepositoryException("There went something wrong with the DB! - addShipNotification()");
        }
    }

    @Override
    public void addSystemNotification(SystemNotification notification) {
        try( PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Notifications (type, title, expireTime, message, createDate, receiver) VALUES (?,?,?,?,?,?")){
            preparedStatement.setString(1, "SYSTEM");
            preparedStatement.setString(2, notification.getTitle());
            preparedStatement.setTimestamp(3, java.sql.Timestamp.valueOf(notification.getExpireTime()));
            preparedStatement.setString(4, notification.getMessage());
            preparedStatement.setTimestamp(5, java.sql.Timestamp.valueOf(notification.getTime()));
            preparedStatement.setString(6, null);

            preparedStatement.execute();

        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addSystemNotification()");
            throw new RepositoryException("There went something wrong with the DB! - addSystemNotification()");
        }
    }

    protected void addItem(Item item){
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO Items (uid, name, mapSummary, molecules, height, length, width, sendTime) VALUES (?,?,?,?,?,?,?,?)")){

            preparedStatement.setString(1, item.getId());
            preparedStatement.setString(2, item.getName());
            if (item.getMolecules() == null){
                preparedStatement.setString(3, null);
                preparedStatement.setString(4, null);
                preparedStatement.setTimestamp(8, null);
            } else {
                preparedStatement.setString(3, Json.encode(item.getMolecules()));
                preparedStatement.setString(4, Json.encode(item.getMolecules().getMolecules()));
                preparedStatement.setTimestamp(8,  java.sql.Timestamp.valueOf(item.getSendTime()));
            }
            preparedStatement.setDouble(5, item.getSize().getHeight());
            preparedStatement.setDouble(6, item.getSize().getLength());
            preparedStatement.setDouble(7, item.getSize().getWidth());


            preparedStatement.execute();
        } catch (SQLException e){
            System.out.println(e);
            LOGGER.log(Level.SEVERE, "DB error - addItem()");
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
