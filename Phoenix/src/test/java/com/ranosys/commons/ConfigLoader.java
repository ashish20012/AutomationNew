package com.ranosys.commons;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * This Class load config properties defined in config.peroperties file
 *
 * @author Arif Khan
 * @version 1.0
 * @since 29-07-2024
 */
public class ConfigLoader {
	// Get Console Logger instance to log messages in console
	private static final Logger LOGGER = Logger.getLogger(DriverBase.class.getName());

	// Create Empty Property List
	private Properties properties = new Properties();

	/**
	 * ConfigLoader() is ConfigLoader class constructor which is use to load
	 * config.properties file
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 29-07-2024
	 */
	public ConfigLoader() {
		InputStream inputStream = null;
		try {
			inputStream = ClassLoader.getSystemResourceAsStream("config.properties");
			if (inputStream != null) {
				properties.load(inputStream);
			} else {
				LOGGER.info("Unable to find properties file");
			}
		} catch (IOException e) {
			LOGGER.info("Error while loading properties file" + e.getMessage());
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				LOGGER.info("Error while closing input stream" + e.getMessage());
			}
		}
	}

	/**
	 * getProperty() : This function is use for get property value from
	 * config.property file
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 29-07-2024
	 * 
	 * @param key : Name of the key
	 * @return String
	 */
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
