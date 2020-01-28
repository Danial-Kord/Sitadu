package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Account {
    private String user;
    private String pass;
    private String first_name;
    private String last_name;
    private String melli_code;
    private String phone;
    private int age;
    private Address address;
    private ArrayList<Factor>factors;
    private boolean logedIn = false;

public boolean logIn(String user,String pass){

    String statement = SQLStatement.select("customer","*","user = \'"+user+"\' and pass = \'"+pass+"\'");
    try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
        PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
        //preparedStatement.setInt(1, Types.INTEGER);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            first_name = rs.getString("first_name");
            last_name = rs.getString("last_name");
            melli_code = rs.getString("melli_code");
            age = rs.getInt("age");
            phone = rs.getString("phone");
            int addressId = rs.getInt("address_id");
            logedIn = address.findAddress(addressId);
            return logedIn;
        }
        else {
            System.out.println("wrong user or pass!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;

}

public void logIn(){
logIn(user,pass);
}

public boolean signUp(){

    String statement = SQLStatement.select("customer","user","user =\'"+user+"\'");
    try {
        ResultSet rs = DBConnection.myExcuteQuery(statement);

        if (rs.next()) {
            System.out.println("user exists");
            return false;
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    PreparedStatement preparedStatement = null;
    try {
        ArrayList<Object> allDatas = allDatas();
        statement = SQLStatement.insert("customer",allDatas.size());
        preparedStatement = DBConnection.connection.prepareStatement(statement);
        SQLTypeGenerator.setdata(preparedStatement,allDatas);
        return preparedStatement.execute();
    } catch (SQLException e) {
        e.printStackTrace();

    }
    return false;
}

    public void setAge(int age) {
        this.age = age;
    }

    public ArrayList<Object> allDatas(){
    ArrayList<Object>objects = new ArrayList<Object>();
        objects.add(user);
        objects.add(pass);
        objects.add(first_name);
        objects.add(last_name);
        objects.add(melli_code);
        objects.add(phone);
        objects.add(address.getId());
        objects.add(age);
        return objects;
    }

    public boolean findAllFactors(){
        if(!logedIn)
            return false;
        String statement = SQLStatement.select("facor","*","customer_id = \'"+user+"\'");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String total_price = rs.getString("total_price");
                Timestamp time = rs.getTimestamp("time");
                String name = rs.getString("name");
                Factor factor = new Factor(id,user,total_price,time,name);
                factor.findFoodsOfFactor();
                factors.add(factor);
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String getUser() {
        return user;
    }

    public boolean isLogedIn() {
        return logedIn;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getMelli_code() {
        return melli_code;
    }

    public void setMelli_code(String melli_code) {
        this.melli_code = melli_code;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }



    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public ArrayList<Factor> getFactors() {
        return factors;
    }

    public void setFactors(ArrayList<Factor> factors) {
        this.factors = factors;
    }
}

