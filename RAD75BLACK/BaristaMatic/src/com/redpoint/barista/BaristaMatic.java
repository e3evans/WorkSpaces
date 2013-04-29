package com.redpoint.barista;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.redpoint.barista.beans.MenuItem;
import com.redpoint.barista.services.InventoryService;
import com.redpoint.barista.services.MenuService;

public class BaristaMatic {
	
	 public static void main (String[] args) {

		 boolean exit = false;
	     InventoryService.getInstance().displayInventory();
    	 MenuService.getInstance().displayMenu();
	      while (!exit){
	    	  System.out.print("Enter your name: ");
		      //  open up standard input
		      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		      String userInput = null;
	
		      //  read the username from the command-line; need to use try/catch with the
		      //  readLine() method
		      try {
		         userInput = br.readLine();
		      } catch (IOException ioe) {
		         System.out.println("IO error trying read input!");
		         System.exit(1);
		      }
		      if (userInput.equalsIgnoreCase("q")){
		    	  exit=true;
		      }else if (userInput.equalsIgnoreCase("r")){
		    	  InventoryService.getInstance().replenishInventory();
		      }else{
		    	  if (userInput.length()>1){
		    		  System.out.println("Invalid selection: "+userInput);
		    	  }else if (userInput.matches("((-|\\+)?[0-9]+(\\.[0-9]+)?)+")) {  
		    		  int selection = Integer.parseInt(userInput);
		    		  if (selection >0 && selection < 7){
		    			  MenuItem mi = MenuService.getInstance().getMenuItem(selection-1);
		    			  if (mi.isIsavailable()){
		    				  System.out.println("Dispensing: "+mi.getItemDescr());
		    				  InventoryService.getInstance().adjustInventory(mi);
		    			  }else{
		    				  System.out.println("Out of stock: "+mi.getItemDescr());
		    			  }
		    		  }else{
		    			  System.out.println("Invalid selection: "+userInput);
		    		  }
		    	  } else {  
		    		  
		    		  System.out.println("Invalid selection: "+userInput);  
		    	  }    
		      }
		      if (!exit){
		    	  InventoryService.getInstance().displayInventory();
		    	  MenuService.getInstance().displayMenu();
		      }
		     }
	   }
	

}
