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
        drinksHashMap.put(name, drink);
    }


    public void removeDrink(String drinkName) {
            drinksHashMap.remove(drinkName);
        System.out.println("Beverages remove"+ drinkName);
    }

    public String listDrinks(){
        return drinksHashMap.toString();
    }

    /**
     * INGREDIENTS
     */
    public void addIngredient(String name, String textualDescription, Double ABV) {
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

    public String listIngredients(){
        return ingredientsHashMap.toString();
    }


    /**
     * RECIPES
     */


}