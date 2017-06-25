package com.smash2k17.game.logic;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Martien on 29-May-17.
 */
public class ProductTest {
    private Product product;

    @Before
    public void setUp(){
        product = new Product(50.1, "test");
    }

    @Test
    public void testy(){
        assertEquals("test", product.getName());
    }
}