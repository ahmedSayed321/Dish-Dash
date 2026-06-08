package com.example.dishdash.data.model.meals;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class IngredientConverter {

    private static final Gson gson = new Gson();

    @TypeConverter
    public static String fromIngredientList(List<Ingredient> list) {
        return gson.toJson(list);
    }

    @TypeConverter
    public static List<Ingredient> toIngredientList(String json) {
        Type type = new TypeToken<List<Ingredient>>() {
        }.getType();
        return gson.fromJson(json, type);
    }
}
