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

    }

    @Override
    public Set<User> getUsers() {
        try{
            Set<User> users = new HashSet<>();
            PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM Users");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                users.add(convertorSQL.sqlToUser(resultSet));
            }

            return users;
        } catch (SQLException e){
            LOGGER.log(Level.SEVERE, "DB error");
            throw new RepositoryException("There went something wrong with the DB!");
        }
    }

    @Override
    public User getUser(String userID) {
        return null;
    }

    @Override
    public void deleteUser(String userID) {

    }

    @Override
    public Blacklist getShippertBlacklist() {
        return null;
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
}
