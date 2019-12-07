package com.parkchanwoo.laundrytracker;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ClothItemListFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private WardrobeViewModel wardrobeViewModel;

	public ClothItemListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_cloth_item_list, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		wardrobeViewModel = ViewModelProviders.of(getActivity()).get(WardrobeViewModel.class);

		LiveData<ArrayList<ClothItem>> clothItemsLiveData = wardrobeViewModel.getClothItemsLiveData();

		final TextView tvClothItemsCount = getView().findViewById(R.id.tvClothItemsCount);
		final RecyclerView rvClothItems = getView().findViewById(R.id.rvClothItems);
		rvClothItems.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvClothItems.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

		clothItemsLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ClothItem>>() {
			@Override
			public void onChanged(ArrayList<ClothItem> clothItems) {
				tvClothItemsCount.setText("Cloth items count: " + clothItems.size());
				rvClothItems.setAdapter(new ClothItemAdapter(clothItems));
			}
		});
	}
}
