package com.example.dishdash.data.model.meals;

public class MealMapper {

    public static FavoriteMealEntity mapToFavorite(Meal meal, String userId) {
        if (meal == null || meal.id == null) return null;

        return new FavoriteMealEntity(
                meal.id,
                userId,
                safe(meal.name),
                safe(meal.thumbnail),
                safe(meal.category),
                safe(meal.area),
                safe(meal.instructions),
                safe(meal.youtubeLink),
                meal.getIngredients()
        );
    }

    private static String safe(String value) {
        return value != null ? value.trim() : "";
    }
}

