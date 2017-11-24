package com.sharing.storage.model;

import java.util.HashMap;
import java.util.Map;

public class DisplayIdMapper {

	private static Map<String,String> encryptionMap=new HashMap<>();
	
	public static void add(String key,String value){
		encryptionMap.put(key, value);
	}
	
	public static String remove(String key){
		return encryptionMap.remove(key);
	}
	
	public static String get(String key){
		return encryptionMap.get(key);
	}
}
