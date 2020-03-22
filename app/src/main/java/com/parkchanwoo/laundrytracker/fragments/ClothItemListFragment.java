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

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parkchanwoo.laundrytracker.activities.EditClothItemActivity;
import com.parkchanwoo.laundrytracker.models.ClothItem;
import com.parkchanwoo.laundrytracker.adapters.ClothItemAdapter;
import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.viewmodels.LaundryViewModel;
import com.smlnskgmail.jaman.adaptiverecyclerview.AdaptiveMessageView;
import com.smlnskgmail.jaman.adaptiverecyclerview.AdaptiveRecyclerView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ClothItemListFragment extends Fragment {
	public static final int EDIT_CLOTH_REQUEST = 1;
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
		AdaptiveRecyclerView rvClothItems = getView().findViewById(R.id.rvClothItems);
		AdaptiveMessageView amvClothItems = getView().findViewById(R.id.amvClothItems);
		rvClothItems.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvClothItems.setHasFixedSize(true);
		rvClothItems.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		rvClothItems.setMessageView(amvClothItems);
		final ClothItemAdapter clothItemAdapter = new ClothItemAdapter();
		rvClothItems.setAdapter(clothItemAdapter);
		clothItemAdapter.setOnItemClickListener(new ClothItemAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(ClothItem clothItem) {
				Intent intent = new Intent(getActivity(), EditClothItemActivity.class);
				intent.putExtra(EditClothItemActivity.CLOTHITEM_EXTRA_TAG, clothItem);
				startActivityForResult(intent, EDIT_CLOTH_REQUEST);
			}
		});

		// ViewModel
		laundryViewModel = ViewModelProviders.of(getActivity()).get(LaundryViewModel.class);
		LiveData<ArrayList<ClothItem>> clothItemsLiveData = laundryViewModel.getClothItemsLiveData();
		clothItemsLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<ClothItem>>() {
			@Override
			public void onChanged(ArrayList<ClothItem> clothItems) {
				tvClothItemsCount.setText("Count: " + clothItems.size());
				Log.d("clothitemadapter", clothItemAdapter.getClothItems() + "");
				clothItemAdapter.setClothItems(clothItems);
			}
		});
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			ClothItem clothItem = (ClothItem) data.getSerializableExtra(EditClothItemActivity.CLOTHITEM_EXTRA_TAG);
			laundryViewModel.updateClothItem(clothItem);
		}
	}
}
