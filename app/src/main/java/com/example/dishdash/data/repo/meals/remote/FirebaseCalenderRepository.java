package com.example.dishdash.data.repo.meals.remote;

import com.example.dishdash.data.model.meals.CalenderMeal;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.WriteBatch;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class FirebaseCalenderRepository {

    private final FirebaseFirestore firestore;

    public FirebaseCalenderRepository() {
        firestore = FirebaseFirestore.getInstance();
    }

    public void uploadCalenderMeals(
            String userId,
            List<CalenderMeal> meals,
            Runnable onSuccess,
            Consumer<String> onError
    ) {
        if (meals == null) {
            meals = new ArrayList<>();
        }

        List<CalenderMeal> finalMeals = meals;
        firestore.collection("users")
                .document(userId)
                .collection("calender_meals")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    WriteBatch batch = firestore.batch();

                    for (var doc : querySnapshot.getDocuments()) {
                        batch.delete(doc.getReference());
                    }

                    for (CalenderMeal meal : finalMeals) {
                        DocumentReference ref = firestore.collection("users")
                                .document(userId)
                                .collection("calender_meals")
                                .document(meal.getIdMeal());
                        batch.set(ref, meal);
                    }

                    batch.commit()
                            .addOnSuccessListener(unused -> onSuccess.run())
                            .addOnFailureListener(e -> onError.accept(e.getMessage()));
                })
                .addOnFailureListener(e -> onError.accept(e.getMessage()));
    }


    public void downloadCalenderMeals(
            String userId,
            Consumer<List<CalenderMeal>> onSuccess,
            Consumer<String> onError
    ) {
        firestore.collection("users")
                .document(userId)
                .collection("calender_meals")
                .get()
                .addOnSuccessListener((QuerySnapshot querySnapshot) -> {
                    List<CalenderMeal> meals = new ArrayList<>();
                    for (var doc : querySnapshot.getDocuments()) {
                        CalenderMeal meal = doc.toObject(CalenderMeal.class);
                        if (meal != null) {
                            meals.add(meal);
                        }
                    }
                    onSuccess.accept(meals);
                })
                .addOnFailureListener(e -> onError.accept(e.getMessage()));
    }
}
