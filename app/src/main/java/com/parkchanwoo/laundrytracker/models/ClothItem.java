package com.parkchanwoo.laundrytracker.models;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class ClothItem implements Serializable {
	private String name;
	private int color;
	private ArrayList<Date> washHistory;

	public ClothItem() {
		name = "Unnamed Cloth Item";
		color = Color.WHITE;
		washHistory = new ArrayList<>();
	}

	public ClothItem(String name, int color) {
		this.name = name;
		this.color = color;
		washHistory = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public ArrayList<Date> getWashHistory() {
		return washHistory;
	}

	public void setWashHistory(ArrayList<Date> washHistory) {
		this.washHistory = washHistory;
	}

	/**
	 * Wash now and add current date to history
	 */
	public void wash() {
		Date date = new Date(); // assigns current date time
		washHistory.add(date);
	}

	/**
	 * Remove history record
	 */
	public void removeHistory(Date date) {
		washHistory.remove(date);
	}

	/**
	 * Clear entire wash history
	 */
	public void clearHistory() {
		washHistory.clear();
	}
}
