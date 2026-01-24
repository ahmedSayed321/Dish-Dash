package com.example.dishdash.data.model.meals;

public class Ingredient {
    public final String name;
    public final String measure;

    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public String getMeasure() {
        return measure;
    }
}