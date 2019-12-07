package com.parkchanwoo.laundrytracker;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WardrobeAdapter extends RecyclerView.Adapter<WardrobeAdapter.ViewHolder> {
	private List<Wardrobe> wardrobes;

	public WardrobeAdapter(List<Wardrobe> wardrobes) {
		this.wardrobes = wardrobes;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wardrobe, parent, false);
		return new ViewHolder(rowItem);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.tvWardrobeName.setText(wardrobes.get(position).getName()); // set text to name of wardrobe at position
	}

	@Override
	public int getItemCount() {
		return wardrobes.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {

		private TextView tvWardrobeName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvWardrobeName = itemView.findViewById(R.id.tvWardrobeName);
		}
	}
}
