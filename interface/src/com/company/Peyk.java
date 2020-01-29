package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Peyk {

    private int id;
    private String first_name;
    private String last_name;
    private String phone;
    int busy = 0;

    public Peyk(){

    }
    public Peyk(int id, String first_name, String last_name, String phone) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone = phone;
    }

    private boolean setNewId(){
        String statement = SQLStatement.selectWithCond("peyk","max(id)");
        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("max peyk id : "+rs.getInt(1));
                id = rs.getInt(1)+1;
                return true;
            }
            else {
                System.out.println("some thing went wrong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean addNewFactorNoName(){
        if(!setNewId())
            return false;
        String statement;
        try{
            ArrayList<Object>allDatas = allDatas();
            statement = SQLStatement.insert("peyk",allDatas.size());
            PreparedStatement preparedStatement = null;
            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean remove() {
        return SQLInstructions.remove("peyk","id = \'" +id + "\'");
    }
    public ArrayList<Object> allDatas(){
        ArrayList<Object>objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(first_name);
        objects.add(last_name);
        objects.add(phone);
        return objects;
    }

    public int getId() {
        return id;
    }
}
