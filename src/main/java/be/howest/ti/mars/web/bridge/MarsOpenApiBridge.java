package be.howest.ti.mars.web.bridge;

import be.howest.ti.mars.logic.controller.DefaultMarsController;
import be.howest.ti.mars.logic.controller.MarsController;
import be.howest.ti.mars.web.auth.BearerAuthHandler;
import be.howest.ti.mars.web.auth.TokenManager;
import be.howest.ti.mars.web.auth.Tokens;
import be.howest.ti.mars.web.auth.UserToken;
import be.howest.ti.mars.web.exceptions.ForbiddenAccessException;
import be.howest.ti.mars.web.exceptions.MalformedRequestException;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.openapi.RouterBuilder;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * In the MarsOpenApiBridge class you will create one handler-method per API operation.
 * The job of the "bridge" is to bridge between JSON (request and response) and Java (the controller).
 * <p>
 * For each API operation you should get the required data from the `Request` class.
 * The Request class will turn the HTTP request data into the desired Java types (int, String, Custom class,...)
 * This desired type is then passed to the controller.
 * The return value of the controller is turned to Json or another Web data type in the `Response` class.
 */
public class MarsOpenApiBridge {
    private static final Logger LOGGER = Logger.getLogger(MarsOpenApiBridge.class.getName());
    private final MarsController controller;
    private final TokenManager tokenManager;

    public Router buildRouter(RouterBuilder routerBuilder) {
        LOGGER.log(Level.INFO, "Installing cors handlers");
        routerBuilder.rootHandler(createCorsHandler());

        LOGGER.log(Level.INFO, "Installing failure handlers for all operations");
        routerBuilder.operations().forEach(op -> op.failureHandler(this::onFailedRequest));

        LOGGER.log(Level.INFO, "Installing security handlers");
        routerBuilder.securityHandler("userAuth", BearerAuthHandler.create(tokenManager));

        LOGGER.log(Level.INFO, "Installing handler for getInfo");
        routerBuilder.operation("getInfo").handler(this::getInfo);

        LOGGER.log(Level.INFO, "Installing handler for getUsers");
        routerBuilder.operation("getUsers").handler(this::getUsers);

        LOGGER.log(Level.INFO, "Installing handler for createUsers");
        routerBuilder.operation("createUser").handler(this::createUsers);

        LOGGER.log(Level.INFO, "Installing handler for getUser");
        routerBuilder.operation("getUser").handler(this::getUser);

        LOGGER.log(Level.INFO, "Installing handler for deleteUser");
        routerBuilder.operation("deleteUser").handler(this::deleteUser);

        LOGGER.log(Level.INFO, "Installing handler for getShippertBlacklist");
        routerBuilder.operation("getShippertBlacklist").handler(this::getShippertBlacklist);

        LOGGER.log(Level.INFO, "Installing handler for getUserBlacklist");
        routerBuilder.operation("getUserBlacklist").handler(this::getUserBlacklist);

        LOGGER.log(Level.INFO, "Installing handler for addItemToUserBlacklist");
        routerBuilder.operation("addItemToUserBlacklist").handler(this::addItemToUserBlacklist);

        LOGGER.log(Level.INFO, "Installing handler for deleteItemFromUserBlacklist");
        routerBuilder.operation("deleteItemFromUserBlacklist").handler(this::deleteItemFromUserBlacklist);

        LOGGER.log(Level.INFO, "Installing handler for getTransporters");
        routerBuilder.operation("getTransporters").handler(this::getTransporters);

        LOGGER.log(Level.INFO, "Installing handler for getTransporter");
        routerBuilder.operation("getTransporter").handler(this::getTransporter);

        LOGGER.log(Level.INFO, "Installing handler for createTransporter");
        routerBuilder.operation("createTransporter").handler(this::createTransporter);

        LOGGER.log(Level.INFO, "Installing handler for setTransporter");
        routerBuilder.operation("setTransporter").handler(this::updateTransporter);

        LOGGER.log(Level.INFO, "Installing handler for deleteTransporter");
        routerBuilder.operation("deleteTransporter").handler(this::deleteTransporter);

        LOGGER.log(Level.INFO, "All handlers are installed, creating router.");
        return routerBuilder.createRouter();
    }

    /**
     * Constructor, used in default case
     */
    public MarsOpenApiBridge() {
        this.controller = new DefaultMarsController();
        this.tokenManager = new Tokens();
    }

    /**
     * Constructor, used in test case.
     * @param controller in testing case the MockMarsController.
     */
    public MarsOpenApiBridge(MarsController controller) {
        this.controller = controller;
        this.tokenManager = new Tokens();
    }

    private void getInfo(RoutingContext routingContext){
        Response.sendInformationResponse(routingContext, 1);
    }

    private void getUsers(RoutingContext routingContext) {
        Response.sendJsonResponse(routingContext, 200, controller.getUsers());
    }

    private void createUsers(RoutingContext routingContext){
        Request request = Request.from(routingContext);
        String firstname = request.getUserFirstname();
        String lastname = request.getUserLastname();
        String subscription = request.getUserPricePlan();

        Response.sendJsonResponse(routingContext, 201, new JsonObject()
                .put("id", tokenManager.createToken(new UserToken(controller.createUser(firstname, lastname, subscription).getId())))
        );
    }

