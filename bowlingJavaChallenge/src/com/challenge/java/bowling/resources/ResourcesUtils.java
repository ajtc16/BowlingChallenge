package com.challenge.java.bowling.resources;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class ResourcesUtils {

	
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle("com.challenge.java.bowling.resources.Resources");

	  
	  public static String getString(String key)
	  {
	    try
	    {
	      return RESOURCE_BUNDLE.getString(key);
	    }
	    catch (MissingResourceException e) {}
	    return '!' + key + '!';
	  }
	
	
}
