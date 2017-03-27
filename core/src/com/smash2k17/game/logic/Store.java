package com.smash2k17.game.logic;

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

    public void AddItems(StoreItem item)
    {
        items.add(item);
    }
}
