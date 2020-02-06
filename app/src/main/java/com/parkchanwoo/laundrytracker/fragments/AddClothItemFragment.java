package com.parkchanwoo.laundrytracker.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jaredrummler.android.colorpicker.ColorPickerDialog;
import com.parkchanwoo.laundrytracker.R;

public class AddClothItemFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();

	public AddClothItemFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_add_cloth_item, container, false);
		Button bAddClothItem = view.findViewById(R.id.bAddClothItem);
		bAddClothItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buildAddClothItemDialog();
			}
		});
		return view;
	}

	public void buildAddClothItemDialog() {
		ColorPickerDialog.newBuilder().show(getActivity());
	}
}
