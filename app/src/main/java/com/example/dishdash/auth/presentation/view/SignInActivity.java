package com.example.dishdash.auth.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.dishdash.MainActivity;
import com.example.dishdash.R;
import com.example.dishdash.auth.presentation.presenter.AuthPresenter;
import com.example.dishdash.auth.presentation.presenter.AuthPresenterImpl;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity implements AuthView {

    static final int RC_SIGN_IN = 100;
    EditText email, password;
    Button loginBtn;
    LinearLayout googleLogin, guest;
    TextView signUpText;
    AuthPresenter authPresenter;
    LottieAnimationView loadingAnimation;
    FirebaseAuth mAuth;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passEditText);
        loginBtn = findViewById(R.id.loginBtn);
        googleLogin = findViewById(R.id.googleLoginLayout);
        guest = findViewById(R.id.guestLoginLayout);
        signUpText = findViewById(R.id.signupTextBtn);

        loadingAnimation = findViewById(R.id.loadingAnimation2);
        authPresenter = new AuthPresenterImpl(this, SignInActivity.this);
        mAuth = FirebaseAuth.getInstance();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        googleLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = googleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authPresenter.signIn(email.getText().toString().trim(), password.getText().toString().trim());
                // signIn(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                authPresenter.googleSignIn(account.getIdToken());
                //firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                Toast.makeText(this, "Google Sign In Failed", Toast.LENGTH_SHORT).show();
            }
        }
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
        email.requestFocus();
    }

    @Override
    public void showPasswordError(String message) {
        password.setError(message);
        password.requestFocus();
    }

    @Override
    public void showConfirmPasswordError(String message) {

    }

    @Override
    public void showFirstNameError(String message) {

    }

    @Override
    public void showLastNameError(String message) {

    }

    @Override
    public void onAuthSuccess() {
        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void onAuthError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();


    }
}
