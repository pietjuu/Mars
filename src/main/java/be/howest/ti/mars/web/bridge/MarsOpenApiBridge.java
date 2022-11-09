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


        LOGGER.log(Level.INFO, "All handlers are installed, creating router.");
        return routerBuilder.createRouter();
    }

    public MarsOpenApiBridge() {
        this.controller = new DefaultMarsController();
        this.tokenManager = new Tokens();
    }

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
