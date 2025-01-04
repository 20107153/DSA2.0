package org.example.demo.models;

import java.io.Serializable;

public class Recipe implements Serializable {

    private String name;
    private String description;
    private Entry<String,Ingredient> headIngredient;


    public Recipe(String name, String description, Entry<String,Ingredient> headIngredient){
        this.name=name;
        this.description=description;
        this.headIngredient = headIngredient;
    }

    public Entry<String, Ingredient> getHeadIngredient() {
        return headIngredient;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //no need to add name to string the Entry<> already calls it as key
        StringBuilder str = new StringBuilder();
        str.append(description).append(", ");
        Entry<String,Ingredient> currentIngredient = headIngredient;
        while (currentIngredient.getNext()!=null){
            str.append(currentIngredient).append(", ");
        }
        return str.toString();
    }

}
