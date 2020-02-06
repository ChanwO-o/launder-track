package com.parkchanwoo.laundrytracker.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parkchanwoo.laundrytracker.models.Wardrobe;
import com.parkchanwoo.laundrytracker.repositories.ObjectFileRepository;

import java.util.ArrayList;

public class LaundryViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	private ObjectFileRepository objectFileRepository;

	public LaundryViewModel(@NonNull Application application) {
		super(application);
		objectFileRepository = new ObjectFileRepository(application);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "LaundryViewModel destroyed");
	}

	public LiveData<ArrayList<Wardrobe>> getWardrobesLiveData() {
		return objectFileRepository.getWardrobesLiveData();
	}

	public void addNewWardrobe(Wardrobe wardrobe) {
		objectFileRepository.addNewWardrobe(wardrobe);
	}
}
