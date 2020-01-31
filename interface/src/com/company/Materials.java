package com.company;

import GUI.AttentionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Materials {
    protected int id;
    protected String name;
    protected int price;
    private String id1;
    private String price1;

    public Materials(){}
    public Materials(String name, int price) {
        this.name = name;
        this.price = price;
        price1 = ""+price;
    }

    public Materials(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
        price1 = ""+price;
        id1 = ""+id;
    }

    protected boolean setNewId(String table){
        String statement = SQLStatement.select(table,"max(id)");
        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("max menu id : "+rs.getInt(1));
                id = rs.getInt(1)+1;
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

    public boolean updateDataBase(String table){
        if(!setNewId(table))
            return false;
        String statement;
        PreparedStatement preparedStatement = null;
        try {
            ArrayList<Object> allDatas = allDatas();
            statement = SQLStatement.insert(table,allDatas.size());
            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());


        }
        return false;
    }

    public void setId1(String id1) {
        try {
            id = Integer.parseInt(id1);
        }
        catch (NumberFormatException e){
            AttentionPane.Error(e.getLocalizedMessage());
        }
        this.id1 = id1;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String id1) {
        try {
            price = Integer.parseInt(id1);
        }
        catch (NumberFormatException e){
            AttentionPane.Error(e.getLocalizedMessage());
        }
        this.price1 = price1;
    }

    public String getId1() {
        return id1;
    }

    //    public static ArrayList<Food> findAllFoods(String table){
//
//        ArrayList<Food> foods = new ArrayList<Food>();
//        String statement = SQLStatement.select(table,"*");
//        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
//
//            while (rs.next()) {
//                int id = rs.getInt("id");
//                String name = rs.getString("name");
//                int price  = rs.getInt("price");
//                foods.add(new Food(id,name,price));
//            }
//            return foods;
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return foods;
//    }
    protected boolean remove(String table,String cond){
        return SQLInstructions.remove(table,cond);
    }

    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        price1 = ""+price;
    }

    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(name);
        objects.add(price);
        return objects;
    }

    public void setId(int id) {
        id1 = ""+id;
        this.id = id;
    }

}
