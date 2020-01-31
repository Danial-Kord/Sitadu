package com.company;

public class RawMaterial extends Materials{


    public RawMaterial(String name, int price) {
        super(name, price);
        setNewId("raw_material");
    }
    public RawMaterial() {
        super();
        setNewId("raw_material");
    }
    public boolean remove() {
        return SQLInstructions.remove("raw_material","id = \'" +id + "\'");
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', price  = \'%s\'",name,price);
        return SQLInstructions.update("menu",values,"id = \'"+id +"\'");
    }
    public void setnewFood(){
        updateDataBase("raw_material");
    }
    public RawMaterial(int id, String name, int price) {
        super(id, name, price);
    }
}
