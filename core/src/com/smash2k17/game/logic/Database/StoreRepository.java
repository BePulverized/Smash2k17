package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Stef on 18-4-2017.
 */
public class StoreRepository implements Serializable {

    private IStore context;

    public StoreRepository(IStore context)
    {
        this.context = context;
    }

    public ArrayList<Product> getProducts() throws SQLException
    {
        return context.getProducts();
    }

    public void koopProduct(int idProduct, int idKlant) throws SQLException
    {
        context.koopProduct(idProduct, idKlant);
    }

    public void changeBalance(double bedrag, int id)throws SQLException
    {
        context.changeBalance(bedrag, id);
    }

    public BigDecimal getBalance(int idKlant) throws SQLException
    {
        return context.getBalance(idKlant);
    }

    public BigDecimal getPrijs(int idProduct) throws SQLException
    {
        return context.getPrijs(idProduct);
    }

}
