package org.example.demo.controllers;

import org.example.demo.models.Drink;
import org.example.demo.models.Ingredient;

public class BeveragesController {
    MyHashMap<String, Drink> drinksHashMap = new MyHashMap<>();
    MyHashMap<String, Ingredient> ingredientsHashMap = new MyHashMap<>();

    /**
     * DRINKS
     */
    public void addDrink(String name, String placeOfOrigin, String textualDescription, String image) {
        Drink drink = new Drink(name, placeOfOrigin, textualDescription, image);
        drinksHashMap.put(drink.getName(), drink);
    }


    public void removeDrink(String drinkName) {
        if (drinksHashMap == null) {
            System.out.println("There are no drinks to remove from the system");
        } else {
            drinksHashMap.remove(drinkName);
        }
    }


    /**
     * INGREDIENTS
     */
    public void addIngredient(String name, String textualDescription, int ABV) {
        Ingredient ingredient = new Ingredient(name, textualDescription, ABV);
        ingredientsHashMap.put(ingredient.getName(), ingredient);
    }

    public void removeIngredient(String ingredientName) {
        if (ingredientsHashMap == null) {
            System.out.println("There are no drinks to remove from the system");
        } else {
            ingredientsHashMap.remove(ingredientName);
        }
    }
}