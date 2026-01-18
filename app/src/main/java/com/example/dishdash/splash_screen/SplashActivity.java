package com.example.dishdash.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dishdash.R;
import com.example.dishdash.auth.presentation.view.SignInActivity;

public class SplashActivity extends AppCompatActivity {
    TextView splashText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        splashText = findViewById(R.id.textView);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        splashText.startAnimation(animation);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, SignInActivity.class);
            startActivity(intent);
            finish();
        }, 5000);

    }
}