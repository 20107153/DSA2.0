package org.example.demo.models;

public class Ingredient {

    String name;
    String textualDescription;
    Double ABV;

    public Ingredient(String name, String textual_description, Double ABV) {
        this.name = name;
        this.textualDescription = textual_description;
        this.ABV = ABV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextualDescription() {
        return textualDescription;
    }

    public void setTextualDescription(String textualDescription) {
        this.textualDescription = textualDescription;
    }

    public Double getABV() {
        return ABV;
    }

    public void setABV(Double ABV) {
        this.ABV = ABV;
    }

    @Override
    public String toString() {
        return //name is already called as key in entry
                ", " + textualDescription +
                ", ABV=" + ABV;
    }
}