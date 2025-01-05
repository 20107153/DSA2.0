package org.example.demo.controllers;

import java.io.*;

import org.example.demo.models.Drink;
import org.example.demo.models.Entry;
import org.example.demo.models.Ingredient;
import org.example.demo.models.Recipe;


public class BeveragesController {
    public MyHashMap<String, Drink> drinksHashMap = new MyHashMap<>();
    public MyHashMap<String, Ingredient> ingredientsHashMap = new MyHashMap<>();
    public MyHashMap<String, Recipe> recipeHashMap = new MyHashMap<>();

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

    public Entry<String, Drink> searchDrinksByName(String searchTerm){
        Entry<String,Drink> listHead = drinksHashMap.searchByName(searchTerm);
        return listHead;
    }


    public Entry<String, Drink> returnListSortedDrinks(){
        Entry<String,Drink> listHead = drinksHashMap.sort();
        return listHead;
    }

    public Entry<String, Ingredient> returnListSortedIngredients(){
        Entry<String,Ingredient> listHead = ingredientsHashMap.sort();
        return listHead;
    }

    public void saveDrink(MyHashMap<String, Drink> drinksHashMap, String filename){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(drinksHashMap);
            System.out.println("Drinks saved successfully to " + filename);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MyHashMap<String, Drink> loadDrink(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            drinksHashMap = (MyHashMap<String, Drink>) ois.readObject();
            System.out.println("Drinks loaded successfully from " + filename);
            return drinksHashMap;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
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


    public void saveIngredient(MyHashMap<String, Ingredient> ingredientsHashMap, String filename){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(ingredientsHashMap);
            System.out.println("Ingredients saved successfully to " + filename);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    public MyHashMap<String, Ingredient> loadIngredient(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            ingredientsHashMap = (MyHashMap<String, Ingredient>) ois.readObject();
            System.out.println("Ingredients loaded successfully from " + filename);
            return ingredientsHashMap;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Entry<String,Ingredient> searchIngredientssByName(String searchTerm){
        Entry<String,Ingredient> listHead = ingredientsHashMap.searchByName(searchTerm);
        return listHead;
    }

    public Entry<String,Ingredient> getIngredientMap(String searchTerm){
        return ingredientsHashMap.getEntry(searchTerm);
    }


    /**
     * RECIPES
     */

    public void addRecipe(String name, String description, Entry<String, Ingredient> headIngredient){
        Recipe addRecipe = new Recipe(name, description, headIngredient);
        recipeHashMap.put(addRecipe.getName(), addRecipe);
    }

    public void removeRecipe(String recipeName){
        recipeHashMap.remove(recipeName);
    }

    public String listRecipes(){
        return recipeHashMap.toString();
    }

    public String sortRecipesAlphabetically(){
        Entry<String,Recipe> listHead = recipeHashMap.sort();
        Entry<String,Recipe> current = listHead;
        String resultString = "";
        while (current.getNext()!=null){
            resultString+= current.thisString() + "\n";
            current=current.getNext();
        }
        resultString += current.thisString();
        return resultString;
    }

    public void saveRecipe(MyHashMap<String, Recipe> recipeHashMap, String filename){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(recipeHashMap);
            System.out.println("Recipes saved successfully to " + filename);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public MyHashMap<String, Recipe> loadRecipe(String filename) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            recipeHashMap = (MyHashMap<String, Recipe>) ois.readObject();
            System.out.println("Ingredients loaded successfully from " + filename);
            return recipeHashMap;
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Drink getDrink(String name){
        return drinksHashMap.get(name);
    }
    public Ingredient getIngredient(String name){
        return ingredientsHashMap.get(name);
    }
}