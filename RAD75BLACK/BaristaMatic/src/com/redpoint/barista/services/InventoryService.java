package com.redpoint.barista.services;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.redpoint.barista.beans.Ingredient;
import com.redpoint.barista.beans.MenuItem;


public class InventoryService {
	
	private static final int MAXINVENTORY = 10;
	private static InventoryService instance = null;
	
	public static final String ITEM_COFFEE = "Coffee";
	public static final String ITEM_DECAF = "Decaf Coffee";
	public static final String ITEM_SUGAR = "Sugar";
	public static final String ITEM_CREAM = "Cream";
	public static final String ITEM_STEAMED = "Steamed Milk";
	public static final String ITEM_FOAMED = "Foamed Milk";
	public static final String ITEM_ESPRE = "Espresso";
	public static final String ITEM_COCOA = "Cocoa";
	public static final String ITEM_WHIPPED = "Whipped Cream";
	
	private HashMap<String,Ingredient> inventory;
	
	
	protected InventoryService(){
		replenishInventory();
	}
	
	public static InventoryService getInstance(){
		if (instance==null){
			instance = new InventoryService();
		}
		return instance;
	}

	public void replenishInventory(){
		inventory = new HashMap<String,Ingredient>();
		inventory.put(ITEM_COFFEE, new Ingredient(ITEM_COFFEE,MAXINVENTORY,.75));
		inventory.put(ITEM_DECAF, new Ingredient(ITEM_DECAF,MAXINVENTORY,.75));
		inventory.put(ITEM_SUGAR, new Ingredient(ITEM_SUGAR,MAXINVENTORY,.25));
		inventory.put(ITEM_CREAM, new Ingredient(ITEM_CREAM,MAXINVENTORY,.25));
		inventory.put(ITEM_STEAMED, new Ingredient(ITEM_STEAMED,MAXINVENTORY,.35));
		inventory.put(ITEM_FOAMED, new Ingredient(ITEM_FOAMED,MAXINVENTORY,.35));
		inventory.put(ITEM_ESPRE, new Ingredient(ITEM_ESPRE,MAXINVENTORY,1.1));
		inventory.put(ITEM_COCOA, new Ingredient(ITEM_COCOA,MAXINVENTORY,.9));
		inventory.put(ITEM_WHIPPED, new Ingredient(ITEM_WHIPPED,MAXINVENTORY,1));
	}
	

	@SuppressWarnings("unchecked")
	public void adjustInventory(MenuItem menuItem){
		
		List ingredients = menuItem.getIngredients();
		Iterator i = ingredients.iterator();
		while (i.hasNext()){
			Ingredient tempIngredient = (Ingredient)i.next();
			Ingredient invIngredient = (Ingredient)inventory.get(tempIngredient.getDescription());
			if (invIngredient.getQty()>=tempIngredient.getQty())invIngredient.setQty(invIngredient.getQty()-tempIngredient.getQty());
			inventory.put(invIngredient.getDescription(), invIngredient);
		}
		
	}
	

	public void displayInventory(){
		
		SortedSet<String> sortedset= new TreeSet<String>(inventory.keySet());
	    Iterator<String> iterator = sortedset.iterator();
	    System.out.println("Inventory:");
	    while (iterator.hasNext()){
	    	String key = (String)iterator.next();
	    	Ingredient ingredient = (Ingredient)inventory.get(key);
	    	System.out.println(ingredient.getDescription()+","+ingredient.getQty());
	    	
	    }
		
	}
	
	public HashMap<String, Ingredient>getInventory(){
		return inventory;
	}
}
