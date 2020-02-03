package com.parkchanwoo.laundrytracker.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Wardrobe implements Serializable {
	private String name;
	private ArrayList<ClothItem> clothItems;

	public Wardrobe() {
		name = "Unnamed Wardrobe";
		clothItems = new ArrayList<>();
	}

	public Wardrobe(String name) {
		this.name = name;
		clothItems = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<ClothItem> getClothItems() {
		return clothItems;
	}

	public void setClothItems(ArrayList<ClothItem> clothItems) {
		this.clothItems = clothItems;
	}

	/**
	 * Add new cloth item to wardrobe
	 */
	public void addClothItem(ClothItem clothItem) {
		clothItems.add(clothItem);
	}

	/**
	 * Remove cloth item from wardrobe
	 */
	public boolean removeClothItem(ClothItem clothItem) {
		return clothItems.remove(clothItem);
	}

	/**
	 * Clean out wardrobe
	 */
	public void clearWardrobe() {
		clothItems.clear();
	}

	/**
	 * Count number of cloth items in wardrobe
	 */
	public int getCount() {
		return clothItems.size();
	}
}