    private void getUser(RoutingContext routingContext){
        String id = Request.from(routingContext).getUserID();

        Response.sendJsonResponse(routingContext, 200, controller.getUser(id));

    }

    private void deleteUser(RoutingContext routingContext){
        String id = Request.from(routingContext).getUserID();

        controller.deleteUser(id);
        Response.sendEmptyResponse(routingContext, 202);
    }

    private void getShippertBlacklist(RoutingContext routingContext){
        Response.sendItems(routingContext, controller.getShippertBlacklist());
    }

    private void getUserBlacklist(RoutingContext routingContext){
        String id = Request.from(routingContext).getUserID();

        Response.sendItems(routingContext, controller.getUserBlacklist(id));
    }

    private void addItemToUserBlacklist(RoutingContext routingContext){
        String id = Request.from(routingContext).getUserID();
        String itemName = Request.from(routingContext).getItemNameBody();

        controller.addItemToUserBlacklist(itemName, id);
        Response.sendEmptyResponse(routingContext, 201);
    }

    private void deleteItemFromUserBlacklist(RoutingContext routingContext){
        String id = Request.from(routingContext).getUserID();
        String itemName = Request.from(routingContext).getItemNameParam();

        controller.deleteItemToUserBlacklist(itemName, id);
        Response.sendEmptyResponse(routingContext, 202);
    }

    private void getTransporters(RoutingContext routingContext){
        Response.sendTransporters(routingContext, 200, controller.getTransporters());
    }

    private void getTransporter(RoutingContext routingContext){
        String id = Request.from(routingContext).getTransporterID();

        Response.sendTransporter(routingContext, 200, controller.getTransporter(id));
    }

    private void createTransporter(RoutingContext routingContext){
        String name = Request.from(routingContext).getTransporterNameBody();
        Double[] size = Request.from(routingContext).getTransporterSizeBody();
        Float[] coordinates = Request.from(routingContext).getTransporterCoordinatesBody();
        String typeOfBuilding = Request.from(routingContext).getTransporterTypeOfBuildingBody();
        String ipAddress = Request.from(routingContext).getTransporterIpAddressBody();

        Response.sendJsonResponse(routingContext, 201, controller.createTransporter(
                name,
                controller.createSize(size),
                controller.createCoordinates(coordinates),
                typeOfBuilding,
                ipAddress
        ));
    }

    private void updateTransporter(RoutingContext routingContext){
        String id = Request.from(routingContext).getTransporterID();
        Float[] coordinates = Request.from(routingContext).getTransporterCoordinatesBody();
        String typeOfBuilding = Request.from(routingContext).getTransporterTypeOfBuildingBody();
        String ipAddress = Request.from(routingContext).getTransporterIpAddressBody();
        String name = Request.from(routingContext).getTransporterNameBody();
        Double[] size = Request.from(routingContext).getTransporterSizeBody();


        Response.sendTransporter(routingContext, 201, controller.updateTransporter(
                id,
                name,
                controller.createSize(size),
                controller.createCoordinates(coordinates),
                typeOfBuilding,
                ipAddress
        ));
    }

    private void deleteTransporter(RoutingContext routingContext){
        String id = Request.from(routingContext).getTransporterID();

        controller.deleteTransporter(id);
        Response.sendEmptyResponse(routingContext, 202);
    }

    private void onFailedRequest(RoutingContext ctx) {
        Throwable cause = ctx.failure();
        int code = ctx.statusCode();
        String quote = Objects.isNull(cause) ? "" + code : cause.getMessage();

        // Map custom runtime exceptions to a HTTP status code.
        LOGGER.log(Level.INFO, "Failed request", cause);
        if (cause instanceof IllegalArgumentException) {
            code = 400;
        } else if (cause instanceof MalformedRequestException) {
            code = 400;
        } else if (cause instanceof NoSuchElementException) {
            code = 404;
        } else if (cause instanceof ForbiddenAccessException) {
            code = 401;
        } else {
            LOGGER.log(Level.WARNING, "Failed request", cause);
        }

        Response.sendFailure(ctx, code, quote);
    }

    private CorsHandler createCorsHandler() {
        return CorsHandler.create(".*.")
                .allowedHeader("x-requested-with")
                .allowedHeader("Access-Control-Allow-Origin")
                .allowedHeader("Access-Control-Allow-Credentials")
                .allowCredentials(true)
                .allowedHeader("origin")
                .allowedHeader("Content-Type")
                .allowedHeader("Authorization")
                .allowedHeader("accept")
                .allowedMethod(HttpMethod.HEAD)
                .allowedMethod(HttpMethod.GET)
                .allowedMethod(HttpMethod.POST)
                .allowedMethod(HttpMethod.OPTIONS)
                .allowedMethod(HttpMethod.PATCH)
                .allowedMethod(HttpMethod.DELETE)
                .allowedMethod(HttpMethod.PUT);
    }
}
