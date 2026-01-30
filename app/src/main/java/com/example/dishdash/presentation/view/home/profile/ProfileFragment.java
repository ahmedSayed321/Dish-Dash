package com.example.dishdash.presentation.view.home.profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dishdash.OfflineFragment;
import com.example.dishdash.R;
import com.example.dishdash.auth.data.data_source.local_data_source.AuthLocalDataSource;
import com.example.dishdash.auth.presentation.view.SignInActivity;
import com.example.dishdash.data.model.meals.CalenderMeal;
import com.example.dishdash.data.repo.meals.CalenderRepo;
import com.example.dishdash.data.repo.meals.local.FavouriteRepository;
import com.example.dishdash.presentation.presenter.profile.ProfilePresenter;
import com.example.dishdash.utilites.NetworkMonitor;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class ProfileFragment extends Fragment implements ProfileView {

    EditText userName, userEmail;
    private Button logoutButton;
    private FavouriteRepository favouriteRepository;
    private CalenderRepo calenderRepo;
    private AuthLocalDataSource authLocalDataSource;
    private ProgressDialog progressDialog;
    private ProfilePresenter presenter;
    private FrameLayout frameLayout;

    private NetworkMonitor networkMonitor;

    public ProfileFragment() {
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
        favouriteRepository = new FavouriteRepository(getContext());
        calenderRepo = new CalenderRepo(requireContext());

        frameLayout = view.findViewById(R.id.errorFragment);

        authLocalDataSource = new AuthLocalDataSource(getContext());
        userName = view.findViewById(R.id.profileEditUsername);
        userEmail = view.findViewById(R.id.profileEditEmail);
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Syncing your favorites...");
        progressDialog.setCancelable(false);
        presenter = new ProfilePresenter(this, requireContext());
        presenter.loadUserProfile();
        networkMonitor = new NetworkMonitor(requireContext());
        networkMonitor.observe(getViewLifecycleOwner(), isConnected -> {
            if (isConnected) {
                frameLayout.setVisibility(View.GONE);  // <-- crash here
            } else {
                frameLayout.setVisibility(View.VISIBLE);
                getChildFragmentManager()
                        .beginTransaction()
                        .replace(R.id.errorFragment, new OfflineFragment())
                        .commitAllowingStateLoss();
            }
        });
        logoutButton.setOnClickListener(v -> new AlertDialog.Builder(getContext())
                .setTitle("Confirm Logout")
                .setMessage("Are you sure you want to logout?")
                .setCancelable(true)
                .setPositiveButton("Yes", (dialog, which) -> logoutUser())
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .show());
    }


    private void logoutUser() {
        String userId = authLocalDataSource.getUserUid();
        progressDialog.show();

        favouriteRepository.syncFavoritesToFirebase(
                userId,
                () -> {
                    progressDialog.dismiss();
                    presenter.uploadLocalCalenderMeals(userId);
                    authLocalDataSource.clearUser();
                    favouriteRepository.deleteAllFav();
                    calenderRepo.deleteAllCalenderMeals();
                    Log.i("ProfileFragment", "logoutUser: All Cal Meals Deleted Successfully");
                    FirebaseAuth.getInstance().signOut();

                    Intent intent = new Intent(getContext(), SignInActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                },

                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(getContext(),
                            "Failed to sync favorites: " + error,
                            Toast.LENGTH_SHORT).show();
                }
        );
    }

    @Override
    public void showUserName(String fullName) {
        userName.setText(fullName);
    }

    @Override
    public void showUserEmail(String email) {
        userEmail.setText(email);
    }

    @Override
    public void showError(String message) {
        //MySnackBar.showError();
    }

    @Override
    public void showCalendarMeals(List<CalenderMeal> meals) {

    }

    @Override
    public void showLoading(boolean isLoading) {

    }
}
