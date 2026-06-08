package com.example.dishdash.data.model.meals;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.List;

@Entity(tableName = "favorite_meals")
@TypeConverters(IngredientConverter.class)
public class FavoriteMealEntity {

    @PrimaryKey
    @NonNull
    public String id;

    @NonNull
    public String userId;

    public String name;
    public String thumbnail;
    public String category;
    public String area;
    public String instructions;
    public String youtubeLink;

    public List<Ingredient> ingredients;

    @Ignore
    public FavoriteMealEntity() {
    }

    public FavoriteMealEntity(
            @NonNull String id,
            @NonNull String userId,
            String name,
            String thumbnail,
            String category,
            String area,
            String instructions,
            String youtubeLink,
            List<Ingredient> ingredients
    ) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.thumbnail = thumbnail;
        this.category = category;
        this.area = area;
        this.instructions = instructions;
        this.youtubeLink = youtubeLink;
        this.ingredients = ingredients;
    }
}
