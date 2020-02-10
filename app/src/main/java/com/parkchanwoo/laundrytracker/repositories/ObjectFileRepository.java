package com.parkchanwoo.laundrytracker.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parkchanwoo.laundrytracker.models.ClothItem;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectFileRepository {
	private String TAG = this.getClass().getSimpleName();
	private Context appContext;
	private static final String LAUNDRYTRACKER_FILE_NAME = "laundrytracker.txt";
	private MutableLiveData<ArrayList<ClothItem>> clothItemsLiveData;

	public ObjectFileRepository(Context context) {
		appContext = context.getApplicationContext();
	}

	public LiveData<ArrayList<ClothItem>> getClothItemsLiveData() {
		if (clothItemsLiveData == null) {
			Log.i(TAG, "clothItemsLiveData is null, reading from file");
			clothItemsLiveData = readLaundry(); // read from file
			if (clothItemsLiveData == null) { // still null
				Log.i(TAG, "clothItemsLiveData is still null, creating new LiveData");
				clothItemsLiveData = new MutableLiveData<>(); // create new
				initializeClothItemsLiveData();
			}
		}
		return clothItemsLiveData;
	}

	private void initializeClothItemsLiveData() {
		clothItemsLiveData.setValue(new ArrayList<ClothItem>());
	}

	public void addNewClothItem(ClothItem clothItem) {
		Log.i(TAG, "insert()");
		ArrayList<ClothItem> temp = clothItemsLiveData.getValue();
		temp.add(clothItem);
		clothItemsLiveData.setValue(temp);
		writeLaundry(temp);
	}

	public void updateClothItem(ClothItem clothItem) {
		ArrayList<ClothItem> temp = clothItemsLiveData.getValue();
		for (ClothItem ci : temp)
			if (clothItem.getId().equals(ci.getId())) {
				ci.setName(clothItem.getName());
				ci.setColor(clothItem.getColor());
				ci.setWashHistory(clothItem.getWashHistory());
			}
		clothItemsLiveData.setValue(temp);
		writeLaundry(temp);
	}

	private void writeLaundry(ArrayList<ClothItem> clothItems) {
		try
		{
			FileOutputStream fos = appContext.openFileOutput(LAUNDRYTRACKER_FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(clothItems);
			fos.close();
			os.close();
			Log.i(TAG, "writeLaundry() done");
		} catch(Exception e) {
			//handle exceptions
			Log.i(TAG, "writeLaundry() exception");
		}
	}

	private MutableLiveData<ArrayList<ClothItem>> readLaundry() {
		try
		{
			FileInputStream fis = appContext.openFileInput(LAUNDRYTRACKER_FILE_NAME);
			ObjectInputStream is = new ObjectInputStream(fis);
			ArrayList<ClothItem> laundryList = (ArrayList<ClothItem>) is.readObject();
			fis.close();
			is.close();
			Log.i(TAG, "readLaundry() done");
			MutableLiveData<ArrayList<ClothItem>> mld = new MutableLiveData<>();
			mld.setValue(laundryList);
			return mld;
		} catch(Exception e) {
			Log.i(TAG, "readLaundry() exception: " + e.getMessage());
			return null;
		}
	}
}
