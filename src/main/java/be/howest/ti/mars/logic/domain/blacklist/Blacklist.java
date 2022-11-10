package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Blacklist {

    private final Set<Item> list;

    public Blacklist(){
        this.list = new HashSet<>();
    }

    public List<Item> getItems(){
        return new ArrayList<>(list);
    }

    public void addItem(Item item){
        list.add(item);
    }

}
