package org.example.demo.models;

public class Ingredient<T> {

    String name;
    String textualDescription;
    float ABV;

    public Ingredient(String name, String textual_description, float ABV) {
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

    public float getABV() {
        return ABV;
    }

    public void setABV(float ABV) {
        this.ABV = ABV;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", textual_description='" + textualDescription + '\'' +
                ", ABV=" + ABV +
                '}';
    }
}