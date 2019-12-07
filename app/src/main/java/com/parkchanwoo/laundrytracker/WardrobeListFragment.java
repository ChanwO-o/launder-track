package com.parkchanwoo.laundrytracker;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class WardrobeListFragment extends Fragment {
	private String TAG = this.getClass().getSimpleName();
	private LaundryViewModel laundryViewModel;

	public WardrobeListFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_wardrobe_list, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		laundryViewModel = ViewModelProviders.of(getActivity()).get(LaundryViewModel.class);

		LiveData<ArrayList<Wardrobe>> wardrobesLiveData = laundryViewModel.getWardrobesLiveData();

		final TextView tvWardrobesCount = getView().findViewById(R.id.tvWardrobesCount);
		final RecyclerView rvWardrobeList = getView().findViewById(R.id.rvWardrobeList);
		rvWardrobeList.setLayoutManager(new LinearLayoutManager(getActivity()));

		wardrobesLiveData.observe(getViewLifecycleOwner(), new Observer<ArrayList<Wardrobe>>() {
			@Override
			public void onChanged(ArrayList<Wardrobe> wardrobes) {
				tvWardrobesCount.setText("Count: " + wardrobes.size());
				rvWardrobeList.setAdapter(new WardrobeAdapter(wardrobes));
//				wardrobeAdapter.notifyDataSetChanged();
			}
		});
	}
}