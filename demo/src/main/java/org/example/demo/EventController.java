package org.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.demo.controllers.BeveragesController;
import org.example.demo.models.*;
import org.example.demo.controllers.MyHashMap;
import org.example.demo.models.Ingredient;
import javafx.scene.control.Dialog;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class EventController<Vbox> {
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
    @FXML
    private FlowPane ingredientsFlowPane;

    private MyHashMap<String, Button> drinkButtonsHashMap = new MyHashMap<>();
    private MyHashMap<String, Button> ingredientButtonsHashMap = new MyHashMap<>();

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

    private void makeDrinkButton(String name) {
        Button drinkButton = new Button(name);
        drinkButton.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        drinkButton.setOnAction(event -> {
            Drink drink = beverages.getDrink(name);
            if (drink != null) {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Drink Information");
                dialog.setHeaderText(drink.getName());

                VBox vbox = new VBox();
                vbox.setPrefWidth(300);
                vbox.getChildren().add(new javafx.scene.control.Label("Origin: " + drink.getPlaceOfOrigin()));
                vbox.getChildren().add(new javafx.scene.control.Label("Description: " + drink.getTextualDescription()));
                vbox.getChildren().add(new javafx.scene.control.Label("Image: " + drink.getImage()));

                Button deleteButton = new Button("Delete");
                deleteButton.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
                deleteButton.setOnAction(deleteEvent -> {
                    beverages.removeDrink(name);
                    drinkButtonsHashMap.remove(name);
                    drinksFlowPane.getChildren().remove(drinkButton);
                    dialog.close();
                });

                vbox.getChildren().add(deleteButton);

                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                dialog.showAndWait();
            }
        });
        drinkButtonsHashMap.put(name, drinkButton);
        sortButtons(drinkButtonsHashMap);
        drinksFlowPane.getChildren().add(drinkButton);
    }
    public void listDrinksJfx(ActionEvent e){
        drinksTextArea.setText(beverages.listDrinks());
    }

    public void addDrinkJfx(ActionEvent e){
        String name = drinkNameField.getText();
        String origin = drinkOriginField.getText();
        String description = drinkDescriptionField.getText();
        String image = drinkImageField.getText();

        beverages.addDrink(name,origin,description,image);

        makeDrinkButton(name);
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
        MyHashMap<String, Drink> loadHashDrinks = beverages.loadDrink(filename);
        System.out.println(beverages.loadDrink(filename));

        if (loadHashDrinks != null) {
            Entry<String, Drink> listHead = beverages.returnListSortedDrinks();
            Entry<String, Drink> currentEntry = listHead;
            while (currentEntry.getNext() != null) {
                String name = currentEntry.getKey();
                makeDrinkButton(name);
                currentEntry = currentEntry.getNext();
            }
            String name = currentEntry.getKey();
            makeDrinkButton(name);

            // Show success message
            System.out.println("Drink loaded successfully from " + filename);
        } else {
            // If loading failed, show an error message
            System.out.println("Error loading drinks from file.");
        }
    }




    /**
     * INGREDIENTS METHODS
     */
    private void makeIngredientButton(String name) {
        Button ingredientButton = new Button(name);
        ingredientButton.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");

        ingredientButton.setOnAction(event -> {
            Ingredient ingredient = beverages.getIngredient(name);
            if (ingredient != null) {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Ingredient Information");
                dialog.setHeaderText(ingredient.getName());

                VBox vbox = new VBox();
                vbox.setPrefWidth(300);
                vbox.getChildren().add(new javafx.scene.control.Label("Description: " + ingredient.getTextualDescription()));
                vbox.getChildren().add(new javafx.scene.control.Label("ABV: " + ingredient.getABV()));

                Button deleteButton = new Button("Delete");
                deleteButton.setStyle("-fx-padding: 10; -fx-background-color: #f0f0f0;");
                deleteButton.setOnAction(deleteEvent -> {
                    beverages.removeIngredient(name);
                    ingredientButtonsHashMap.remove(name);
                    ingredientsFlowPane.getChildren().remove(ingredientButton);
                    dialog.close();
                });

                vbox.getChildren().add(deleteButton);

                dialog.getDialogPane().setContent(vbox);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);
                dialog.showAndWait();
            }
        });
        ingredientButtonsHashMap.put(name, ingredientButton);
        sortButtons(ingredientButtonsHashMap);
        ingredientsFlowPane.getChildren().add(ingredientButton);
    }

    public void listIngredientsJfx(ActionEvent e){
        ingredientsTextArea.setText(beverages.listIngredients());
    }

    public void addIngredientJfx(ActionEvent e){
        String name = ingredientsNameField.getText();
        String description = ingredientsDescriptionField.getText();
        Double ABV = Double.valueOf(ingredientsAbvField.getText());

        beverages.addIngredient(name,description,ABV);
        makeIngredientButton(name);
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
            Entry<String,Ingredient> newItem = beverages.getIngredientMap(addIngredientToRecipe.getText());
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