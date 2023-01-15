package com.hit.androidonefinalproject.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.hit.androidonefinalproject.api.GamesApi;
import com.hit.androidonefinalproject.api.MyRetrofit;
import com.hit.androidonefinalproject.model.UnfilteredGameModel;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NetworkRepository implements INetworkRepository {

    Retrofit retrofit;
    MutableLiveData<ArrayList<UnfilteredGameModel>> appData = new MutableLiveData<>();

    public NetworkRepository() {
        retrofit = MyRetrofit.provideRetrofitInstance();
    }

    @Override
    public MutableLiveData<ArrayList<UnfilteredGameModel>> getGames() {
        GamesApi api = retrofit.create(GamesApi.class);

        Call<ArrayList<UnfilteredGameModel>> data = api.getGames();

        data.enqueue(new Callback<ArrayList<UnfilteredGameModel>>() {

            @Override
            public void onResponse(Call<ArrayList<UnfilteredGameModel>> call, Response<ArrayList<UnfilteredGameModel>> response) {
                if(response.code() == 200) {
                    appData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<UnfilteredGameModel>> call, Throwable t) {
                t.printStackTrace();
            }
        });

        return null;
    }

    public LiveData<ArrayList<UnfilteredGameModel>> getAppData() {
        return appData;
    }
}
