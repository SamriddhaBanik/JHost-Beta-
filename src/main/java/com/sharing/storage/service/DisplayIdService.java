package com.sharing.storage.service;

import com.sharing.storage.model.DisplayIdMapper;
import com.sharing.util.Encryption;
import com.sharing.util.KeyGenerator;

public class DisplayIdService {

	public static String add(String plainText) throws Exception{
		Encryption encryption=Encryption.getInstance();
		String encryptedValue=encryption.encrypt(plainText);
		String idKey=KeyGenerator.getKey();
		DisplayIdMapper.add(idKey, encryptedValue);
		return idKey;
	}
	
	public static String get(String uuidString) throws Exception{
		Encryption encryption=Encryption.getInstance();
		return encryption.decrypt(DisplayIdMapper.get(uuidString));
	}
}
