package com.parkchanwoo.laundrytracker.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parkchanwoo.laundrytracker.R;
import com.parkchanwoo.laundrytracker.models.ClothItem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ClothItemAdapter extends RecyclerView.Adapter<ClothItemAdapter.ViewHolder> {
	private List<ClothItem> clothItems;
	private OnItemClickListener listener;

	public ClothItemAdapter() {
		clothItems = new ArrayList<>();
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
		holder.tvClothItemName.setText(clothItem.getName());
		if (clothItem.getWashHistory().isEmpty())
			holder.tvClothItemRecentWashDate.setText("--");
		else {
			Calendar cal = Calendar.getInstance(Locale.getDefault());
			Date date = clothItem.getRecentWashDate();
			cal.setTime(date);
			String dateText = (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cal.get(Calendar.YEAR);
			holder.tvClothItemRecentWashDate.setText(dateText);
		}
		holder.itemView.setBackgroundColor(clothItem.getColor());
		int BLACK = -16777216;
		if (clothItem.getColor() == BLACK) { // color selected is black
			holder.tvClothItemName.setTextColor(Color.WHITE);
			holder.tvClothItemRecentWashDate.setTextColor(Color.WHITE);
		}
	}

	@Override
	public int getItemCount() {
		return clothItems.size();
	}

	public List<ClothItem> getClothItems() {
		return clothItems;
	}

	public void setClothItems(List<ClothItem> clothItems) {
		this.clothItems = clothItems;
		notifyDataSetChanged();
	}

	public ClothItem getClothItemAt(int position) {
		return clothItems.get(position);
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private TextView tvClothItemName, tvClothItemRecentWashDate;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvClothItemName = itemView.findViewById(R.id.tvClothItemName);
			tvClothItemRecentWashDate = itemView.findViewById(R.id.tvClothItemRecentWashDate);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = getAdapterPosition();
					if (listener != null && position != RecyclerView.NO_POSITION)
						listener.onItemClick(clothItems.get(position));
				}
			});
		}
	}

	public interface OnItemClickListener {
		void onItemClick(ClothItem clothItem);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}
}
