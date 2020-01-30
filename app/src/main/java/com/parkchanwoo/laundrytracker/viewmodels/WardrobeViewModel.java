package com.parkchanwoo.laundrytracker.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parkchanwoo.laundrytracker.models.ClothItem;

import java.util.ArrayList;

public class WardrobeViewModel extends AndroidViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<ArrayList<ClothItem>> clothItemsLiveData;

	public WardrobeViewModel(@NonNull Application application) {
		super(application);
	}

	public LiveData<ArrayList<ClothItem>> getClothItemsLiveData() {
		if (clothItemsLiveData == null) {
			clothItemsLiveData = new MutableLiveData<>();
			initializeClothItemsLiveData();
		}
		return clothItemsLiveData;
	}

	private void initializeClothItemsLiveData() {
		clothItemsLiveData.setValue(new ArrayList<ClothItem>());
	}

	@Override
	protected void onCleared() {
		super.onCleared();
		Log.i(TAG, "WardrobeViewModel destroyed");
	}

	public void addNewClothItem(ClothItem clothItem) {
		ArrayList<ClothItem> temp = clothItemsLiveData.getValue();
		temp.add(clothItem);
		clothItemsLiveData.setValue(temp);
	}
}
