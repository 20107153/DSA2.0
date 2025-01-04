package org.example.demo.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.example.demo.models.Drink;
import org.example.demo.models.Entry;
import org.example.demo.models.Ingredient;
import org.example.demo.models.Recipe;


public class BeveragesController {
    MyHashMap<String, Drink> drinksHashMap = new MyHashMap<>();
    MyHashMap<String, Ingredient> ingredientsHashMap = new MyHashMap<>();
    MyHashMap<String, Recipe> recipeHashMap = new MyHashMap<>();

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

    public void saveDrink() throws Exception
    {
        XStream xtream = new XStream(new DomDriver());
        ObjectOutputStream out =
                xtream.createObjectOutputStream(new FileWriter("drinks.xml"));
        out.writeObject(drinksHashMap);
        out.close();
    }

    public void loadDrink() throws Exception
    {
        Class<?>[] classes = new Class[] { Drink.class };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("drinks.xml"));
        drinksHashMap = (MyHashMap<String, Drink>) is.readObject();
        is.close();
    }

    public String searchDrinksByName(String searchTerm){
        Entry<String,Drink> listHead = drinksHashMap.searchByName(searchTerm);
        Entry<String,Drink> current = listHead;
        String resultString = "";
        while (current!=null){
            resultString+= current + "\n";
        }
        resultString += current;
        return resultString;
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

    //This save and load can be found in prog fund 2, part 5, XML and Java.
    //The arrayList has been replaced with hashMap, not sure if it will work
    public void saveIngredient() throws Exception
    {
        XStream xtream = new XStream(new DomDriver());
        ObjectOutputStream out =
                xtream.createObjectOutputStream(new FileWriter("ingredients.xml"));
        out.writeObject(ingredientsHashMap);
        out.close();
    }

    public void loadIngredient() throws Exception
    {
        Class<?>[] classes = new Class[] { Ingredient.class };

        XStream xstream = new XStream(new DomDriver());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(classes);

        ObjectInputStream is = xstream.createObjectInputStream(new FileReader("ingredients.xml"));
        ingredientsHashMap = (MyHashMap<String, Ingredient>) is.readObject();
        is.close();
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
}