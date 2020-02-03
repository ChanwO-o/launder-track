package com.parkchanwoo.laundrytracker.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.activities.WardrobeActivity;
import com.parkchanwoo.laundrytracker.models.Wardrobe;

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
		holder.wardrobe = wardrobes.get(position);
		holder.tvWardrobeName.setText(wardrobes.get(position).getName()); // set text to name of wardrobe at position
	}

	@Override
	public int getItemCount() {
		return wardrobes.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		public static final String WARDROBE_EXTRA_TAG = "WARDROBE_EXTRA_TAG";
		private Wardrobe wardrobe;
		private TextView tvWardrobeName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvWardrobeName = itemView.findViewById(R.id.tvWardrobeName);
			itemView.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(v.getContext(), WardrobeActivity.class);
			intent.putExtra(WARDROBE_EXTRA_TAG, wardrobe);
			v.getContext().startActivity(intent);
		}
	}
}
