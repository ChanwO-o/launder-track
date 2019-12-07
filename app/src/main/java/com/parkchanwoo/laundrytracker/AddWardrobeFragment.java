package com.parkchanwoo.laundrytracker;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
				laundryViewModel.addNewWardrobe();
			}
		});
	}
}
