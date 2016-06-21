package com.milanoo.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {

	/**
	 * 
	 * @param key
	 * @return
	 * 
	 * 读取配置文件
	 */
	public static String getConfiguration(String key) {
		String value;
		Properties prop = new Properties();
		InputStream in = Global.class
				.getResourceAsStream("configure.properties");
		try {
			prop.load(in);
			value = prop.getProperty(key).trim();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
