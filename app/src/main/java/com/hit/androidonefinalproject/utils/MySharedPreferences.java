package com.hit.androidonefinalproject.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.hit.androidonefinalproject.model.FilteredGameModel;
import com.hit.androidonefinalproject.model.UserData;

public class MySharedPreferences implements IMySharedPreferences {

    SharedPreferences preferences;
    Gson gson;
    private final String PREFS_NAME = "data";
    private final String USER_DATA_NAME = "user";

    public MySharedPreferences(Context ctx) {
        preferences = ctx.getSharedPreferences(
                PREFS_NAME,
                Context.MODE_PRIVATE
        );
        gson = new Gson();
    }

    public void setGamesDataToLocalStorage(FilteredGameModel games) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(
                PREFS_NAME,
                gson.toJson(games)
        );
        editor.apply();
    }

    public FilteredGameModel getGamesDataFromLocalStorage() {
        String data = preferences.getString(
                PREFS_NAME,
                null
        );
        return gson.fromJson(data, FilteredGameModel.class);

    }

    public void setUserData(UserData user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(
                USER_DATA_NAME,
                gson.toJson(user)
        );
        editor.apply();
    }

    public UserData getUserData() {
        String data = preferences.getString(
                USER_DATA_NAME,
                null
        );
        return gson.fromJson(data, UserData.class);
    }
}
