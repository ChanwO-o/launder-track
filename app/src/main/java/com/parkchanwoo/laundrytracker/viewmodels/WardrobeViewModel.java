package com.parkchanwoo.laundrytracker.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.models.Wardrobe;
import com.parkchanwoo.laundrytracker.repositories.ObjectFileRepository;

public class WardrobeViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	ObjectFileRepository objectFileRepository;

	public WardrobeViewModel(@NonNull Application application) {
		super(application);
		objectFileRepository = new ObjectFileRepository(application);
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "WardrobeViewModel destroyed");
	}

	public LiveData<Wardrobe> getWardrobeLiveData() {
		return objectFileRepository.getWardrobeLiveData();
	}

	public void addNewClothItem(ClothItem clothItem) {
		objectFileRepository.addNewClothItem(clothItem);
	}
}
