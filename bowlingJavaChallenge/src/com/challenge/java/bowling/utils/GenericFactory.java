package com.challenge.java.bowling.utils;

public class GenericFactory {
	
	public static Utils getBowlingUtils() {
		return new UtilsImpl();
	}

}
