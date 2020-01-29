package com.company;



public class Food extends Materials{

    public Food(String name, int price) {
        super(name, price);
        setNewId("menu");
    }


    public boolean remove() {
       return SQLInstructions.remove("menu","id = \'" +id + "\'");
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', total_price  = \'%s\'",name,price);
        return SQLInstructions.update("menu",values,"id = \'"+id +"\'");
    }
    public Food(int id, String name, int price) {
        super(id, name, price);
    }
}
