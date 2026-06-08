package com.example.dishdash.auth.data.data_source.remote_data_source;

import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AuthRemoteDataSource implements AuthDataSource {

    private final FirebaseAuth auth;
    private final FirebaseFirestore firestore;

    public AuthRemoteDataSource() {
        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
    }

    @Override
    public void signUp(String email, String password, String firstName, String lastName, AuthCallback callback) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String userId = authResult.getUser().getUid();
                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("email", email);
                    userMap.put("first_name", firstName);
                    userMap.put("last_name", lastName);

                    firestore.collection("users")
                            .document(userId)
                            .set(userMap)
                            .addOnSuccessListener(unused -> callback.onSuccess(userId))
                            .addOnFailureListener(e -> callback.onError(e.getMessage()));
                })
                .addOnFailureListener(e -> callback.onError(e.getMessage()));
    }

    @Override
    public void signIn(String email, String password, AuthCallback callback) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();
                        callback.onSuccess(uid);
                    } else {
                        callback.onError(task.getException().getMessage());
                    }
                });
    }

    @Override
    public void signInWithGoogle(AuthCredential credential, AuthCallback callback) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String uid = task.getResult().getUser().getUid();

                        callback.onSuccess(uid);
                    } else {
                        callback.onError(task.getException().getMessage());
                    }
                });
    }


    public void getUserProfile(String userId, UserProfileCallback callback) {
        firestore.collection("users")
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {

                    if (!documentSnapshot.exists()) {
                        callback.onError("User not found");
                        return;
                    }

                    String email = documentSnapshot.getString("email");
                    String firstName = documentSnapshot.getString("first_name");
                    String lastName = documentSnapshot.getString("last_name");

                    callback.onSuccess(email, firstName, lastName);
                })
                .addOnFailureListener(e ->
                        callback.onError(e.getMessage())
                );
    }

}
