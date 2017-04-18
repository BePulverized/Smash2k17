package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.StoreItem;

import java.util.ArrayList;

/**
 * Created by BePul on 27-3-2017.
 */
public class Store {

    private ArrayList<StoreItem> items;

    public Store()
    {
        items = new ArrayList<StoreItem>();
    }

    public ArrayList<StoreItem> getItems() {
        return items;
    }

    public void addItems(StoreItem item)
    {
        items.add(item);
    }
}
