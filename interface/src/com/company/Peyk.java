package com.company;

import GUI.AttentionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Peyk {

    private int id;
    private String id1;
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
        id1 = ""+id;
    }

    private boolean setNewId(){
        String statement = SQLStatement.select("peyk","max(id)");
        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("max peyk id : "+rs.getInt(1));
                id = rs.getInt(1)+1;
                id1 = ""+id;
                return true;
            }
            else {
                System.out.println("some thing went wrong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }


    public boolean update(){
        String values = String.format("first_name = \'%s\', last_name = \'%s\'" +
                ",phone = \'%s\'",first_name,last_name,phone);
        return SQLInstructions.update("peyk",values,"id = \'"+id +"\'");
    }
    public boolean addNewPeyk(){
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
            AttentionPane.Error(e.getLocalizedMessage());
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

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getPhone() {
        return phone;
    }

    public int getBusy() {
        return busy;
    }

    public void setId(int id) {
        this.id = id;
        id1 = ""+id;
    }

    public void setId1(String id1) {
        this.id1 = id1;
        try {
            id = Integer.parseInt(id1);
        }
        catch (NumberFormatException e){
            AttentionPane.Error("number exp");
        }
    }

    public String getId1() {
        return id1;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    public int getId() {
        return id;
    }
}
