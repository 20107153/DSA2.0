package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.controllers.BeveragesController;
import org.example.demo.controllers.MyHashMap;
import org.example.demo.models.Drink;

import java.net.URL;
import java.util.ResourceBundle;

public class EventController {
    private BeveragesController beverages = new BeveragesController();

    @FXML
    private TextArea drinksTextArea;
    @FXML
    private TextField drinkNameField, drinkOriginField, drinkDescriptionField, drinkImageField, drinkRemoveField;

    @FXML
    private TextArea ingredientsTextArea;
    @FXML
    private TextField ingredientsNameField, ingredientsDescriptionField, ingredientsAbvField, ingredientsRemoveField;

    @FXML
    private TextArea resultsTextArea;
    @FXML
    private TextField searchTextField;



    /**
     *DRINKS METHODS
     */
    public void listDrinksJfx(ActionEvent e){
        drinksTextArea.setText(beverages.listDrinks());
    }

    public void addDrinkJfx(ActionEvent e){
        String name = drinkNameField.getText();
        String origin = drinkOriginField.getText();
        String description = drinkDescriptionField.getText();
        String image = drinkImageField.getText();
        beverages.addDrink(name,origin,description,image);
        drinksTextArea.setText(beverages.listDrinks());
    }

    public void removeDrinkJfx(ActionEvent e){
        String drinkToRemove = drinkRemoveField.getText();
        beverages.removeDrink(drinkToRemove);
        drinksTextArea.setText(beverages.listDrinks());
    }

    public void saveDrinksJfx(ActionEvent e) {
        String filename = "drinks.dat";

        try {
            beverages.saveDrink(beverages.drinksHashMap, filename);
            System.out.println("Drink saved successfully to " + filename);
        } catch (Exception err) {
            System.out.println("Error saving drink to " + filename);
        }
    }

    public void loadDrinksJfx(ActionEvent e) {
        String filename = "drinks.dat"; // Hardcoded filename

        // Attempt to load the drinks from the file
        MyHashMap<String, Drink> listDrinks = beverages.loadDrink(filename);
        System.out.println(beverages.loadDrink(filename));

        if (listDrinks != null) {
            // Update the UI or internal state with the loaded drinks
            drinksTextArea.setText(beverages.listDrinks());

            // Show success message
            System.out.println("Drink loaded successfully from " + filename);
        } else {
            // If loading failed, show an error message
            System.out.println("Error loading drinks from file.");
        }
        drinksTextArea.setText(beverages.listDrinks());
    }

    public void sortDrinksJfx(ActionEvent e){
        drinksTextArea.setText(beverages.sortDrinksAlphabetically());
    }

    /**
     * INGREDIENTS METHODS
     */

    public void listIngredientsJfx(ActionEvent e){
        ingredientsTextArea.setText(beverages.listIngredients());
    }

    public void addIngredientJfx(ActionEvent e){
        try {
            String name = ingredientsNameField.getText();
            String description = ingredientsDescriptionField.getText();
            String abvText = ingredientsAbvField.getText();
            Double abv= Double.parseDouble(abvText);
            beverages.addIngredient(name,description,abv);
            ingredientsTextArea.setText(beverages.listIngredients());
        } catch (NumberFormatException err){
            System.out.println("Invalid abv entered, must be double");
        }
    }

    public void removeIngredientJfx(ActionEvent e){
        String ingredientToRemove = ingredientsRemoveField.getText();
        beverages.removeIngredient(ingredientToRemove);
        ingredientsTextArea.setText(beverages.listIngredients());
    }

    public void saveIngredientsJfx(ActionEvent e){
        try {
            beverages.saveIngredient();
            ingredientsTextArea.setText(beverages.listIngredients());
        } catch (Exception err){
            System.out.println(err);
        }
    }

    public void loadIngredientsJfx(ActionEvent e){
        try {
            beverages.loadIngredient();
            ingredientsTextArea.setText(beverages.listIngredients());
        } catch (Exception err){
            System.out.println(err);
        }
    }


    /**
     * SEARCH METHODS
     */

    public void searchDrinksJfx(ActionEvent e){
        String searchTerm =searchTextField.getText();
        resultsTextArea.setText(beverages.searchDrinksByName(searchTerm));
    }


}