package be.howest.ti.mars.web.bridge;

import be.howest.ti.mars.web.exceptions.MalformedRequestException;
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
    private final RequestParameters params;

    public static Request from(RoutingContext ctx) {
        return new Request(ctx);
    }

    private Request(RoutingContext ctx) {
        this.params = ctx.get(ValidationHandler.REQUEST_CONTEXT_KEY);
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

}
