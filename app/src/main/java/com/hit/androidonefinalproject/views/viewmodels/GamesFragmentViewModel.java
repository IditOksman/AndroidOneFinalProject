package com.hit.androidonefinalproject.views.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.FilteredGameModel;
import com.hit.androidonefinalproject.repository.NetworkRepository;


public class GamesFragmentViewModel extends ViewModel {

    NetworkRepository repo;
    public MutableLiveData<FilteredGameModel> data = new MutableLiveData<>();

    public GamesFragmentViewModel() {
        repo = new NetworkRepository();
    }

    public void getGames() {
        repo.getGames();
        repo.appData.observeForever(games -> {
            FilteredGameModel filteredGames = new FilteredGameModel(games);
            data.postValue(filteredGames);
        });
    }

}
