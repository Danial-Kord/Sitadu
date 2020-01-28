package com.company;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

public class Sitadu {
    private ArrayList<Food> menu;
    private Account account;
    private ArrayList<Peyk>peyks ;



    public Sitadu(){
        findAllFoods();
        account = new Account();
    }
    public boolean delivary(Factor factor){
        if(!account.isLogedIn())
            return false;
        Peyk peyk = findBestPeyk();
        String statement = null;
        PreparedStatement preparedStatement = null;
        try {
            ArrayList<Object> allDatas = new ArrayList<Object>();
            allDatas.add(peyk.getId());
            allDatas.add(factor.getId());
            statement = SQLStatement.insert("delivery",allDatas.size());
            preparedStatement = DBConnection.connection.prepareStatement(statement);
            SQLTypeGenerator.setdata(preparedStatement,allDatas);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    private boolean findAllPeyks(){
        peyks = new ArrayList<Peyk>();
        String statement = SQLStatement.selectWithCond("peyk","*");
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
        }
        return false;
    }

    private boolean findAllFoods(){
        menu = new ArrayList<Food>();
        String statement = SQLStatement.selectWithCond("menu","*");
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
        }
        return false;
    }

    private Peyk findBestPeyk(){
        findAllPeyks();
        String statement = SQLStatement.selectWithCond("delivery","* count(*) as mcount",null,"group by peyk_id order by mcount asc");
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
        }
        return null;
    }
}