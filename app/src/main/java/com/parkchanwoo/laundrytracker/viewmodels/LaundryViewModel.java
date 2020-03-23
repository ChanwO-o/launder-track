package com.parkchanwoo.laundrytracker.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parkchanwoo.laundrytracker.models.ClothItem;
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

	public LiveData<ArrayList<ClothItem>> getClothItemsLiveData() {
		return objectFileRepository.getClothItemsLiveData();
	}

	public void addNewClothItem(ClothItem clothItem) {
		objectFileRepository.addNewClothItem(clothItem);
	}

	public void updateClothItem(ClothItem clothItem) {
		objectFileRepository.updateClothItem(clothItem);
	}

	public void deleteClothItem(String clothItemId) {
		objectFileRepository.deleteClothItem(clothItemId);
	}
}
