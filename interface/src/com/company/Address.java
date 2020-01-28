package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Address {
    private Integer id;
    private String name;
    private String address;
    private String house_phone_number;



    public Address(){
        String statement = SQLStatement.select("address","max(id)");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                System.out.println("user exists : "+rs.getInt(1));
                id = rs.getInt(1)+1;
            }
            else {
                System.out.println("some thing went wrong!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void addToDataBase(){
        String statement = null;
        try{
                statement = SQLStatement.insert("address",4);
                PreparedStatement preparedStatement = null;

                preparedStatement = DBConnection.connection.prepareStatement(statement);
                SQLTypeGenerator.setdata(preparedStatement,allDatas());
                preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public void setHouse_phone_number(String house_phone_number) {
        this.house_phone_number = house_phone_number;
    }
    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(name);
        objects.add(address);
        objects.add(house_phone_number);
        return objects;
    }
}
