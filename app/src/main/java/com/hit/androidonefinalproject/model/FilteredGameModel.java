package com.hit.androidonefinalproject.model;

import java.util.ArrayList;

public class FilteredGameModel {

    ArrayList<UnfilteredGameModel> games;

    public FilteredGameModel(ArrayList<UnfilteredGameModel> games) {
        this.games = games;
    }

    public ArrayList<UnfilteredGameModel> getData() {
        return games;
    }

    public void setData(ArrayList<UnfilteredGameModel> games) {
        this.games = games;
    }

}
