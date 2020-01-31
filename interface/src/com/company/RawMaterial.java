package com.company;

import GUI.AttentionPane;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RawMaterial extends Materials{

    private String market_id;

    public RawMaterial(int id, String name, int price, String market_id) {
        super(id, name, price);
        this.market_id = market_id;
    }

    public RawMaterial() {
        super();
    }
    public boolean remove() {
        return SQLInstructions.remove("raw_material","id = \'" +id + "\'");
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', price  = \'%s\'",name,price);
        return SQLInstructions.update("raw_material",values,"id = \'"+id +"\'");
    }

    @Override
    public boolean updateDataBase(String table) {
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
    public ArrayList<Object> allDatas(){
        ArrayList<Object> objects = new ArrayList<Object>();
        objects.add(id);
        objects.add(name);
        objects.add(price);
        objects.add(market_id);
        return objects;
    }

    @Override
    protected boolean setNewId(String table) {
        return super.setNewId(table);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    public String getMarket_id() {
        return market_id;
    }

    public void setMarket_id(String market_id) {
        this.market_id = market_id;
    }

    public void setnewFood(){
        updateDataBase("raw_material");
    }
    public RawMaterial(int id, String name, int price) {
        super(id, name, price);
    }
}
