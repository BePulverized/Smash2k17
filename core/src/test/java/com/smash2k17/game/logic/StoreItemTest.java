package com.smash2k17.game.logic;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.*;

/**
 * Created by Martien on 29-May-17.
 */
public class StoreItemTest {
    private StoreItem storeItem;
    private Image img;

    @Before
    public void setUp(){
        img = new BufferedImage(50,50,1);
        storeItem = new Skins(10.1, "test", img);
    }

    @Test
    public void getNameTest() throws Exception {
        assertEquals("test", storeItem.getName());
    }

    @Test
    public void getPriceTest() throws Exception {
        assertEquals(10.1, storeItem.getPrice(), 0);
    }

    @Test
    public void getPreviewImageTest() throws Exception {
        assertEquals(img, storeItem.getPreviewImage());
    }
}