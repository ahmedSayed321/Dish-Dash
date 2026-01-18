package com.example.dishdash.auth.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.auth.presentation.presenter.AuthPresenter;
import com.example.dishdash.auth.presentation.presenter.AuthPresenterImpl;

public class SignUpActivity extends AppCompatActivity implements AuthView {

    ImageView backBtn;
    EditText email ,firstName , lastName , password , confirmPassword;
    Button signUpBtn;

    AuthPresenter authPresenter;
    LottieAnimationView loadingAnimation;




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
        loadingAnimation = findViewById(R.id.loadingAnimation);


        authPresenter = new AuthPresenterImpl(this);



        signUpBtn.setOnClickListener(v -> {

            String userEmail = email.getText().toString().trim();
            String userPassword = password.getText().toString().trim();
            String userConfirmPassword = confirmPassword.getText().toString().trim();
            String fName = firstName.getText().toString().trim();
            String lName = lastName.getText().toString().trim();

            authPresenter.signUp(userEmail,userPassword,userConfirmPassword,fName,lName);


        });



        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }


    @Override
    public void showLoading() {
        loadingAnimation.setVisibility(View.VISIBLE);
        loadingAnimation.playAnimation();
    }

    @Override
    public void hideLoading() {
     loadingAnimation.cancelAnimation();
     loadingAnimation.setVisibility(View.GONE);
    }

    @Override
    public void showEmailError(String message) {
     email.setError(message);
    }

    @Override
    public void showPasswordError(String message) {
       password.setError(message);
    }

    @Override
    public void showConfirmPasswordError(String message) {

        confirmPassword.setError(message);
    }

    @Override
    public void showFirstNameError(String message) {
        firstName.setError(message);
    }

    @Override
    public void showLastNameError(String message) {
        lastName.setError(message);
    }

    @Override
    public void onAuthSuccess() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAuthError(String message) {
        email.setError(message);
    }
}