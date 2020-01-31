package com.company;

import GUI.AttentionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Sitadu {
    private ArrayList<Food> menu;
    private Account account;
    private ArrayList<Peyk>peyks;
    private ArrayList<Address>addresses;
    private ArrayList<Factor>factors;
    private ArrayList<RawMaterial>raw_material;



    public Sitadu(){
        DBConnection.connect();
        findAllFoods();
        findAllPeyks();
        findAllFactors();
        account = new Account();

    }


    public ArrayList<Food> getMenu() {
        findAllFoods();
        return menu;
    }

    public ArrayList<RawMaterial> getRaw_material() {
        findAllRawMaterials();
        return raw_material;
    }

    public ArrayList<Peyk> getPeyks() {
        findAllPeyks();
        return peyks;
    }

    public ArrayList<Factor> getFactors() {
        findAllFactors();
        return factors;
    }

    public boolean findAllFactors(){
        factors = new ArrayList<Factor>();
        String statement = SQLStatement.select("factor","*");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String customer_id = rs.getString("customer_id");
                Timestamp time = rs.getTimestamp("time");
                String name = rs.getString("name");
                String total_price = rs.getString("total_price");
                factors.add(new Factor(id,customer_id,total_price,time,name));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

    public ArrayList<Account> getAllAccounts(){
        if(!account.isLogedIn() || !account.isAdmin()) {
            AttentionPane.Error("not loggd in or not admin");
            return null;
        }
        ArrayList<Account>accounts = new ArrayList<>();
        String statement = SQLStatement.select("customer","*");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String user = rs.getString("user");
                String pass = rs.getString("pass");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String melli_code = rs.getString("melli_code");
                String phone = rs.getString("phone");
                int age = rs.getInt("age");
                System.out.println(phone);
                System.out.println(first_name);
                System.out.println(last_name);
                accounts.add(new Account(user,pass,first_name,last_name,melli_code,phone,age));
            }
            return accounts;

        } catch (SQLException e) {
            AttentionPane.Error(e.getLocalizedMessage());

            e.printStackTrace();
        }
        return null;
    }
    public boolean delivary(){
        Factor factor = new Factor();
        if(!account.isLogedIn()) {
            factor.addNewFactorNoName();
            return false;
        }
        Peyk peyk = findBestPeyk();
        return factor.addNewFactor(account.getUser(),account.getFirst_name() + " " + account.getDefault_address(),peyk);
    }

    private boolean findAllPeyks(){
        peyks = new ArrayList<Peyk>();
        String statement = SQLStatement.select("peyk","*");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String phone = rs.getString("phone");
                peyks.add(new Peyk(id,first_name,last_name,phone));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

    public Account getAccount() {
        return account;
    }

    private boolean findAllFoods(){
        menu = new ArrayList<Food>();
        String statement = SQLStatement.select("menu","*");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                menu.add(new Food(id,name,price));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }
    private boolean findAllRawMaterials(){
        raw_material = new ArrayList<RawMaterial>();
        String statement = SQLStatement.select("raw_material","*");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");
                raw_material.add(new RawMaterial(id,name,price));
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return false;
    }

    public  ArrayList<Address> findAddress(){
        addresses = new ArrayList<Address>();
        String statement = SQLStatement.select("address","*");
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
                String customer_id = rs.getString("customer_id");
                addresses.add(new Address(id,name,address,house_phone_number,customer_id));

            }
            return addresses;
        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return addresses;
    }

    private Peyk findBestPeyk(){
        findAllPeyks();
        String statement = SQLStatement.select("factor","* count(*) as mcount",null,"group by peyk_id order by mcount asc");
        try {
//            ResultSet rs = DBConnection.myExcuteQuery(statement);
            PreparedStatement preparedStatement = DBConnection.connection.prepareStatement(statement);
            //preparedStatement.setInt(1, Types.INTEGER);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                for (int i=0;i<peyks.size();i++){
                    if(peyks.get(i).getId() == id){
                        return peyks.get(i);
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            AttentionPane.Error(e.getLocalizedMessage());

        }
        return null;
    }
}
