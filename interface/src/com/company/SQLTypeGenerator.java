package com.company;
import java.sql.*;
import java.util.ArrayList;

public class SQLTypeGenerator {


    public static PreparedStatement setdata(PreparedStatement preparedStatement, ArrayList<Object>objects){
        try {
            Object object;
            for (int i = 0; i < objects.size(); i++) {
                object = objects.get(i);
                if (object == null) {
                    preparedStatement.setNull(i+1, Types.NULL);
                } else if (object instanceof String) {
                    preparedStatement.setString(i+1,(String)object);
                }
                else if (object instanceof java.util.Date) {
                    long me = ((java.util.Date)object).getTime();
                    preparedStatement.setDate(i+1,new Date(me));
                }
                else if (object instanceof Float) {
                    preparedStatement.setFloat(i+1, (float) object);
                }
                else if (object instanceof Integer) {
                    preparedStatement.setFloat(i+1,(int)object);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return preparedStatement;
    }

    public static void getData(ResultSet resultSet, ArrayList<Object>objects){
        try {
            Object object;
            for (int i = 0; i < objects.size(); i++) {
                object = objects.get(i);
                 if (object instanceof String) {
                   objects.set(i,resultSet.getString(i+1));
                }
                else if (object instanceof java.util.Date) {
                    objects.set(i,resultSet.getDate(i+1));

                }
                else if (object instanceof Float) {
                    objects.set(i,resultSet.getFloat(i+1));
                }
                else if (object instanceof Integer) {
                    objects.set(i,resultSet.getInt(i+1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
