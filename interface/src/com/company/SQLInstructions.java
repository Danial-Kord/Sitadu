package com.company;

import GUI.AttentionPane;

import java.sql.SQLException;
import java.sql.Statement;

public class SQLInstructions {

    public static boolean remove(String table,String cond){
        String sql = SQLStatement.delete(table,cond);
        Statement statement = DBConnection.statement;

        try {

            statement.executeUpdate(sql);
            System.out.println("Record deleted successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());
        }
       return false;
    }
    public static boolean update(String table,String values,String cond){
        String sql = SQLStatement.update(table,values,cond);
        Statement statement = DBConnection.statement;
        try {
            statement.executeUpdate(sql);
            System.out.println("Record updated successfully");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());
        }
        return false;
    }
}
