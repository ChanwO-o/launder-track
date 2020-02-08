package com.parkchanwoo.laundrytracker.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Wardrobe implements Serializable {
	private static int instanceNum = 0;
	private String id;
	private String name;
	private ArrayList<ClothItem> clothItems;

	public Wardrobe() {
		instanceNum++;
		id = this.getClass().getSimpleName() + instanceNum;
		name = "Unnamed Wardrobe";
		clothItems = new ArrayList<>();
	}

	public Wardrobe(String name) {
		instanceNum++;
		id = this.getClass().getSimpleName() + instanceNum;
		this.name = name;
		clothItems = new ArrayList<>();
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
