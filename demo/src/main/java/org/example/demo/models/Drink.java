package org.example.demo.models;

import java.io.Serializable;

public class Drink implements Serializable {

    String name;
    String placeOfOrigin;
    String textualDescription;
    String image;

    public Drink(String name, String placeOfOrigin, String textualDescription, String image) {
        this.name = name;
        this.placeOfOrigin = placeOfOrigin;
        this.textualDescription = textualDescription;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTextualDescription() {
        return textualDescription;
    }

    public void setTextualDescription(String textualDescription) {
        this.textualDescription = textualDescription;
    }

    public String getPlaceOfOrigin() {
        return placeOfOrigin;
    }

    public void setPlaceOfOrigin(String placeOfOrigin) {
        this.placeOfOrigin = placeOfOrigin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ", from: " + placeOfOrigin +
                ", Description: " + textualDescription +
                ", Image: " + image;
    }
}