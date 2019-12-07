package com.parkchanwoo.laundrytracker;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class WardrobeViewModel extends ViewModel {
	private String TAG = this.getClass().getSimpleName();
	private MutableLiveData<ArrayList<ClothItem>> clothItemsLiveData;

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
