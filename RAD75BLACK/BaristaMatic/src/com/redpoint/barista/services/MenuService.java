package com.redpoint.barista.services;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import com.redpoint.barista.beans.Ingredient;
import com.redpoint.barista.beans.MenuItem;

public class MenuService {

	private List<MenuItem> menu = new ArrayList<MenuItem>();
	private static MenuService instance = null;
	
	public static final String COFFEE = "Coffee";
	public static final String DECAF = "Decaf Coffee";
	public static final String LATTE = "Caffe Latte";
	public static final String AMERIC = "Caffe Americano";
	public static final String MOCHA = "Caffe Mocha";
	public static final String CAPP = "Cappuccino";
	
	protected MenuService(){
		menu.add(createMenuItem(AMERIC+",3"+InventoryService.ITEM_ESPRE));
		menu.add(createMenuItem(LATTE+",2"+InventoryService.ITEM_ESPRE+",1"+InventoryService.ITEM_STEAMED));
		menu.add(createMenuItem(MOCHA+",1"+InventoryService.ITEM_ESPRE+",1"+InventoryService.ITEM_COCOA+",1"+
				InventoryService.ITEM_STEAMED+",1"+InventoryService.ITEM_WHIPPED));
		menu.add(createMenuItem(CAPP+",2"+InventoryService.ITEM_ESPRE+",1"+InventoryService.ITEM_STEAMED+
				",1"+InventoryService.ITEM_FOAMED));
		menu.add(createMenuItem(COFFEE+",3"+InventoryService.ITEM_COFFEE+",1"+InventoryService.ITEM_SUGAR+",1"+InventoryService.ITEM_CREAM));
		menu.add(createMenuItem(DECAF+",3"+InventoryService.ITEM_DECAF+",1"+InventoryService.ITEM_SUGAR+",1"+InventoryService.ITEM_CREAM));
			
	}
	
	public static MenuService getInstance(){
		
		instance = new MenuService();
		return instance;
	}
	
	private MenuItem createMenuItem(String itemDescription){
		
		String [] parsedItem = itemDescription.split(",");
		boolean available = true;
		double price = 0;
		//Add Menu Description
		MenuItem mi = new MenuItem();
		mi.setItemDescr(parsedItem[0]);
	
		//Add Ingredients.
		for (int i = 1;i<parsedItem.length;i++){
			int itemqty = Integer.parseInt(parsedItem[i].substring(0,1));
			
			String itemkey = parsedItem[i].substring(1,parsedItem[i].length());
			Ingredient invIngredient = InventoryService.getInstance().getInventory().get(itemkey);
			//Check to see if inventory is OK for this item.
			if (invIngredient.getQty()<itemqty)available=false;
			Ingredient menuIngredient = new Ingredient(invIngredient.getDescription(),invIngredient.getQty(),invIngredient.getCost());
			menuIngredient.setQty(itemqty);
			mi.getIngredients().add(menuIngredient);
			//Add the price to the for the item.
			price+=(menuIngredient.getCost()*itemqty);
		}
		mi.setIsavailable(available);
		mi.setCost(price);
		
		return mi;
	}
	
	public MenuItem getMenuItem(int itemNumber){
		return (MenuItem)menu.get(itemNumber);
	}
	
	public void displayMenu(){
		System.out.println("Menu:");
		for (int i=0;i<menu.size();i++){
			MenuItem mi = (MenuItem)menu.get(i);
			NumberFormat currencyFormatter=NumberFormat.getCurrencyInstance();
			System.out.println(i+1+","+mi.getItemDescr()+","+currencyFormatter.format(mi.getCost())+","+mi.isIsavailable());
		}
	}
	
}
