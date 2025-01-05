package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import org.example.demo.controllers.BeveragesController;
import org.example.demo.models.*;
import org.example.demo.controllers.MyHashMap;
import org.example.demo.models.Ingredient;
import javafx.scene.control.Dialog;
import javafx.scene.control.Button;
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
    @FXML
    private TextField recipeNameField, recipeDescriptionField, addIngredientToRecipe;
    @FXML
    private TextArea listRecipeIngredients;
    @FXML
    private TextArea recipeTextArea;
    @FXML
    private TextField removeRecipeField;
    @FXML
    private FlowPane drinksFlowPane;
    private MyHashMap<String, Button> drinkButtonsHashMap = new MyHashMap<>();


    private void sortButtons(MyHashMap<String, Button> hashMap){
        Entry<String, Button> sortedHead = hashMap.sort();

        drinksFlowPane.getChildren().clear();

        Entry<String, Button> current = sortedHead;
        while (current != null){
            drinksFlowPane.getChildren().add(current.getValue());
            current = current.getNext();
        }
    }


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

        Button drinkButton = new Button(name);
        drinkButton.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        drinkButton.setOnAction(event -> {
            Drink drink = beverages.getDrink(name);
            if (drink != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Drink Information");
                alert.setHeaderText(drink.getName());
                alert.setContentText(
                        "Origin: " + drink.getPlaceOfOrigin() + "\n" +
                                "Description: " + drink.getTextualDescription() + "\n" +
                                "Image: " + drink.getImage()
                );
                alert.showAndWait();
            }
        });
        drinkButtonsHashMap.put(name, drinkButton);
        sortButtons(drinkButtonsHashMap);
        drinksFlowPane.getChildren().add(drinkButton);
    }

    public void removeDrinkJfx(ActionEvent e){
        String drinkToRemove = drinkRemoveField.getText();
        beverages.removeDrink(drinkToRemove);

        Button buttonToRemove = drinkButtonsHashMap.get(drinkToRemove);
        if (buttonToRemove != null){
            drinksFlowPane.getChildren().remove(buttonToRemove);
            drinkButtonsHashMap.remove(drinkToRemove);
        }
        sortButtons(drinkButtonsHashMap);
    }

    public void sortDrinksJfx(ActionEvent e){
        drinksTextArea.setText(beverages.sortDrinksAlphabetically());
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
        } catch (IndexOutOfBoundsException err){
            System.out.println("Invalid abv entered, must be double");
        }
    }

    public void removeIngredientJfx(ActionEvent e){
        String ingredientToRemove = ingredientsRemoveField.getText();
        beverages.removeIngredient(ingredientToRemove);
        ingredientsTextArea.setText(beverages.listIngredients());
    }

    public void saveIngredientsJfx(ActionEvent e) {
        String filename = "ingredients.dat";

        try {
            beverages.saveIngredient(beverages.ingredientsHashMap, filename);
            System.out.println("Ingredient saved successfully to " + filename);
        } catch (Exception err) {
            System.out.println("Error saving drink to " + filename);
        }
    }

    public void loadIngredientsJfx(ActionEvent e) {
        String filename = "ingredients.dat"; // Hardcoded filename

        // Attempt to load the drinks from the file
        MyHashMap<String, Ingredient> listIngredients = beverages.loadIngredient(filename);
        System.out.println(beverages.loadIngredient(filename));

        if (listIngredients != null) {
            // Update the UI or internal state with the loaded drinks
            ingredientsTextArea.setText(beverages.listIngredients());

            // Show success message
            System.out.println("Drink loaded successfully from " + filename);
        } else {
            // If loading failed, show an error message
            System.out.println("Error loading drinks from file.");
        }
        ingredientsTextArea.setText(beverages.listIngredients());
    }

    public void sortIngredientsJfx(ActionEvent e){
        ingredientsTextArea.setText(beverages.sortIngredientsAlphabetically());
    }

    /**
     * RECIPIES METHODS
     */
    private Entry<String, Ingredient> recipeListHead = null;

    public void addToRecipeList(ActionEvent e){
        if (beverages.getIngredient(addIngredientToRecipe.getText())!=null){
            Entry<String,Ingredient> newItem = beverages.getIngredient(addIngredientToRecipe.getText());
            newItem.setNext(null);

            Entry<String, Ingredient> checkSimilarEntry = recipeListHead;
            while (checkSimilarEntry != null) {
                if (checkSimilarEntry.getKey().equals(newItem.getKey())) {
                    System.out.println("Item already exists in the recipe list: " + newItem);
                    return; // Exit the method without adding the item
                }
                checkSimilarEntry = checkSimilarEntry.getNext();
            }

            // Create a new copy of the item to avoid shared references
            Entry<String, Ingredient> newEntry = new Entry<>(newItem.getKey(), newItem.getValue());
            newEntry.setNext(null); // Ensure the new item's next is null

            // Add the new item to the recipe list
            if (recipeListHead==null){
                recipeListHead = newItem;
                System.out.println("adding new item as head   "+newItem);
            } else {
                Entry<String, Ingredient> currentEntry= recipeListHead;
                while (currentEntry.getNext()!= null){
                    currentEntry=currentEntry.getNext();
                }
                System.out.println("setting last item as new item");
                currentEntry.setNext(newItem);
            }
            System.out.println(recipeListHead);
            listRecipeIngredients.setText(recipeListHead.toString());
        } else {
            System.out.println("Ingredient not found");
        }
    }

    public void clearRecipelist(ActionEvent e){
        recipeListHead = null;

        System.out.println(recipeListHead);
    }

    public void addRecipeJFX(ActionEvent e){
        String name = recipeNameField.getText();
        String desc = recipeDescriptionField.getText();
        System.out.println(recipeListHead);
        beverages.addRecipe(name,desc,recipeListHead);
        recipeTextArea.setText(beverages.listRecipes());
    }

    public void listRecipiesJfx(ActionEvent e){
        recipeTextArea.setText(beverages.listRecipes());
    }


    public void removeRecipeJfx(ActionEvent e){
        String st = removeRecipeField.getText();
        beverages.removeRecipe(st);
        recipeTextArea.setText(beverages.listRecipes());
    }
    @FXML
    private void sortRecipesJfx(ActionEvent e){
        recipeTextArea.setText(beverages.sortRecipesAlphabetically());
    }

    @FXML
    public void saveRecipesJfx(ActionEvent e) {
        String filename = "recipes.dat";

        try {
            beverages.saveRecipe(beverages.recipeHashMap, filename);
            System.out.println("Recipes saved successfully to " + filename);
        } catch (Exception err) {
            System.out.println("Error saving recipe to " + filename);
        }
    }

    @FXML
    public void loadRecipesJfx(ActionEvent e) {
        String filename = "recipes.dat"; // Hardcoded filename

        // Attempt to load the drinks from the file
        MyHashMap<String, Recipe> listRecipes = beverages.loadRecipe(filename);
        System.out.println(beverages.loadRecipe(filename));

        if (listRecipes != null) {
            // Update the UI or internal state with the loaded drinks
            recipeTextArea.setText(beverages.listRecipes());

            // Show success message
            System.out.println("Recipe loaded successfully from " + filename);
        } else {
            // If loading failed, show an error message
            System.out.println("Error loading Recipes from file.");
        }
        recipeTextArea.setText(beverages.listRecipes());
    }



    /**
     * SEARCH METHODS
     */

    public void searchDrinksJfx(ActionEvent e){
        String searchTerm = searchTextField.getText();
        resultsTextArea.setText(beverages.searchDrinksByName(searchTerm));
    }

    public void searchIngredientsJfx(ActionEvent e){
        String searchTerm = searchTextField.getText();
        resultsTextArea.setText(beverages.searchIngredientssByName(searchTerm));
    }


}