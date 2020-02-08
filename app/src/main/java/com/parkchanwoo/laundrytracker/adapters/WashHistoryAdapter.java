package com.parkchanwoo.laundrytracker.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parkchanwoo.laundrytracker.R;

import java.util.Date;
import java.util.List;

public class WashHistoryAdapter extends RecyclerView.Adapter<WashHistoryAdapter.ViewHolder> {
	private List<Date> washHistory;
	private OnItemClickListener listener;

	@NonNull
	@Override
	public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View rowItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_clothitem, parent, false);
		return new ViewHolder(rowItem);
	}

	@Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
		holder.tvWashDate.setText(washHistory.get(position).toString());
	}

	@Override
	public int getItemCount() {
		return washHistory.size();
	}

	public void setWashHistory(List<Date> washHistory) {
		this.washHistory = washHistory;
		notifyDataSetChanged();
	}

	public Date getDateAt(int position) {
		return washHistory.get(position);
	}

	public void addDate(Date date) {
		washHistory.add(date);
		notifyDataSetChanged();
	}

	public void removeDate(Date date) {
		washHistory.remove(date);
		notifyDataSetChanged();
	}

	class ViewHolder extends RecyclerView.ViewHolder {
		private TextView tvWashDate;

		public ViewHolder(@NonNull View itemView) {
			super(itemView);
			tvWashDate = itemView.findViewById(R.id.tvWashDate);
			itemView.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int position = getAdapterPosition();
					if (listener != null && position != RecyclerView.NO_POSITION)
						listener.onItemClick(washHistory.get(position));
				}
			});
		}
	}

	public interface OnItemClickListener {
		void onItemClick(Date date);
	}

	public void setOnItemClickListener(OnItemClickListener listener) {
		this.listener = listener;
	}
}
