package com.company;

public class RawMaterial extends Materials{


    public RawMaterial(String name, int price) {
        super(name, price);
        setNewId("raw_material");
    }

    public boolean remove() {
        return SQLInstructions.remove("raw_material","id = \'" +id + "\'");
    }
    public boolean update(){
        String values = String.format("name  = \'%s\', total_price  = \'%s\'",name,price);
        return SQLInstructions.update("menu",values,"id = \'"+id +"\'");
    }
    public RawMaterial(int id, String name, int price) {
        super(id, name, price);
    }
}
