package com.company;

import java.util.ArrayList;

public class SQLStatement {
    public static String insert(String table, ArrayList<String> values1){
        String values =values1.get(0);
        for (int i=1;i<values1.size();i++){
            values += "," +values1.get(i);
        }
        return "insert into "+table+" values("+values+")";
    }
    public static String insert(String table, int size){
        String values ="?";
        for (int i=1;i<size;i++){
            values += ", ?";
        }
        return "insert into "+table+" values("+values+")";
    }
    public static String select(String table, ArrayList<String> values1,String cond){
        String values =values1.get(0);
        for (int i=1;i<values1.size();i++){
            values += " " +values1.get(i);
        }
        return "select " + values +" from "+table +" where "+cond;
    }
    public static String select(String table, ArrayList<String> values1){
        String values =values1.get(0);
        for (int i=1;i<values1.size();i++){
            values += " " +values1.get(i);
        }
        return "select " + values +" from "+table ;
    }
}
