package com.parkchanwoo.laundrytracker.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.parkchanwoo.laundrytracker.R;

public class EditClothItemActivity extends AppCompatActivity {
	private String TAG = this.getClass().getSimpleName();

	public static final String CLOTHITEM_EXTRA_TAG = "CLOTHITEM_EXTRA_TAG";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_cloth_item);

		ClothItemDetailsFragment clothItemDetailsFragment = new ClothItemDetailsFragment();
		getSupportFragmentManager().beginTransaction()
				.add(R.id.clEditClothItemActivity, clothItemDetailsFragment, ClothItemDetailsFragment.TAG)
				.commit();
	}
}
