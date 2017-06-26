package com.smash2k17.game.logic.Database;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

/**
 * Created by Stef on 18-4-2017.
 */
public class AccountContext implements IAccount, Serializable {

    private static String connString = "jdbc:mysql://localhost:3306/smash2k17";
    private static String connUser = "student";
    private static String connPassword = "Proftaakgroep32C";


    public String encrypt(String base) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = mDigest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();

        } catch (RuntimeException e) {
            throw e;
        } catch (NoSuchAlgorithmException e) {
            throw e;
        } catch (UnsupportedEncodingException e) {
            throw e;
        }
    }

    public Account logIn(String email, String password) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException, RemoteException {
        Account result = null;
        Connection myConn = DriverManager.getConnection(connString, connUser, connPassword);
        PreparedStatement preparedStmt;
        ResultSet myRs;
        String encryptedPassword = encrypt(password);

        String query = "select ID, password, Email, Balance from Account where Email = ?;";
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setString(1, email);
        myRs = preparedStmt.executeQuery();

        while (myRs.next()) {
            if(encryptedPassword.equals( myRs.getString("password"))) {
                result = new Account(myRs.getInt("ID"), myRs.getString("Email"), myRs.getDouble("Balance"));
            }
        }
        preparedStmt.close();
        myConn.close();
        return result;
    }

    public void addUser(String email, String password, double balance) throws SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Connection myConn = null;
        PreparedStatement preparedStmt = null;
        String encryptedPassword = encrypt(password);
        String query = " insert into Account (Email, Password, Balance)"
                + " values (?, ?, ?)";
        try {
        myConn = DriverManager.getConnection(connString, connUser, connPassword);
        preparedStmt = myConn.prepareStatement(query);
        preparedStmt.setString(1, email);
        preparedStmt.setString(2, encryptedPassword);
        preparedStmt.setDouble(3, 0.00);
        preparedStmt.execute();
           }
           catch (SQLException e){
               throw e;
           }
           finally {
               if(preparedStmt !=null) {
                   preparedStmt.close();
               }
               if(myConn !=null) {
                   myConn.close();
               }
           }
    }
}
