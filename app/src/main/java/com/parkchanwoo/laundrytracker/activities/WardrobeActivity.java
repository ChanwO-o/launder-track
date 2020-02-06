package com.parkchanwoo.laundrytracker.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.models.Wardrobe;
import com.parkchanwoo.laundrytracker.viewmodels.WardrobeViewModel;

import java.util.ArrayList;

public class WardrobeActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();
	private WardrobeViewModel wardrobeViewModel;

	public static final String WARDROBE_EXTRA_TAG = "WARDROBE_EXTRA_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wardrobe);

		wardrobeViewModel = ViewModelProviders.of(this).get(WardrobeViewModel.class);
//		LiveData<ArrayList<ClothItem>> clothItemsLiveData = wardrobeViewModel.getClothItemsLiveData();

		Intent intent = getIntent();
		Wardrobe wardrobe = (Wardrobe) intent.getSerializableExtra(WARDROBE_EXTRA_TAG);
		wardrobeViewModel.setWardrobeLiveData(wardrobe); // WardrobeViewModel now controls the selected wardrobe object
		setTitle(wardrobe.getName());
	}

//	@Override
//	public void onColorSelected(int dialogId, int color) {
//		Toast.makeText(this, "Color selected: " + color, Toast.LENGTH_SHORT).show();
//		buildAddClothItemDialog(color);
//	}
//
//	@Override
//	public void onDialogDismissed(int dialogId) {
//
//	}
//
//	private void buildAddClothItemDialog(final int color) {
//		AlertDialog.Builder dialogAddClothItem = new AlertDialog.Builder(this);
//		dialogAddClothItem.setTitle("Add new clothing");
//		final EditText etAddClothItemName = new EditText(this);
//		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//		etAddClothItemName.setLayoutParams(layoutParams);
//		dialogAddClothItem.setView(etAddClothItemName);
//
//		dialogAddClothItem.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				String clothItemName = etAddClothItemName.getText().toString();
//				if (!clothItemName.isEmpty()) {
//					wardrobeViewModel.addNewClothItem(new ClothItem(clothItemName, color));
//				}
//			}
//		});
//
//		dialogAddClothItem.show();
//	}
}
