package be.howest.ti.mars.logic.domain.blacklist;

public class UserBlacklist extends Blacklist{

    private final String userID;

    public UserBlacklist(String userID){
        this.userID = userID;
    }

}
