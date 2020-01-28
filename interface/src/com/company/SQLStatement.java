package com.company;

import java.util.ArrayList;

public class SQLStatement {
    public static String insert(String table, String  attributs){


        String values ="?";
        for (int i=1;i<attributs.split(",").length;i++){
            values += ", ?";
        }
        return "insert into "+table+"("+attributs+")"+" values("+values+")";
    }
    public static String insert(String table, int size){
        String values ="?";
        for (int i=1;i<size;i++){
            values += ", ?";
        }
        return "insert into "+table+" values("+values+")";
    }
    public static String select(String table,String values,String cond){
        return "select " + values +" from "+table +" where "+cond;
    }
    public static String select(String table, String values){
        return "select " + values +" from "+table ;
    }
}
