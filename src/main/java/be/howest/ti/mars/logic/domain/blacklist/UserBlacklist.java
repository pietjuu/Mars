package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;

public class UserBlacklist extends Blacklist{

    private final String userID;

    public UserBlacklist(String userID){
        this.userID = userID;
    }

    public void removeItem(Item item){
        list.remove(item);
    }

    public String getUserID() {
        return userID;
    }
}
