package com.parkchanwoo.laundrytracker;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class AddWardrobeFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private LaundryViewModel laundryViewModel;

	public AddWardrobeFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_wardrobe, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		laundryViewModel = ViewModelProviders.of(getActivity()).get(LaundryViewModel.class);

		LiveData<ArrayList<Wardrobe>> wardrobesLiveData = laundryViewModel.getWardrobesLiveData();

		Button bAddWardrobe = getView().findViewById(R.id.bAddWardrobe);
		bAddWardrobe.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buildAddWardrobeDialog();
			}
		});
	}

	private void buildAddWardrobeDialog() {
		AlertDialog.Builder dialogAddWardrobe = new AlertDialog.Builder(getActivity());
		dialogAddWardrobe.setTitle("Add new wardrobe");
		final EditText etAddWardrobeName = new EditText(getActivity());
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		etAddWardrobeName.setLayoutParams(layoutParams);
		dialogAddWardrobe.setView(etAddWardrobeName);

		dialogAddWardrobe.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				String wardrobeName = etAddWardrobeName.getText().toString();
				if (!wardrobeName.isEmpty()) {
					laundryViewModel.addNewWardrobe(new Wardrobe(wardrobeName));
				}
			}
		});

		dialogAddWardrobe.show();
	}
}
