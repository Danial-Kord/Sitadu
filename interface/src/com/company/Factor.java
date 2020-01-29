package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Factor {
    private int id;
    private String customer_id;
    private ArrayList<Food> foods;
    private String total_price;
    private Timestamp time;
    private String name;
    private int peyk_id;

    public Factor(){

    }

    public Factor(int id, String customer_id, String total_price, Timestamp time, String name) {
        foods = new ArrayList<Food>();
        this.id = id;
        this.customer_id = customer_id;
        this.total_price = total_price;
        this.time = time;
        this.name = name;
    }

    public boolean findFoodsOfFactor(){

        String statement = SQLStatement.select("menu_factor","*","factor_id = \'"+id+"\'");
        try {
            ResultSet rs = DBConnection.myExcuteQuery(statement);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price  = rs.getInt("price");
                foods.add(new Food(id,name,price));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean setNewId(){
        String statement = SQLStatement.selectWithCond("factor","max(id)");
        try {
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("max factor id : "+rs.getInt(1));
                id = rs.getInt(1)+1;
                foods = new ArrayList<Food>();
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
            allDatas.remove(customer_id);
            statement = SQLStatement.insert("factor","id,name,time");
            PreparedStatement preparedStatement = null;
            time = new Timestamp(Calendar.getInstance().getTime().getTime());
            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addNewFactor(String customer_id, String name,Peyk peyk){
        String statement;
        if(!setNewId())
            return false;
        try{
            time = new Timestamp(Calendar.getInstance().getTime().getTime());
            this.customer_id = customer_id;
            this.name = name;
            this.peyk_id = peyk.getId();
            ArrayList<Object>allDatas = allDatas();
            statement = SQLStatement.insert("factor",allDatas.size());
            PreparedStatement preparedStatement = null;

            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean addFoodToFactor(Food food){
        String statement;
        try{
            ArrayList<Object>allDatas = new ArrayList<Object>();
            allDatas.add(id);
            allDatas.add(food.getId());
            allDatas.add(food.getName());
            allDatas.add(food.getPrice());//TODO price
            statement = SQLStatement.insert("menu_factor","factor_id,food_id,food_name,price");
            PreparedStatement preparedStatement = null;

            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            if(preparedStatement.execute()){
                foods.add(food);
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean remove() {
        return SQLInstructions.remove("factor","id = \'" +id + "\'");
    }
    public int getId() {
        return id;
    }

    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(customer_id);
        objects.add(name);
        objects.add(time);
        objects.add(peyk_id);
        return objects;
    }
}
