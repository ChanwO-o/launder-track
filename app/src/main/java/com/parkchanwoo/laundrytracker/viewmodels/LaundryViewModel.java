package com.parkchanwoo.laundrytracker.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parkchanwoo.laundrytracker.models.Wardrobe;

import java.util.ArrayList;

public class LaundryViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<ArrayList<Wardrobe>> wardrobesLiveData;
	private MutableLiveData<Wardrobe> wardrobeLiveData;

	public LaundryViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<ArrayList<Wardrobe>> getWardrobesLiveData() {
		Log.i(TAG, "getWardrobesLiveData()");
		if (wardrobesLiveData == null) {
			wardrobesLiveData = new MutableLiveData<>();
			initializeWardrobesLiveData();
		}
		return wardrobesLiveData;
	}

	private void initializeWardrobesLiveData() {
		wardrobesLiveData.setValue(new ArrayList<Wardrobe>());
	}

	public LiveData<Wardrobe> getWardrobeLiveData() {
		Log.i(TAG, "getWardrobeLiveData()");
		if (wardrobeLiveData == null) {
			wardrobeLiveData = new MutableLiveData<>();
			initializeWardrobeLiveData();
		}
		return wardrobeLiveData;
	}

	private void initializeWardrobeLiveData() {
		wardrobeLiveData.setValue(new Wardrobe());
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "LaundryViewModel destroyed");
	}

	public void addNewWardrobe(Wardrobe wardrobe) {
		ArrayList<Wardrobe> temp = wardrobesLiveData.getValue();
		temp.add(wardrobe);
		wardrobesLiveData.setValue(temp);
	}
}
