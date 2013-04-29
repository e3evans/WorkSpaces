package com.redpoint.barista.beans;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	
	private String itemDescr;
	private List<Ingredient> ingredients = new ArrayList<Ingredient>();
	private double cost;
	private boolean isavailable;
	
	public String getItemDescr() {
		return itemDescr;
	}
	public void setItemDescr(String itemDescr) {
		this.itemDescr = itemDescr;
	}
	public List<Ingredient> getIngredients() {
		return ingredients;
	}
	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public boolean isIsavailable() {
		return isavailable;
	}
	public void setIsavailable(boolean isavailable) {
		this.isavailable = isavailable;
	}
	
	
	

}
