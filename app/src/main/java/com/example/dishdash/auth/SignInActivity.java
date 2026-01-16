package com.example.dishdash.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.dishdash.R;

public class SignInActivity extends AppCompatActivity {

    EditText email , password;
    Button loginBtn;
    LinearLayout googleLogin, guest;
    TextView signUpText;


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


        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });



    }
}