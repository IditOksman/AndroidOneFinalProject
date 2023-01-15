package com.hit.androidonefinalproject.views.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.FilteredGameModel;
import com.hit.androidonefinalproject.repository.NetworkRepository;


public class GamesFragmentViewModel extends ViewModel {

    NetworkRepository repo;
    MutableLiveData<FilteredGameModel> data = new MutableLiveData<>();

    public GamesFragmentViewModel() {
        repo = new NetworkRepository();
    }

    public void getGames() {
        repo.getGames();
        repo.getAppData().observeForever(games -> {
            FilteredGameModel filteredGames = new FilteredGameModel(games);
            data.postValue(filteredGames);
        });
    }

    public LiveData<FilteredGameModel> getData() {
        return data;
    }

}
