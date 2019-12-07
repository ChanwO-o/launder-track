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

public class AddClothItemFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private WardrobeViewModel wardrobeViewModel;

	public AddClothItemFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_add_cloth_item, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		wardrobeViewModel = ViewModelProviders.of(getActivity()).get(WardrobeViewModel.class);

		LiveData<ArrayList<ClothItem>> clothItemsLiveData = wardrobeViewModel.getClothItemsLiveData();

		Button bAddClothItem = getView().findViewById(R.id.bAddClothItem);
		bAddClothItem.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				wardrobeViewModel.addNewClothItem(new ClothItem());
			}
		});
	}
}
