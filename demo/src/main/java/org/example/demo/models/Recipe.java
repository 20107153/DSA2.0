package org.example.demo.models;

public class Recipe<K,V> {
    private String name;
    private String description;
    private Entry<K,V> headIngredient;


    public Recipe(String name, String description, Entry<K,V> headIngredient){
        this.name=name;
        this.description=description;
        this.headIngredient = headIngredient;
    }

    public Entry<K, V> getHeadIngredient() {
        return headIngredient;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        //no need to add name to string the Entry<> already calls it as key
        StringBuilder str = new StringBuilder();
        str.append(description).append(", ");
        Entry<K,V> currentIngredient = headIngredient;
        while (currentIngredient.getNext()!=null){
            str.append(currentIngredient).append(", ");
        }
        return str.toString();
    }

}
