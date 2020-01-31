package com.company;



public class Food extends Materials{

    public Food(String name, int price) {
        super(name, price);
        setNewId("menu");
    }
    public Food() { super();}


    public boolean remove() {
       return SQLInstructions.remove("menu","id = \'" +id + "\'");
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', price  = \'%s\'",name,price);
        return SQLInstructions.update("menu",values,"id = \'"+id +"\'");
    }
    public void setnewFood(){
        updateDataBase("menu");
    }
    public Food(int id, String name, int price) {
        super(id, name, price);
    }
}
