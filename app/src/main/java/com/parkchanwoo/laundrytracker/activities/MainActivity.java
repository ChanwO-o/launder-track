package com.parkchanwoo.laundrytracker.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jaredrummler.android.colorpicker.ColorPickerDialogListener;
import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.viewmodels.LaundryViewModel;

public class MainActivity extends AppCompatActivity implements ColorPickerDialogListener {
	private String TAG = this.getClass().getSimpleName();
	private LaundryViewModel laundryViewModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		setTheme(R.style.AppTheme);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// ViewModel
		laundryViewModel = ViewModelProviders.of(this).get(LaundryViewModel.class);
	}

	@Override
	public void onColorSelected(int dialogId, int color) {
		Toast.makeText(this, "Color selected: " + color, Toast.LENGTH_SHORT).show();
		buildAddClothItemDialog(color);
	}

	@Override
	public void onDialogDismissed(int dialogId) {

	}

	private void buildAddClothItemDialog(final int color) {
		AlertDialog.Builder dialogAddClothItem = new AlertDialog.Builder(this);
		dialogAddClothItem.setTitle("Add new clothing");
		final EditText etAddClothItemName = new EditText(this);
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		etAddClothItemName.setLayoutParams(layoutParams);
		dialogAddClothItem.setView(etAddClothItemName);

		dialogAddClothItem.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String clothItemName = etAddClothItemName.getText().toString();
				if (!clothItemName.isEmpty()) {
					laundryViewModel.addNewClothItem(new ClothItem(clothItemName, color));
				}
			}
		});

		dialogAddClothItem.show();
	}
}
