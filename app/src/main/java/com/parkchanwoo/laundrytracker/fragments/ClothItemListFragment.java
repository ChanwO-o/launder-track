package com.parkchanwoo.laundrytracker.fragments;


import android.content.Intent;
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

import com.parkchanwoo.laundrytracker.activities.EditClothItemActivity;
import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.adapters.ClothItemAdapter;
import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.viewmodels.LaundryViewModel;

import java.util.ArrayList;

public class ClothItemListFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private LaundryViewModel laundryViewModel;

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

		// Views
		final TextView tvClothItemsCount = getView().findViewById(R.id.tvClothItemsCount);
		RecyclerView rvClothItems = getView().findViewById(R.id.rvClothItems);
		rvClothItems.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvClothItems.setHasFixedSize(true);
		rvClothItems.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		final ClothItemAdapter clothItemAdapter = new ClothItemAdapter();
		rvClothItems.setAdapter(clothItemAdapter);
		clothItemAdapter.setOnItemClickListener(new ClothItemAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(ClothItem clothItem) {
				Intent intent = new Intent(getActivity(), EditClothItemActivity.class);
				intent.putExtra(EditClothItemActivity.CLOTHITEM_EXTRA_TAG, clothItem);
				startActivity(intent);
			}
		});

		// ViewModel
		laundryViewModel = ViewModelProviders.of(getActivity()).get(LaundryViewModel.class);
		LiveData<ArrayList<ClothItem>> clothItemsLiveData = laundryViewModel.getClothItemsLiveData();
		clothItemsLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ClothItem>>() {
			@Override
			public void onChanged(ArrayList<ClothItem> clothItems) {
				tvClothItemsCount.setText("Count: " + clothItems.size());
				clothItemAdapter.setClothItems(clothItems);
			}
		});
	}
}
