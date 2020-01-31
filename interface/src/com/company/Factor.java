package com.company;

import GUI.AttentionPane;
import GUI.GuiManager;

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
    private String id1;
    private String peyk_id1;
    private String time1;

    public Factor(){

    }

    public Factor(int id, String customer_id, String total_price, Timestamp time, String name,String peyk_id) {
        foods = new ArrayList<Food>();
        this.id = id;
        this.customer_id = customer_id;
        this.total_price = total_price;
        this.time = time;
        this.name = name;
        id1 = ""+ id;
        peyk_id1 = ""+peyk_id;
        if(time!=null)
        time1 = "" +time.toString();
    }


    public String getId1() {
        return id1;
    }

    public String getPeyk_id1() {
        return peyk_id1;
    }

    public String getTime1() {
        return time1;
    }

    public boolean findFoodsOfFactor(){

        String statement = SQLStatement.select("menu_factor","*","factor_id = \'"+id+"\'");
        try {
            ResultSet rs = DBConnection.myExcuteQuery(statement);

            while (rs.next()) {
                int id = rs.getInt("id");
                String food_name = rs.getString("food_name");
                int price  = rs.getInt("price");
                int food_id = rs.getInt("food_id");
                foods.add(new Food(id,food_name,price));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

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
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }
    public boolean addNewFactorNoName(){
        System.out.println("ggggg");

        if(!setNewId())
            return false;

        String statement;
        try{
            ArrayList<Object>allDatas = allDatas();
            allDatas.remove(customer_id);
            allDatas.remove(peyk_id);
            allDatas.remove(time);
            statement = SQLStatement.insert("factor","id, name, total_price");
            PreparedStatement preparedStatement = null;
            time = new Timestamp(Calendar.getInstance().getTime().getTime());
            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }




    public boolean addNewFactor(String customer_id, String name,Peyk peyk){
        System.out.println("ffffff");
        String statement;
        if(!setNewId())
            return false;
        if(customer_id == null)
            return addNewFactorNoName();
        if(customer_id.equals(""))
            return addNewFactorNoName();
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
            System.out.println("shit");
            preparedStatement.execute();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        System.out.println("fffuk");
        return false;
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', total_price  = \'%s\', customer_id  = \'%s\'" +
                ", time  = \'%s\', peyk_id  = \'%s\'",name,total_price,customer_id,time,peyk_id);
        return SQLInstructions.update("factor",values,"id = \'"+id +"\'");
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
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

    public boolean removeFoodToFactor(Food food){
        //TODO
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
        objects.add(total_price);
        return objects;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public ArrayList<Food> getFoods() {
        return foods;
    }

    public void setFoods(ArrayList<Food> foods) {
        this.foods = foods;
    }

    public String getTotal_price() {
        return total_price;
    }

    public void setTotal_price(String total_price) {
        this.total_price = total_price;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeyk_id() {
        return peyk_id;
    }

    public void setPeyk_id(int peyk_id) {
        this.peyk_id = peyk_id;
    }
}
