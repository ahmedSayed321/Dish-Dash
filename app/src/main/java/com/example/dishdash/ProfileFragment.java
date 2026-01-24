package com.example.dishdash;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.auth.presentation.view.SignInActivity;
import com.google.firebase.auth.FirebaseAuth;


public class ProfileFragment extends Fragment {

    Button logoutButton;


    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        logoutButton = view.findViewById(R.id.logOutBtn);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(getContext())
                        .setTitle("Confirm Logout")
                        .setMessage("Are you sure you want to logout?")
                        .setCancelable(true)

                        .setPositiveButton("Yes", (dialog, which) -> {

                            // 1️⃣ مسح SharedPreferences
                            AuthLocalDataSource localDataSource =
                                    new AuthLocalDataSource(getContext());
                            localDataSource.clearUser();

                            FirebaseAuth.getInstance().signOut();

                            Intent intent =
                                    new Intent(getContext(), SignInActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        })

                        .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())

                        .show();
            }
        });
    }
}