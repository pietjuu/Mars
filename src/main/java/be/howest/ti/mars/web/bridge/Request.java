package be.howest.ti.mars.web.bridge;

import be.howest.ti.mars.web.auth.UserToken;
import be.howest.ti.mars.web.exceptions.ForbiddenAccessException;
import be.howest.ti.mars.web.exceptions.MalformedRequestException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.validation.RequestParameters;
import io.vertx.ext.web.validation.ValidationHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The Request class is responsible for translating information that is part of the
 * request into Java.
 * For every piece of information that you need from the request, you should provide a method here.
 * You can find information in:
 * - the request path: params.pathParameter("some-param-name")
 * - the query-string: params.queryParameter("some-param-name")
 * Both return a `RequestParameter`, which can contain a string or an integer in our case.
 * The actual data can be retrieved using `getInteger()` or `getString()`, respectively.
 * You can check if it is an integer (or not) using `isNumber()`.
 * Finally, some requests have a body. If present, the body will always be in the json format.
 * You can acces this body using: `params.body().getJsonObject()`.
 * **TIP:** Make sure that al your methods have a unique name. For instance, there is a request
 * that consists of more than one "player name". You cannot use the method `getPlayerName()` for both,
 * you will need a second one with a different name.
 */
public class Request {
    public static final String ERROR_BODY = "Unable to decipher the data in the request body. See logs for details.";
    private static final Logger LOGGER = Logger.getLogger(Request.class.getName());
    public static final String ITEM_NAME = "itemName";
    private final RequestParameters params;
    private final UserToken userToken;

    public static Request from(RoutingContext ctx) {
        return new Request(ctx);
    }

    private Request(RoutingContext ctx) {
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
        this.userToken = (UserToken) ctx.user();
    }

    public String getToken(){
        return userToken.getId();
    }

    public String getUserFirstname() {
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getString("firstname");
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'firstname' in the body of POST user", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getUserLastname() {
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getString("lastname");
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'lastname' in the body of POST user", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getItemNameBody(){
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getString(ITEM_NAME);
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'itemName' in the body of GET blacklist", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getItemNameParam(){
        try {
            if (!params.pathParameter(ITEM_NAME).isNull())
                return params.pathParameter(ITEM_NAME).toString();
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'itemName' in the path of POST blacklist", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getUserID() {
        try {
            if (!params.pathParameter("userId").getString().equals(getToken())){
                LOGGER.log(Level.INFO, "Unable to get user, userID not in connection with authentication ID.");
                throw new ForbiddenAccessException("User ID is not in connection with authentication ID.");
            }
            return params.pathParameter("userId").getString();
        } catch (IllegalArgumentException ex){
            LOGGER.log(Level.INFO, "Unable to decipher 'userId' in the path of GET user", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getUserPricePlan(){
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getString("subscription");
            return params.body().get().toString();
        } catch (IllegalArgumentException ex){
            LOGGER.log(Level.INFO, "Unable to decipher 'subscription' in the body of POST user", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getTransporterID(){
        try{
            if (!params.pathParameter("transporterId").isNull()){
                return params.pathParameter("transporterId").getString();
            }
            return null;
        } catch (IllegalArgumentException ex){
            LOGGER.log(Level.INFO, "Unable to decipher 'TransporterID' in the path of GET transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getTransporterNameBody(){
        try {
            if (params.body().isJsonObject())
                return params.body().getJsonObject().getString("name");
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'name' in the body of POST transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public Double[] getTransporterSizeBody(){
        try {
            if (params.body().isJsonObject()){
                JsonObject size = params.body().getJsonObject().getJsonObject("size");
                return new Double[]{size.getDouble("length"), size.getDouble("width"), size.getDouble("depth")};
            }

            return new Double[]{};
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'size' in the body of POST transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public Float[] getTransporterCoordinatesBody(){
        try {
            if (params.body().isJsonObject()){
                JsonObject size = params.body().getJsonObject().getJsonObject("location").getJsonObject("coordinates");
                return new Float[]{size.getFloat("longitude"), size.getFloat("latitude")};
            }

            return new Float[]{};
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'size' in the body of POST transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getTransporterTypeOfBuildingBody(){
        try {
            if (params.body().isJsonObject()){
                return params.body().getJsonObject().getJsonObject("location").getJsonObject("building").getString("typeOfBuilding");
            }
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'typeOfBuilding' in the body of POST transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }

    public String getTransporterIpAddressBody(){
        try {
            if (params.body().isJsonObject()){
                return params.body().getJsonObject().getString("ipAddress");
            }
            return params.body().get().toString();
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.INFO, "Unable to decipher 'ipAddress' in the body of POST transporter", ex);
            throw new MalformedRequestException(ERROR_BODY);
        }
    }
}
