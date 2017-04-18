package com.smash2k17.game.logic.Database;

import com.smash2k17.game.logic.Product;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by Stef on 18-4-2017.
 */
public class StoreContext implements IStore {
    private static String connString = "jdbc:mysql://studmysql01.fhict.local/dbi307792?useSSL=false";
    private static String connUser = "dbi307792";
    private static String connPassword = "Wachtwoord1";

    public ArrayList<Product> getProducts() throws SQLException {
        ArrayList<Product> products = new ArrayList<Product>();
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt = null;
        ResultSet myRs;

        String query = "select name, price from storeitem;";
        preparedStmt = myConn.prepareStatement(query);
        myRs = preparedStmt.executeQuery();

        while (myRs.next()) {
           products.add( new Product(myRs.getBigDecimal("Price").doubleValue(), myRs.getString("Name")));
        }
        myConn.close();
        preparedStmt.close();
        myRs.close();
        return products;
    }

    public void koopProduct(int idProduct, int idKlant) throws SQLException {
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt = null;
        String query;

        if(getPrijs(idProduct).doubleValue()<= getBalance(idKlant).doubleValue()) {
            query = " insert into Account_storeitem (storeitemid, accountid)"
                    + " values (?, ?)";
            preparedStmt = myConn.prepareStatement(query);
            preparedStmt.setInt(1, idProduct);
            preparedStmt.setInt(2, idKlant);

            preparedStmt.execute();

            changeBalance(getPrijs(idProduct).doubleValue() * -1, idKlant);
            System.out.println("betaling gelukt");
            myConn.close();
            preparedStmt.close();
        }
        else{
            System.out.println("saldo onvoldoende");
        }
    }

    public void changeBalance(double bedrag, int id) throws SQLException {
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt;
        ResultSet myRs;
        double balance=getBalance(id).doubleValue();

        balance += bedrag;

        String query = "UPDATE account SET Balance=? WHERE ID=?";
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setBigDecimal(1, BigDecimal.valueOf(balance));
        preparedStmt.setInt(2, id);
        preparedStmt.execute();

        myConn.close();
        preparedStmt.close();
    }

    public BigDecimal getBalance(int idKlant) throws SQLException {
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt;
        ResultSet myRs;
        String query;

        query = "select balance from Account where id = ?;";
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setInt(1, idKlant);
        myRs = preparedStmt.executeQuery();
        while (myRs.next()) {
            return myRs.getBigDecimal("balance");
        }
        return null;
    }

    public BigDecimal getPrijs(int idProduct) throws SQLException {
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt;
        ResultSet myRs;
        String query;

        query = "select price from storeitem where id = ?;";
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setInt(1, idProduct);
        myRs = preparedStmt.executeQuery();
        while (myRs.next()) {
            return myRs.getBigDecimal("price");
        }
        return null;
    }


}
