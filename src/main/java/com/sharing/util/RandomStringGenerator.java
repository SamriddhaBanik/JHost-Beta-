package com.sharing.util;

public class RandomStringGenerator {
	
	//private static final String ALPHABETS="";
	private static final int MAX=126;
	private static final int MIN=33;
	private static final int THRESHOLD=1000;

	public String getRandomAlphaNumericString(int length){
		return getRandomString(length, true);
	}
	
	private String getRandomString(int length,boolean isAlphaNumeric){
		return null;
	}
	
	public String getRandomString(int length){
		return getRandomString(length, false);
	}
	
	private String getRandomAlpabets(){
		int min=65;
		int max=90;
		return getRandomString(false, 0, 0);
	}
	private String getRandomSpecialCharecters(){
		return getRandomString(false, 0, 0);
	}
	private String getRandomNumericCharecter(){
		return getRandomString(false, 0, 0);
	}
	private String getRandomString(boolean needsExcape ,int ulimit,int llimit){
		int randomNumber=((int) (Math.random()*THRESHOLD)%MAX);
		if(randomNumber<MIN){
			randomNumber=randomNumber+MIN;
		}
		if(needsExcape){
			//if()
		}
		return "";
	}
}
