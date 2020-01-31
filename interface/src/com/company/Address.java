package com.company;

import GUI.AttentionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Address {
    private Integer id;
    private String name;
    private String id1;
    private String address;
    private String house_phone_number;
    private String customer_id;


    public Address(){

    }
    public Address(Integer id, String name, String address, String house_phone_number, String customer_id) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.house_phone_number = house_phone_number;
        this.customer_id = customer_id;
        id1 = ""+id;
    }

    public boolean addToDataBase(String customer_id){
        String statement = SQLStatement.select("address","max(id)");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("user exists : "+rs.getInt(1));
                id = rs.getInt(1)+1;
                this.customer_id = customer_id;
            }
            else {
                System.out.println("some thing went wrong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        try{
            ArrayList<Object> objects = allDatas();
                statement = SQLStatement.insert("address",objects.size());
                PreparedStatement preparedStatement = null;

                preparedStatement = DBConnection.connection.prepareStatement(statement);
                SQLTypeGenerator.setdata(preparedStatement,objects);
                preparedStatement.execute();
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_id() {
        return customer_id;
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

    public String getId1() {
        return id1;
    }


    public static ArrayList<Address> findAddress(String user){
        ArrayList<Address>addresses = new ArrayList<Address>();
        String statement = SQLStatement.select("address","*","customer_id = \'"+user+"\'");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Integer id =  rs.getInt("id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                String house_phone_number = rs.getString("house_phone_number");
                String customer_id = user;
                addresses.add(new Address(id,name,address,house_phone_number,customer_id));

            }
                return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return addresses;
    }
    public boolean removeAddress(){
        return SQLInstructions.remove("address","id = \'"+id+"\'");
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id)
    {
        id1 = ""+id;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouse_phone_number() {
        return house_phone_number;
    }


    public boolean setnewAddress(){
        return addToDataBase(customer_id);
    }
    public void setHouse_phone_number(String house_phone_number) {
        this.house_phone_number = house_phone_number;
    }
    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(name);
        objects.add(address);
        objects.add(house_phone_number);
        objects.add(customer_id);
        return objects;
    }

    public boolean update(){
        String values = String.format("name  = \'%s\',id  = \'%s\', address  = \'%s\',house_phone_number  = \'%s\', customer_id  = \'%s\'" +
                "",name,id,address,house_phone_number,customer_id);
        return SQLInstructions.update("menu",values,"id = \'"+id +"\'");
    }
}
