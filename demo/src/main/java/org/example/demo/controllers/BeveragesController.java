package org.example.demo.controllers;

import org.example.demo.models.Drink;
import org.example.demo.models.Ingredient;

public class BeveragesController {
    MyHashMap<String, Drink> drinksHashMap = new MyHashMap<>();
    MyHashMap<String, Ingredient> ingredientsHashMap= new MyHashMap<>();

    /**
     * DRINKS
     */
    public boolean addDrink(Drink drink) {
        return drinksHashMap.put(drink);
    }



    /**
     * INGREDIENTS
     */
    public boolean addIngredient(String ingredient){
        return ingredientsHashMap.put(ingredient);
    }
}
