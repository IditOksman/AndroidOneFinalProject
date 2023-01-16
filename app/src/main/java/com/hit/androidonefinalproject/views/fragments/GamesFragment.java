package com.hit.androidonefinalproject.views.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.hit.androidonefinalproject.adapter.GamesAdapter;
import com.hit.androidonefinalproject.databinding.FragmentGamesBinding;
import com.hit.androidonefinalproject.model.GameModel;
import com.hit.androidonefinalproject.model.GamesWrapperModel;
import com.hit.androidonefinalproject.utils.MySharedPreferences;
import com.hit.androidonefinalproject.views.viewmodels.GamesFragmentViewModel;

import java.util.ArrayList;

public class GamesFragment extends Fragment implements GamesAdapter.GameOnClickListener {

    private FragmentGamesBinding binding;
    private GamesFragmentViewModel viewModel;
    private GamesAdapter adapter;
    private RecyclerView.SmoothScroller smoothScroller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGamesBinding.inflate(inflater, container, false);
        viewModel = new GamesFragmentViewModel(new MySharedPreferences(this.getContext()));
        adapter = new GamesAdapter(
                new GamesWrapperModel(new ArrayList<>()),
                this::onItemClick
        );
        binding.gamesRv.setAdapter(adapter);
        binding.gamesRv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        smoothScroller = new
                LinearSmoothScroller(this.getContext()) {
                    @Override
                    protected int getVerticalSnapPreference() {
                        return LinearSmoothScroller.SNAP_TO_START;
                    }
                };
        setListeners();
        setDropDownMenu();
        viewModel.getGames();

        return binding.getRoot();
    }

    private void setDropDownMenu() {
        String[] items = new String[]{"Genre", "Developer", "Name"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_spinner_dropdown_item, items);
        binding.spinner1.setAdapter(adapter);
    }

    private void setListeners() {
        viewModel.getData().observe(getViewLifecycleOwner(), games -> {
            adapter.setAdapterData(games);
        });

        binding.searchBtn.setOnClickListener(v -> {
            search();
        });
    }

    private void search() {

        String selectedDropDownOption = binding.spinner1.getSelectedItem().toString();
        ArrayList<GameModel> games = adapter.getAdapterData();
        String userInput = binding.searchEt.getText().toString();

        switch (selectedDropDownOption) {
            case "Name": {
                for(int i = 0; i < games.size(); i++) {
                    if(games.get(i).title.equals(userInput)) {
                        scrollToGame(i);
                    }
                }
                break;
            }
            case "Genre": {

                for(int i = 0; i < games.size(); i++) {
                    if(games.get(i).genre.equals(userInput)) {
                        scrollToGame(i);
                    }
                }
                break;
            }
            case "Developer": {

                for(int i = 0; i < games.size(); i++) {
                    if(games.get(i).developer.equals(userInput)) {
                        scrollToGame(i);
                    }
                }
            }
            break;
        }
    }

    private void scrollToGame(int gamePosition) {
        smoothScroller.setTargetPosition(gamePosition);
        binding.gamesRv.getLayoutManager().startSmoothScroll(smoothScroller);
    }

    @Override
    public void onItemClick(int position) {
        ArrayList<GameModel> games = adapter.getAdapterData();
        GameModel selectedGame = games.get(position);
        String url = selectedGame.game_url;

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }
}