package com.example.dishdash.data.model.meals;

public class MealToFavMapper {


    public static FavoriteMealEntity converterMealToFav(Meal meal, String userId) {
        return new FavoriteMealEntity(
                meal.id,
                userId,
                meal.name,
                meal.thumbnail,
                meal.category,
                meal.area,
                meal.instructions,
                meal.youtubeLink,
                meal.getIngredients()
        );
    }

//    public static Meal converterFavToMeal(FavoriteMealEntity favMeal) {
//        return new Meal(
//                favMeal.id,
//                favMeal.name,
//                favMeal.thumbnail,
//                favMeal.category,
//                favMeal.area,
//                favMeal.instructions,
//                favMeal.youtubeLink,
//                favMeal.ingredients
//        );
//    }
}
