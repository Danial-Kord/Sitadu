package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class Factor {
    private int id;
    private int customer_id;
    private ArrayList<Food> foods;
    private String total_price;
    private Timestamp time;
    private String name;


    private boolean setNewId(){
        String statement = SQLStatement.select("factor","max(id)");
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

    public boolean addNewFactor(int customer_id,String name){
        String statement;
        if(!setNewId())
            return false;
        try{
            time = new Timestamp(Calendar.getInstance().getTime().getTime());
            this.customer_id = customer_id;
            this.name = name;
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
            allDatas.add(food.getPrice());
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


    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(customer_id);
        objects.add(name);
        objects.add(time);
        return objects;
    }
}
