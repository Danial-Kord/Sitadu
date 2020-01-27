
package com.company;

import java.sql.*;


public class DBConnection {
    public static Statement statement;
    public static void connect(){

        try {
            System.out.println("trying to coonect");
            Class.forName("com.mysql.jdbc.Driver");
            String serverName = "localhost:3306";
            String mydatabase = "sitadu_DataBase";
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sitadu_DataBase","root","Dkm45477781");
            statement = connection.createStatement();
        }
        catch (SQLException e) {
            System.out.println("couldn't build statement object");
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static int myExcuteUpdate(String request){
        try {
            return statement.executeUpdate(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("problem  on calling excute update with sent request! : "+request);
        }
        return -1;
    }
    public static ResultSet myExcuteQuery(String request){
        try {
            return statement.executeQuery(request);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("problem  on calling excute query with sent request! : "+request);
        }
        return null;
    }
}
