package be.howest.ti.mars.logic.domain.blacklist;

import be.howest.ti.mars.logic.domain.items.Item;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Blacklist class
 * Should only be directly called when it is about the Shippert blacklist.
 */
public class Blacklist {

    protected final Set<Item> list;

    public Blacklist(){
        this.list = new HashSet<>();
    }

    public List<Item> getItems(){
        return new ArrayList<>(list);
    }

    public void addItem(Item item){
        list.add(item);
    }

    public boolean containsItem(String itemName){
        boolean result = false;
        for (Item i : list){
            if (i.getName().equals(itemName)) {
                result = true;
                break;
            }
        }

        return result;
    }

}
