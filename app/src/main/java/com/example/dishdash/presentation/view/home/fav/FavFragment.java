package com.example.dishdash.presentation.view.home.fav;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.data.model.meals.FavoriteMealEntity;
import com.example.dishdash.presentation.presenter.favourite.FavPresenter;

import java.util.List;

public class FavFragment extends Fragment implements FavView {

    private RecyclerView recyclerView;
    private FavAdapter adapter;
    private FavPresenter presenter;

    public FavFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.fragment_fav, container, false);
        presenter = new FavPresenter(
                this,
                new com.example.dishdash.data.repo.meals.local.FavouriteRepository(requireContext())
        );
        adapter = new FavAdapter(presenter);
        // RecyclerView
        recyclerView = view.findViewById(R.id.rvFavMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.getFavorites();
    }


    @Override
    public void showFavorites(List<FavoriteMealEntity> favorites) {
        adapter.setMeals(favorites);
    }

    @Override
    public void showEmpty() {
        adapter.setMeals(List.of());
    }

    @Override
    public void showError(String message) {
    }
}
