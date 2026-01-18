package com.example.dishdash.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.ValidationUtils;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity {

    ImageView backBtn;
    EditText email ,firstName , lastName , password , confirmPassword;
    Button signUpBtn;
    FirebaseAuth auth;
    FirebaseFirestore firestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_up);

        backBtn = findViewById(R.id.backBtn);
        firstName = findViewById(R.id.editTextFirstName);
        lastName = findViewById(R.id.editTextLastName);
        password = findViewById(R.id.editTextPassword);
        confirmPassword = findViewById(R.id.editTextConfirmPassword);
        email = findViewById(R.id.editTextEmail);
        signUpBtn =findViewById(R.id.signupBtn);

        auth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();



        signUpBtn.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();
            String fName = firstName.getText().toString().trim();
            String lName = lastName.getText().toString().trim();

            if (!ValidationUtils.isValidEmail(userEmail)) {
                email.setError("Invalid Email");
                return;
            }

            if (!ValidationUtils.isValidPassword(userPassword)) {
                password.setError("Password too weak");
                return;
            }

            if (!ValidationUtils.isPasswordMatching(userPassword, userConfirmPassword)) {
                confirmPassword.setError("Passwords do not match");
                return;
            }

            createUser(userEmail, userPassword, fName, lName);
        });



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void createUser(String email, String password, String firstName, String lastName) {
        auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    String userId = auth.getCurrentUser().getUid();

                    Map<String, Object> userMap = new HashMap<>();
                    userMap.put("email", email);
                    userMap.put("first_name", firstName);
                    userMap.put("last_name", lastName);

                    firestore.collection("users")
                            .document(userId)
                            .set(userMap)
                            .addOnSuccessListener(unused -> {
                                Toast.makeText(this,
                                        "Signup Successful",
                                        Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(this, MainActivity.class));
                                finish();
                            })
                            .addOnFailureListener(e -> {
                                Toast.makeText(this,
                                        e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            });
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this,
                            e.getMessage(),
                            Toast.LENGTH_LONG).show();
                });
    }

}