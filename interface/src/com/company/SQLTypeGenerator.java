package com.company;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
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
}
