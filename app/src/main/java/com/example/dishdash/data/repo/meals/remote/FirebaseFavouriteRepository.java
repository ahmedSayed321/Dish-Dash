package com.example.dishdash.data.repo.meals.remote;

import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FirebaseFavouriteRepository {

    private final FirebaseFirestore firestore;

    public FirebaseFavouriteRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void uploadFavorites(
            String userId,
            List<FavoriteMealEntity> meals,
            Runnable onSuccess,
            Consumer<String> onError
    ) {

        if (meals == null || meals.isEmpty()) {
            onSuccess.run();
            return;
        }

        WriteBatch batch = firestore.batch();

        for (FavoriteMealEntity meal : meals) {
            DocumentReference ref =
                    firestore.collection("users")
                            .document(userId)
                            .collection("favorite_meals")
                            .document(meal.id);

            batch.set(ref, meal);
        }

        batch.commit()
                .addOnSuccessListener(unused -> onSuccess.run())
                .addOnFailureListener(e -> onError.accept(e.getMessage()));
    }


    public void downloadFavorites(
            String userId,
            Consumer<List<FavoriteMealEntity>> onSuccess,
            Consumer<String> onError
    ) {
        firestore.collection("users")
                .document(userId)
                .collection("favorite_meals")
                .get()
                .addOnSuccessListener((QuerySnapshot querySnapshot) -> {
                    List<FavoriteMealEntity> meals = new ArrayList<>();
                    for (var doc : querySnapshot.getDocuments()) {
                        FavoriteMealEntity meal = doc.toObject(FavoriteMealEntity.class);
                        if (meal != null) {
                            meals.add(meal);
                        }
                    }
                    onSuccess.accept(meals);
                })
                .addOnFailureListener(e -> onError.accept(e.getMessage()));
    }
}
