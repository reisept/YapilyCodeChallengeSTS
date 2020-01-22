package com.riscovirtual.marvel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MarvelInfoConfiguration {

	private Properties prop = new Properties();

	public MarvelInfoConfiguration() {
		try (InputStream input = new FileInputStream("./config.properties")) {

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			//System.out.println(prop.getProperty("keys_file"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public String getKeysFile() {
		return prop.getProperty("keys_file");
	}

	public static void main(String[] args) {
		MarvelInfoConfiguration c = new MarvelInfoConfiguration();
		
	}
	
}
