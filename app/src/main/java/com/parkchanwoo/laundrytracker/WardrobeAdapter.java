package com.parkchanwoo.laundrytracker;

import android.content.Intent;
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

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public static final String ADAPTER_POSITION_TAG = "ADAPTER_POSITION";
		private TextView tvWardrobeName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvWardrobeName = itemView.findViewById(R.id.tvWardrobeName);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			Log.i("WardrobeAdapter", "ViewHolder.onClick()");
			Intent intent = new Intent(v.getContext(), WardrobeActivity.class);
			intent.putExtra(ADAPTER_POSITION_TAG, getAdapterPosition());
			v.getContext().startActivity(intent);
		}
	}
}
