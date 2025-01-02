package org.example.demo.models;

public class Ingredient<T> {

    String name;
    String textual_description;
    float ABV;

    public Ingredient(String name, String textual_description, float ABV) {
        this.name = name;
        this.textual_description = textual_description;
        this.ABV = ABV;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTextual_description() {
        return textual_description;
    }

    public void setTextual_description(String textual_description) {
        this.textual_description = textual_description;
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
                ", textual_description='" + textual_description + '\'' +
                ", ABV=" + ABV +
                '}';
    }
}