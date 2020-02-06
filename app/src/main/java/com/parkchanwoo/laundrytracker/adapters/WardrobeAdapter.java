package com.parkchanwoo.laundrytracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.models.Wardrobe;

import java.util.List;

public class WardrobeAdapter extends RecyclerView.Adapter<WardrobeAdapter.ViewHolder> {
	private List<Wardrobe> wardrobes;
	private OnItemClickListener listener;

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_wardrobe, parent, false);
		return new ViewHolder(rowItem);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		Wardrobe wardrobe = wardrobes.get(position);
		holder.tvWardrobeName.setText(wardrobes.get(position).getName()); // set text to name of wardrobe at position
	}

	@Override
	public int getItemCount() {
		return wardrobes.size();
	}

	public void setWardrobes(List<Wardrobe> wardrobes) {
		this.wardrobes = wardrobes;
		notifyDataSetChanged();
	}

	public Wardrobe getWardrobeAt(int position) {
		return wardrobes.get(position);
	}

	class ViewHolder extends RecyclerView.ViewHolder {
//		public static final String WARDROBE_EXTRA_TAG = "WARDROBE_EXTRA_TAG";
		private TextView tvWardrobeName;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvWardrobeName = itemView.findViewById(R.id.tvWardrobeName);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = getAdapterPosition();
					if (listener != null && position != RecyclerView.NO_POSITION)
						listener.onItemClick(wardrobes.get(position));
				}
			});
		}
	}

	public interface OnItemClickListener {
		void onItemClick(Wardrobe wardrobe);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}
}
