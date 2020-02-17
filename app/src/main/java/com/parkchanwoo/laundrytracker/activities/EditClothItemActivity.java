package com.parkchanwoo.laundrytracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.fragments.ClothItemDetailsFragment;

public class EditClothItemActivity extends AppCompatActivity implements ColorPickerDialogListener {
	private String TAG = this.getClass().getSimpleName();

	public static final String CLOTHITEM_EXTRA_TAG = "CLOTHITEM_EXTRA_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cloth_item);

		ClothItemDetailsFragment clothItemDetailsFragment = new ClothItemDetailsFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.clEditClothItemActivity, clothItemDetailsFragment, ClothItemDetailsFragment.TAG)
				.commit();
	}

	@Override
	public void onColorSelected(int dialogId, int color) {
		ClothItemDetailsFragment clothItemDetailsFragment = (ClothItemDetailsFragment) getSupportFragmentManager().findFragmentByTag(ClothItemDetailsFragment.TAG);
		clothItemDetailsFragment.setClothItemColor(color);
	}

	@Override
	public void onDialogDismissed(int dialogId) {

	}
}
