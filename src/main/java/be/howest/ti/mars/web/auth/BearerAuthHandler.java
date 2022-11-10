package be.howest.ti.mars.web.auth;

import io.vertx.ext.auth.authentication.AuthenticationProvider;
import io.vertx.ext.web.handler.AuthenticationHandler;

public interface BearerAuthHandler extends AuthenticationHandler {

    static BearerAuthHandler create(AuthenticationProvider authenticationProvider){

        return new BearerAuthHandlerImpl(authenticationProvider, "vertx-web");

    }

}
