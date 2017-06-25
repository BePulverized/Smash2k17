package com.smash2k17.game.logic.Database;

import com.badlogic.gdx.math.Interpolation;
import com.smash2k17.game.logic.Product;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Stef on 18-4-2017.
 */
public class StoreContext implements IStore, Serializable {
    private static String connString = "jdbc:mysql://localhost:3306/proftaak?useSSL=false";
    private static String connUser = "root";
    private static String connPassword = "password";

    public ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList();
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt;
        ResultSet myRs;

        String query = "select name, price from storeitem;";
        preparedStmt = myConn.prepareStatement(query);
        myRs = preparedStmt.executeQuery();
        preparedStmt.close();
        myConn.close();
        while (myRs.next()) {
            products.add(new Product(myRs.getBigDecimal("Price").doubleValue(), myRs.getString("Name")));
        }
        return products;
    }

    public void koopProduct(int idProduct, int idKlant) throws SQLException {
        Connection myConn = null;
        PreparedStatement preparedStmt = null;
        try {
            myConn = DriverManager.getConnection(connString, connUser, connPassword);
            String query;

            if (getPrijs(idProduct).doubleValue() <= getBalance(idKlant).doubleValue()) {
                query = " insert into account_storeitem (storeitemid, accountid)"
                        + " values (?, ?)";
                preparedStmt = myConn.prepareStatement(query);
                preparedStmt.setInt(1, idProduct);
                preparedStmt.setInt(2, idKlant);

                preparedStmt.execute();
                preparedStmt.close();
                myConn.close();

                changeBalance(getPrijs(idProduct).doubleValue() * -1, idKlant);
                System.out.println("betaling gelukt");
            } else {
                System.out.println("saldo onvoldoende");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeSQL(preparedStmt, myConn);
        }
    }

    public void changeBalance(double bedrag, int id) throws SQLException {
        Connection myConn = null;
        PreparedStatement preparedStmt = null;
        try {
            myConn = DriverManager.getConnection(connString, connUser, connPassword);

        double balance = getBalance(id).doubleValue();

        balance += bedrag;

        String query = "UPDATE account SET Balance=? WHERE ID=?";
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setBigDecimal(1, BigDecimal.valueOf(balance));
        preparedStmt.setInt(2, id);
        preparedStmt.execute();
        } catch (SQLException e) {
            throw e;
        } finally {
            closeSQL(preparedStmt, myConn);
        }
    }

    public BigDecimal getBalance(int idKlant) throws SQLException {
        Connection myConn = null;
        PreparedStatement preparedStmt = null;

        try {
            myConn = DriverManager.getConnection(connString, connUser, connPassword);
            ResultSet myRs;
            String query;

            query = "select balance from Account where id = ?;";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, idKlant);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
                return myRs.getBigDecimal("balance");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeSQL(preparedStmt, myConn);
        }

        return null;
    }

    public BigDecimal getPrijs(int idProduct) throws SQLException {
        Connection myConn = null;
        PreparedStatement preparedStmt = null;
        try {
            myConn = DriverManager.getConnection(connString, connUser, connPassword);
            ResultSet myRs;
            String query;

            query = "select price from storeitem where id = ?;";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, idProduct);
            myRs = preparedStmt.executeQuery();
            while (myRs.next()) {
                return myRs.getBigDecimal("price");
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            closeSQL(preparedStmt, myConn);
        }
        return null;
    }

    public void closeSQL(PreparedStatement preparedStmt,Connection myConn) throws SQLException {
        if (preparedStmt != null) {
            preparedStmt.close();
        }
        if (myConn != null) {
            myConn.close();
        }
    }

}
