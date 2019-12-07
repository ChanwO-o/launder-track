package com.parkchanwoo.laundrytracker;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class LaundryViewModel extends ViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<ArrayList<Wardrobe>> wardrobesLiveData;

	public MutableLiveData<ArrayList<Wardrobe>> getWardrobesLiveData() {
		Log.i(TAG, "getWardrobesLiveData()");
		if (wardrobesLiveData == null) {
			wardrobesLiveData = new MutableLiveData<>();
			initializeWardrobesLiveData();
		}
		return wardrobesLiveData;
	}

	private void initializeWardrobesLiveData() {
		Log.i(TAG, "initializeWardrobesLiveData()");
		wardrobesLiveData.setValue(new ArrayList<Wardrobe>());
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "LaundryViewModel destroyed");
	}
}
