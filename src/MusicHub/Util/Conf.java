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

}
