package be.howest.ti.mars.web.auth;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.authentication.AuthenticationProvider;

public interface TokenManager extends AuthenticationProvider {

    String createToken(UserToken user);

    UserToken createUser(String token);

    @Override
    default void authenticate(JsonObject credentials, Handler<AsyncResult<User>> handler){
        TokenCredentials tokenCredentials = credentials.mapTo(TokenCredentials.class);
        String token = tokenCredentials.getToken();

        try {
            handler.handle(Future.succeededFuture(
                    this.createUser(token)
            ));
        } catch (InvalidTokenException e){
            handler.handle(Future.failedFuture(e));
        }
    }

}
