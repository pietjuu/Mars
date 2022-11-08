package be.howest.ti.mars.web.util;

import be.howest.ti.mars.logic.domain.users.User;

public class PlainTextTokens implements TokenManager {

    @Override
    public String createToken(User user) {
        return user.getId();
    }

}
