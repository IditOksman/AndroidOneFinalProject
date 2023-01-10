package com.hit.androidonefinalproject.api;

import com.hit.androidonefinalproject.model.UnfilteredGameModel;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.GET;

public interface GamesApi {
    @GET("games")
    Call<ArrayList<UnfilteredGameModel>> getGames();
}
