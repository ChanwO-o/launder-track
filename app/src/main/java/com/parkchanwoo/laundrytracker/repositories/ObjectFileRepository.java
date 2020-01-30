package com.parkchanwoo.laundrytracker.repositories;

import android.content.Context;

import com.parkchanwoo.laundrytracker.models.Wardrobe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class ObjectFileRepository {

	private Context appContext;
	private static final String LAUNDRYTRACKER_FILE_NAME = "laundrytracker.txt";

	public ObjectFileRepository(Context context) {
		appContext = context.getApplicationContext();
	}

	public void writeLaundry(ArrayList<Wardrobe> laundry) {
		try
		{
			FileOutputStream fos = appContext.openFileOutput(LAUNDRYTRACKER_FILE_NAME, Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(laundry);
			fos.close();
			os.close();
		} catch(Exception e) {
			//handle exceptions
		}
	}

	public ArrayList<Wardrobe> readLaundry() {
		try
		{
			FileInputStream fis = appContext.openFileInput(LAUNDRYTRACKER_FILE_NAME);
			ObjectInputStream is = new ObjectInputStream(fis);
			ArrayList<Wardrobe> laundryObj = (ArrayList<Wardrobe>) is.readObject();
			fis.close();
			is.close();
			return laundryObj;
		} catch(Exception e) {
			return null;
		}
	}
}
