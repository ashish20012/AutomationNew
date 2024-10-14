package com.ranosys.commons;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * This Class contain all common functions which is related to any multimedia
 * operation
 *
 * @author Arif Khan
 * @version 1.0
 * @since 23-07-2024
 */
public class Multimedia {
	private static final Logger LOGGER = Logger.getLogger(DriverBase.class.getName());

	/**
	 * getWebBrowserScreenshot() method take a screenshot of web browser and store
	 * into test_screenshot_folder_path
	 * 
	 * @param test_screenshot_folder_path Folder where to store screenshot
	 * @param testMethodName              Test method name for which we need to take
	 *                                    screenshot
	 * @param driver                      Web Driver class instance
	 * @return String Screenshot full path with name
	 */
	public static String getWebBrowserScreenshot(String test_screenshot_folder_path, String testMethodName,
			WebDriver driver) {
		
		// Get current timestamp
		String timestamp = Long.toString(new Date().getTime());

		// Initialize screenshot variable
		TakesScreenshot ts;
		ts = (TakesScreenshot) driver;
		
		// Take screenshot and store in file variable
		File screenShotSourceFile = ts.getScreenshotAs(OutputType.FILE);
		
		// Create Screenshot full path
		String screenShotDestination = PhonixUtility.createPath(test_screenshot_folder_path,
				testMethodName + timestamp + ".png");
		
		// Create Empty file at screenShotDestination
		File screenShotDestinationFile = new File(screenShotDestination);
		
		try {
			// Copy screenshot into screenShotDestination from screenShotSourceFile
			FileUtils.copyFile(screenShotSourceFile, screenShotDestinationFile);
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}
		
		return screenShotDestination;
	}
}
