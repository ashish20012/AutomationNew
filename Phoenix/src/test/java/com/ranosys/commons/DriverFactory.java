package com.ranosys.commons;

import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	private static final Logger LOGGER = Logger.getLogger(DriverFactory.class.getName());

	private static WebDriver webDriver;

	private static final String CHROME_BROWSER = "CHROME";

	private static final String EDGE_BROWSER = "EDGE";

	public WebDriver getDriver(String browserType) {
		try {
			if (browserType.equalsIgnoreCase(EDGE_BROWSER)) {
				WebDriverManager.edgedriver().setup();
				webDriver = new EdgeDriver();
				webDriver.manage().window().maximize();

			} else if (browserType.equalsIgnoreCase(CHROME_BROWSER)) {
				ChromeOptions chromeOptions = new ChromeOptions();
				WebDriverManager.chromedriver().setup();
				webDriver = new ChromeDriver(chromeOptions);
				webDriver.manage().window().maximize();
			} else {
				throw new IllegalArgumentException("Unknown driver specified");
			}

		} catch (WebDriverException e) {
			LOGGER.info("Unable to launch web browser " + e.getMessage());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}
		return webDriver;
	}

	public void exitBrowser() {
		webDriver.close();
	}

}
