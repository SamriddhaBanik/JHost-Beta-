package com.sharing.util;

import java.util.UUID;

public class KeyGenerator {

	public static String getKey(){
		return UUID.randomUUID().toString();
	}
}
