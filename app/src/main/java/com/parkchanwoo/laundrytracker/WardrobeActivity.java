package com.parkchanwoo.laundrytracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;

import java.util.ArrayList;

public class WardrobeActivity extends AppCompatActivity implements ColorPickerDialogListener {
	private String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wardrobe);

		Intent intent = getIntent();
		Wardrobe wardrobe = (Wardrobe) intent.getSerializableExtra(WardrobeAdapter.ViewHolder.WARDROBE_EXTRA_TAG);
		setTitle(wardrobe.getName());
	}

	@Override
	public void onColorSelected(int dialogId, int color) {
		Toast.makeText(this, "Color selected: " + color, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDialogDismissed(int dialogId) {

	}
}
