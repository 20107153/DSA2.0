package org.example.demo.models;

import java.io.Serializable;

public class Ingredient implements Serializable {

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

    public String getTextualDescription() {
        return textualDescription;
    }

    public Double getABV() {
        return ABV;
    }


    @Override
    public String toString() {
        return //name is already called as key in entry
                ", " + textualDescription +
                ", ABV=" + ABV;
    }
}