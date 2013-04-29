package com.manpower.directaltent.reporting.utils;

import java.util.UUID;

public abstract class UUIDFunctions {
	private static final String uuidKey = "x-q-3-t-7"; 
	
	public static boolean isValidUUID(String uuid){
		String [] splitUUID = uuid.split("-");
		String [] splitUUIDkey = uuidKey.split("-");
		for (int i=0;i<splitUUID.length;i++){
			if (!splitUUID[i].substring(splitUUID[i].length()-1).equals(splitUUIDkey[i]))return false;
//			System.out.println(splitUUID[i].substring(splitUUID[i].length()-1)+"----"+splitUUIDkey[i]);
		}
		
		return true;
	}
	
	public static String createUUID(){
		UUID uuid = UUID.randomUUID();
//		System.out.println("UUID:  "+uuid);
		String [] splitUUID = uuid.toString().split("-");
		String [] splitUUIDkey = uuidKey.split("-");
		String temp = "";
		for (int i=0;i<splitUUID.length;i++){
			temp+=splitUUID[i]+splitUUIDkey[i];
			if (i!=splitUUID.length-1)temp+="-";
		}
		
		return temp;
		
	}

}
