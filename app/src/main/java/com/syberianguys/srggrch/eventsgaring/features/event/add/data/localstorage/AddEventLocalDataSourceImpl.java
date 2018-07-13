package com.syberianguys.srggrch.eventsgaring.features.event.add.data.localstorage;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.syberianguys.srggrch.eventsgaring.R;
import com.syberianguys.srggrch.eventsgaring.features.core.events.model.User;

public class AddEventLocalDataSourceImpl implements AddEventLocalDataSource {

    private SharedPreferences sharedPreferences;

    public AddEventLocalDataSourceImpl(Context context) {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.auth_settings_key),
                Context.MODE_PRIVATE);
    }

    @Override
    public User getUser() {
        Gson gson = new Gson();
        String json = getString("userData");
        User user = gson.fromJson(json, User.class);

        return user;
    }

    @Override
    public String getString(String key) {
        return sharedPreferences.getString(key, null);
    }
}