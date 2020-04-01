package com.parkchanwoo.laundrytracker.models;

import android.graphics.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.UUID;

public class ClothItem implements Serializable {
	private String id;
	private String name;
	private String brand;
	private int color;
	private ArrayList<Date> washHistory;

	public ClothItem() {
		id = UUID.randomUUID().toString();
		name = "NoName";
		brand = "";
		color = Color.WHITE;
		washHistory = new ArrayList<>();
	}

	public ClothItem(String name, int color) {
		id = UUID.randomUUID().toString();
		this.name = name;
		this.color = color;
		washHistory = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
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

	public void addWashDate(Date date) {
		washHistory.add(date);
	}

	public Date getRecentWashDate() {
		if (washHistory.isEmpty())
			return new Date(Long.MIN_VALUE); // needs to return some date object for sorting list, so just return minimum date possible
		Collections.sort(washHistory);
		return washHistory.get(washHistory.size() - 1);
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
	public void removeWashDate(Date date) {
		washHistory.remove(date);
	}

	/**
	 * Clear entire wash history
	 */
	public void clearHistory() {
		washHistory.clear();
	}

	@Override
	public String toString() {
		return id + " " + name + " " + brand + " " + color;
	}
}
