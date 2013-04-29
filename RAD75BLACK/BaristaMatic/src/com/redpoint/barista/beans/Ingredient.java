package com.redpoint.barista.beans;

public class Ingredient {
	
	private String description;
	private int qty;
	private double cost;
	
	public Ingredient(){
		
	}
	
	public Ingredient(String description, int qty, double cost){
		this.description=description;
		this.qty=qty;
		this.cost=cost;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	
	

}
