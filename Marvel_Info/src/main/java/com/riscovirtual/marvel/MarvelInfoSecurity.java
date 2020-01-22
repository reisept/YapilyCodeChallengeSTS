package com.riscovirtual.marvel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class MarvelInfoSecurity {

	Properties prop = new Properties();

	/**
	 * @param ts Expected to be tiemstamp but can be any unique string between calls
	 * @return Hashed string to be used in call to Marvel Api
	 */
	public String getHash(String ts) {

		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		messageDigest.reset();

		String original = ts + prop.getProperty("key.private") + prop.getProperty("key.public");

		messageDigest.update(original.getBytes());
		byte[] resultByte = messageDigest.digest();

		StringBuffer sb = new StringBuffer();
		for (byte b : resultByte) {
			sb.append(String.format("%02x", b & 0xff));
		}

		return sb.toString();
	}

	/**
	 * @return public key defined in properties file
	 */
	public String getPublicKey() {
		return prop.getProperty("key.public");
	}

	/**
	 * @param keysFile
	 */
	public MarvelInfoSecurity(String keysFile) {
		try (InputStream input = new FileInputStream(keysFile)) {

			// load a properties file
			prop.load(input);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		MarvelInfoSecurity s = new MarvelInfoSecurity(new MarvelInfoConfiguration().getKeysFile());
		System.out.println(s.getHash("1"));
		System.out.println(s.getHash("2"));
		System.out.println(s.getHash("3"));
	}

}
