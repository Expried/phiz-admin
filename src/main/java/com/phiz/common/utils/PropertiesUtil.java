package com.phiz.common.utils;

import java.util.Properties;


public class PropertiesUtil {

	private Properties properties;

	public PropertiesUtil(String fileName) {
		loadFile(fileName);
	}

	private void loadFile(String fileName) {
		try {
			properties = new Properties();
			properties.load(getClass().getResourceAsStream("/"+fileName));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getPorperty(String key){
		return properties.getProperty(key);
	}
	
	public void savePorperty(String key,String value){
		properties.setProperty(key, value);
	}
	
}
