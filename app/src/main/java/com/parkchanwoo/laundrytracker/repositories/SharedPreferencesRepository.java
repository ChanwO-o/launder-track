package com.parkchanwoo.laundrytracker.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

public class SharedPreferencesRepository {

	private Context appContext;
	private SharedPreferences preferences;

	public SharedPreferencesRepository(Context context) {
		appContext = context.getApplicationContext();
		preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
	}

	public void writeLaundry() {

	}

	private String readString(String key) {
		return preferences.getString(key, null);
	}

	private void writeString(String key, String value) {
		preferences.edit().putString(key, value).apply();
	}
}
