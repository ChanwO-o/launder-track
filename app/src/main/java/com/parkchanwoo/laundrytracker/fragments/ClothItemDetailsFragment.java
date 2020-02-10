package com.parkchanwoo.laundrytracker.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.adapters.WashHistoryAdapter;
import com.parkchanwoo.laundrytracker.models.ClothItem;

import java.util.Date;

import static com.parkchanwoo.laundrytracker.activities.EditClothItemActivity.CLOTHITEM_EXTRA_TAG;

public class ClothItemDetailsFragment extends Fragment {

	private ClothItem clothItem;
	private WashHistoryAdapter washHistoryAdapter;

	public ClothItemDetailsFragment() {
		// Required empty public constructor
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = getActivity().getIntent();
		if (intent != null)
			clothItem = (ClothItem) intent.getSerializableExtra(CLOTHITEM_EXTRA_TAG);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_cloth_item_details, container, false);
	}

	@Override
	public void onActivityCreated(@Nullable Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		// Views
		TextView tvClothItemId = getActivity().findViewById(R.id.tvClothItemId);
		tvClothItemId.setText("ID: " + clothItem.getId());

		EditText etClothItemName = getActivity().findViewById(R.id.etClothItemName);
		etClothItemName.setText(clothItem.getName());

		LinearLayout llClothItemColor = getActivity().findViewById(R.id.llClothItemColor);
		llClothItemColor.setBackgroundColor(clothItem.getColor());

		final RecyclerView rvClothItemWashHistory = getActivity().findViewById(R.id.rvClothItemWashHistory);
		rvClothItemWashHistory.setLayoutManager(new LinearLayoutManager(getActivity()));
		rvClothItemWashHistory.setHasFixedSize(true);
		rvClothItemWashHistory.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
		washHistoryAdapter = new WashHistoryAdapter();
		washHistoryAdapter.setWashHistory(clothItem.getWashHistory());
		rvClothItemWashHistory.setAdapter(washHistoryAdapter);
		washHistoryAdapter.setOnItemClickListener(new WashHistoryAdapter.OnItemClickListener() {
			@Override
			public void onItemClick(Date date) {
				clothItem.removeWashDate(date);
				washHistoryAdapter.setWashHistory(clothItem.getWashHistory());
			}
		});

		Button bClothItemAddWashHistoryNow = getActivity().findViewById(R.id.bClothItemAddWashHistoryNow);
		bClothItemAddWashHistoryNow.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				clothItem.addWashDate(new Date());
				washHistoryAdapter.setWashHistory(clothItem.getWashHistory());
				washHistoryAdapter.notifyDataSetChanged();
			}
		});

		Button bClothItemAddWashHistory = getActivity().findViewById(R.id.bClothItemAddWashHistory);
		bClothItemAddWashHistory.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				buildAddWashDateDialog();
			}
		});
	}

	private void buildAddWashDateDialog() {
		AlertDialog.Builder dialogAddWashDate = new AlertDialog.Builder(getActivity());
		dialogAddWashDate.setTitle("Add new wash date");
		final DatePicker datePicker = new DatePicker(getActivity());
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		datePicker.setLayoutParams(layoutParams);
		dialogAddWashDate.setView(datePicker);

		dialogAddWashDate.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Date date = new Date(datePicker.getYear(), datePicker.getMonth(), datePicker.getDayOfMonth());
				clothItem.addWashDate(date);
				washHistoryAdapter.setWashHistory(clothItem.getWashHistory());
			}
		});
		dialogAddWashDate.show();
	}
}
