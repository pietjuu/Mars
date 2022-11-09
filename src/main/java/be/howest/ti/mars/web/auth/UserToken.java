package be.howest.ti.mars.web.auth;

import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.impl.UserImpl;

public class UserToken extends UserImpl {

    public UserToken(String id){
        super(new JsonObject().put("id", id));
    }

    public String getId(){
        return this.principal().getString("id");
    }

}
