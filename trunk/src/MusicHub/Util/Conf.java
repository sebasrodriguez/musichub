package MusicHub.Util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Conf {

	private static int menuWidth;
	private static int menuHeight;
	private static String fontName;
	private static int fontSize;
	private static int itemHeight;
	private static String twitterConsumerKey;
	private static String twitterConsumerSecret;
	private static String twitterAccessToken;
	private static String twitterSecretToken;
	private static String facebookAccessToken;
	private static String facebookId;
	private static String facebookName;

	/*
	 * public Conf(){ }
	 */

	public static void load() {

		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("../config/config.properties"));

			menuWidth = Integer.parseInt(prop.getProperty("menuWidth"));
			menuHeight = Integer.parseInt(prop.getProperty("menuHeight"));
			fontName = prop.getProperty("fontName");
			fontSize = Integer.parseInt(prop.getProperty("fontSize"));
			itemHeight = Integer.parseInt(prop.getProperty("itemHeight"));
			twitterConsumerKey = prop.getProperty("twitterConsumerKey");
			twitterConsumerSecret = prop.getProperty("twitterConsumerSecret");
			twitterAccessToken = prop.getProperty("twitterAccessToken");
			twitterSecretToken = prop.getProperty("twitterSecretToken");
			facebookAccessToken = prop.getProperty("facebookAccessToken");
			facebookId = prop.getProperty("facebookId");
			facebookName = prop.getProperty("facebookName");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int getMenuWidth() {
		return menuWidth;
	}

	public static int getMenuHeight() {
		return menuHeight;
	}

	public static String getFontName() {
		return fontName;
	}

	public static int getFontSize() {
		return fontSize;
	}

	public static int getItemHeight() {
		return itemHeight;
	}

	public static String getTwitterConsumerKey() {
		return twitterConsumerKey;
	}

	public static String getTwitterCosumerSecret() {
		return twitterConsumerSecret;
	}

	public static String getTwitterAccesToken() {
		return twitterAccessToken;
	}

	public static String getTwitterSecretToken() {
		return twitterSecretToken;
	}
	
	public static String getFacebookAccessToken(){
		return facebookAccessToken;
	}
	public static String getFacebookId(){
		return facebookId;
	}
	public static String getFacebookName(){
		return facebookName;
	}
}
