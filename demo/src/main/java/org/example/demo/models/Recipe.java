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

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(description + ", ");
        Entry<String, Ingredient> currentIngredient = headIngredient;

        // Iterate through the linked list of ingredients
        while (currentIngredient != null) { // Iterate until currentIngredient is null
            str.append(currentIngredient.thisString() + ", ");
            currentIngredient = currentIngredient.getNext(); // Move to the next ingredient
        }

        return str.toString();
    }

}
