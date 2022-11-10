package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;

import java.util.ArrayList;
import java.util.List;

public class Blacklist {

    private final List<Item> list;

    public Blacklist(){
        this.list = new ArrayList<>();
    }

}
