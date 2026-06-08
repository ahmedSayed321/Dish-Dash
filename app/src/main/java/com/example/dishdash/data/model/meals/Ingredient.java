package com.example.dishdash.data.model.meals;

import androidx.room.Ignore;

public class Ingredient {
    public String name;
    public String measure;


    @Ignore
    public Ingredient() {
    }

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