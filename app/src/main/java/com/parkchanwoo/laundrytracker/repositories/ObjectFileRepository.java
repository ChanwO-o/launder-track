package com.parkchanwoo.laundrytracker.repositories;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.models.Wardrobe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectFileRepository {
	private String TAG = this.getClass().getSimpleName();
	private Context appContext;
	private static final String LAUNDRYTRACKER_FILE_NAME = "laundrytracker.txt";
	private MutableLiveData<ArrayList<Wardrobe>> wardrobesLiveData;
	private MutableLiveData<Wardrobe> wardrobeLiveData;

	public ObjectFileRepository(Context context) {
		appContext = context.getApplicationContext();
	}

	public LiveData<ArrayList<Wardrobe>> getWardrobesLiveData() {
		if (wardrobesLiveData == null) {
			Log.i(TAG, "wardrobesLiveData is null, reading from file");
			wardrobesLiveData = readLaundry(); // read from file
			if (wardrobesLiveData == null) { // still null
				Log.i(TAG, "wardrobesLiveData is still null, creating new LiveData");
				wardrobesLiveData = new MutableLiveData<>(); // create new
				initializeWardrobesLiveData();
			}
		}
		Log.i(TAG, "returning wardrobesLiveData");
		return wardrobesLiveData;
	}

	private void initializeWardrobesLiveData() {
		wardrobesLiveData.setValue(new ArrayList<Wardrobe>());
	}

	public void addNewWardrobe(Wardrobe wardrobe) {
		Log.i(TAG, "insert()");
		ArrayList<Wardrobe> temp = wardrobesLiveData.getValue();
		temp.add(wardrobe);
		wardrobesLiveData.setValue(temp);
		writeLaundry(temp);
	}

	public LiveData<Wardrobe> getWardrobeLiveData() {
		if (wardrobeLiveData == null) {
			Log.i(TAG, "wardrobeLiveData is null; creating new LiveData");
			wardrobeLiveData = new MutableLiveData<>();
			initializeWardrobeLiveData();
		}
		return wardrobeLiveData;
	}

	public void setWardrobeLiveData(Wardrobe wardrobe) {
		if (wardrobeLiveData == null) {
			wardrobeLiveData = new MutableLiveData<>();
		}
		wardrobeLiveData.setValue(wardrobe);
	}

	private void initializeWardrobeLiveData() {
		wardrobeLiveData.setValue(new Wardrobe());
	}

	public void addNewClothItem(ClothItem clothItem) {
		Wardrobe wardrobe = wardrobeLiveData.getValue();
		if (wardrobe == null)
			return;
		wardrobe.addClothItem(clothItem);
		wardrobeLiveData.setValue(wardrobe);
//		writeLaundry(wardrobesLiveData);
	}

	private Wardrobe getWardrobeWithName(String wardrobeName) {
		for (Wardrobe wardrobe : wardrobesLiveData.getValue())
			if (wardrobe.getName().equalsIgnoreCase(wardrobeName))
				return wardrobe;
		return null;
	}

	private void writeLaundry(ArrayList<Wardrobe> wardrobes) {
		try
		{
			FileOutputStream fos = appContext.openFileOutput(LAUNDRYTRACKER_FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(wardrobes);
			fos.close();
			os.close();
			Log.i(TAG, "writeLaundry() done");
		} catch(Exception e) {
			//handle exceptions
			Log.i(TAG, "writeLaundry() exception");
		}
	}

	private MutableLiveData<ArrayList<Wardrobe>> readLaundry() {
		try
		{
			FileInputStream fis = appContext.openFileInput(LAUNDRYTRACKER_FILE_NAME);
			ObjectInputStream is = new ObjectInputStream(fis);
			ArrayList<Wardrobe> laundryList = (ArrayList<Wardrobe>) is.readObject();
			fis.close();
			is.close();
			Log.i(TAG, "readLaundry() done");
			MutableLiveData<ArrayList<Wardrobe>> mld = new MutableLiveData<>();
			mld.setValue(laundryList);
			return mld;
		} catch(Exception e) {
			Log.i(TAG, "readLaundry() exception: " + e.getMessage());
			return null;
		}
	}
}
