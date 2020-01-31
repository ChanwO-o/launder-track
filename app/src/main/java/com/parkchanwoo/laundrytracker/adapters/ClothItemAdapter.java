package com.parkchanwoo.laundrytracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.models.ClothItem;

import java.util.List;

public class ClothItemAdapter extends RecyclerView.Adapter<ClothItemAdapter.ViewHolder> {
	private List<ClothItem> clothItems;

	public ClothItemAdapter(List<ClothItem> clothItems) {
		this.clothItems = clothItems;
	}

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clothitem, parent, false);
		return new ViewHolder(rowItem);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		ClothItem clothItem = clothItems.get(position);
		holder.clothItem = clothItem;
		holder.tvClothItemName.setText(clothItems.get(position).getName());
		holder.itemView.setBackgroundColor(clothItem.getColor());
	}

	@Override
	public int getItemCount() {
		return clothItems.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private ClothItem clothItem;
		private TextView tvClothItemName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvClothItemName = itemView.findViewById(R.id.tvClothItemName);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {

		}
	}
}