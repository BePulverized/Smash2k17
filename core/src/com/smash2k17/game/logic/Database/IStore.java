package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.Product;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by Stef on 18-4-2017.
 */
public interface IStore {

    ArrayList<Product> getProducts() throws SQLException;
    void koopProduct(int idProduct, int idKlant)throws SQLException;
    void changeBalance(double bedrag, int id)throws SQLException;
    BigDecimal getBalance(int idKlant) throws SQLException;
    BigDecimal getPrijs(int idProduct) throws SQLException;
}
