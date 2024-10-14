package com.ranosys.commons;

import java.util.logging.Logger;

/**
 * PhonixUtility Class contain method which is common for all Phoenix Project
 * 
 * @author Arif Khan
 * @version 1.0
 * @since 23-07-2024
 */
public class PhonixUtility {
	
	// Get Console Logger instance to log messages in console
	private static final Logger LOGGER = Logger.getLogger(DriverBase.class.getName());

	/**
	 * formatTestSuccessMessage() method return formatted string which needs to log
	 * into extent report
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param title
	 * @param expectedResult
	 * @param actualResult
	 * @return String
	 */
	public static String formatTestSuccessMessage(String title, String expectedResult, String actualResult) {
		return String.format("<b>Test Verification Detail : </b>" + title + "<br />" + "<b>Expected : </b>"
				+ expectedResult + "<br />" + "<b>Actual : </b>" + actualResult);
	}

	/**
	 * createPath() method add file separator between 2 strings or path
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param str1 First Path
	 * @param str2 Second Path
	 * @return String
	 */
	public static String createPath(String str1, String str2) {
		return str1 + System.getProperty("file.separator") + str2;
	}

	/**
	 * getPathFromConfigFile() method will return path from properties file based on
	 * the key name
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param configLoader Instance of ConfigLoader Class
	 * @param configName   Key name in config.properties file
	 * @return String
	 */
	public static String getPathFromConfigFile(ConfigLoader configLoader, String configName) {
		return "." + System.getProperty("file.separator") + configLoader.getProperty(configName);
	}

	/**
	 * staticWaitForSeconds() method will sleep execution for seconds provided in param
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param time This is time value in seconds
	 * @return nothing
	 */
	public static void staticWaitForSeconds(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			LOGGER.info(e.getMessage());
		}
	}

}
