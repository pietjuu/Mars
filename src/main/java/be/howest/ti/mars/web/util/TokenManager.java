package be.howest.ti.mars.web.util;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.AuthenticationProvider;

public interface TokenManager extends AuthenticationProvider {

    @Override
    default void authenticate(JsonObject credentials, Handler<AsyncResult<User>> handler) {

        try {
            handler.handle(Future.succeededFuture(
            ));
        } catch (InvalidTokenException ex) {
            handler.handle(Future.failedFuture(ex));
        }
    }

    String createToken(be.howest.ti.mars.logic.domain.users.User user);
}
