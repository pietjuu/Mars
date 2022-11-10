package be.howest.ti.mars.web.auth;
public class Tokens implements TokenManager {


    @Override
    public String createToken(UserToken user) {
        return user.getId();
    }

    @Override
    public UserToken createUser(String token) {
        return new UserToken(token);
    }
}
