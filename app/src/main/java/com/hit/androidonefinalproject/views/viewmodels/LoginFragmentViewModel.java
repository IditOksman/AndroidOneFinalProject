package com.hit.androidonefinalproject.views.viewmodels;
import androidx.lifecycle.ViewModel;

import com.hit.androidonefinalproject.model.UserData;
import com.hit.androidonefinalproject.utils.MySharedPreferences;

public class LoginFragmentViewModel extends ViewModel {

    MySharedPreferences preferences;

    public LoginFragmentViewModel(MySharedPreferences preferences) {
        this.preferences = preferences;
    }


    public Boolean isUserSaved(String email, String password) {

        UserData newUser = new UserData(email,password);
        UserData storedUser = preferences.getUserData();

        if(storedUser == null) return true;

        return newUser.getEmail() == storedUser.getEmail() &&
                newUser.getPassword() == storedUser.getPassword();

    }
}
