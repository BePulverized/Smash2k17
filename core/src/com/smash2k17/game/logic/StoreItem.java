package com.smash2k17.game.logic;

import java.awt.*;

/**
 * Created by BePul on 27-3-2017.
 */
public abstract class StoreItem {

    private double price;
    private String name;
    private Image previewImage;

    public StoreItem(double price, String name, Image previewImage)
    {
        this.price = price;
        this.name = name;
        this.previewImage = previewImage;
    }

    public StoreItem(double price, String name)
    {
        this.price = price;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Image getPreviewImage() {
        return previewImage;
    }
}
