package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.controllers.BeveragesController;

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

    public void saveDrinksJfx(ActionEvent e){
        try{
            beverages.saveDrink();
            drinksTextArea.setText(beverages.listDrinks());
        } catch (Exception err){
            System.out.println(err);
        }
    }

    public void loadDrinksJfx(ActionEvent e){
        try {
            beverages.loadDrink();
        } catch (Exception err){
            System.out.println(err);
        }
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






}