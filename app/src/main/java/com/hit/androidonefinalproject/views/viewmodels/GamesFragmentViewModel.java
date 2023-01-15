package com.hit.androidonefinalproject.views.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.GamesWrapperModel;
import com.hit.androidonefinalproject.repository.NetworkRepository;


public class GamesFragmentViewModel extends ViewModel {

    NetworkRepository repo;
    MutableLiveData<GamesWrapperModel> data = new MutableLiveData<>();

    public GamesFragmentViewModel() {
        repo = new NetworkRepository();
    }

    public void getGames() {
        repo.getGames();
        repo.getAppData().observeForever(games -> {
            GamesWrapperModel filteredGames = new GamesWrapperModel(games);
            data.postValue(filteredGames);
        });
    }

    public LiveData<GamesWrapperModel> getData() {
        return data;
    }

}
