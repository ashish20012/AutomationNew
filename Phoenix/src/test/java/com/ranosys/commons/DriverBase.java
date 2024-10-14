package com.ranosys.commons;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

import com.ranosys.commons.ZipFolder;

/**
 * This Class have common functions which can be use when we run a test case
 *
 * @author Arif Khan
 * @version 1.0
 * @since 23-07-2024
 */
public class DriverBase {

	// Get Console Logger instance to log messages in console
	private static final Logger LOGGER = Logger.getLogger(DriverBase.class.getName());

	// Set Test Result Count
	private int passedTestCasesCount = 0;

	private int failedTestCasesCount = 0;

	private int skippedTestCasesCount = 0;

	// Initialize Folder Path Variables
	protected String test_screenshot_folder_path;

	protected String test_report_folder_path;

	protected String test_output_folder_path;

	protected String emailable_report_file_name_path;

	// Initialize Other Variables
	protected ConfigLoader configLoader;

	protected static WebDriver driver;

	protected static ExtentSparkReporter htmlReporter;

	protected static ExtentReports extent;

	protected static ExtentTest extentTestLogger;

	/**
	 * setUpSuit() method run before loading any test suit and will setup all the
	 * required configuration for test suit like 1. Load properties file 2. Delete
	 * All existing Test Cases Reports 3. Create Test Cases Report Folders
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 */
	@BeforeSuite(alwaysRun = true)
	public void setUpSuit() {
		// This will load All configuration of Phoenix 2 defined in config.properties
		// file
		configLoader = new ConfigLoader();

		/*------------------------< Start Deleting All existing Test Data >-----------------------*/
		test_screenshot_folder_path = PhonixUtility.getPathFromConfigFile(configLoader, "test_screenshot_folder");
		File test_screenshot_folder = new File(test_screenshot_folder_path);

		test_report_folder_path = PhonixUtility.getPathFromConfigFile(configLoader, "test_report_folder");
		File test_report_folder = new File(test_report_folder_path);

		test_output_folder_path = PhonixUtility.getPathFromConfigFile(configLoader, "test_output_folder");
		File test_output_folder = new File(test_output_folder_path);

		emailable_report_file_name_path = PhonixUtility.getPathFromConfigFile(configLoader,
				"emailable_report_file_name");
		File emailable_report_file_name = new File(emailable_report_file_name_path);

		try {
			if (test_screenshot_folder.exists()) {
				FileUtils.deleteDirectory(test_screenshot_folder);
			}
			if (test_report_folder.exists()) {
				FileUtils.deleteDirectory(test_report_folder);
			}
			if (test_output_folder.exists()) {
				FileUtils.deleteDirectory(test_output_folder);
			}
			if (emailable_report_file_name.exists()) {
				emailable_report_file_name.delete();
			}
		} catch (IOException e) {
			LOGGER.info(e.getMessage());
		}

		/*------------------------< / End Deleting All existing Test Data >-----------------------*/

		/*---------------------------< Start Creating Test Data Folders >-------------------------*/
		new File(test_output_folder_path).mkdir();
		new File(test_screenshot_folder_path).mkdir();
		/*---------------------------< / End Creating Test Data Folders >-------------------------*/
	}

	/**
	 * setUpClass() method runs before any test case class execute. It creates
	 * ExtentReport object and configure reports configuration
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param ctx
	 */
	@BeforeClass(alwaysRun = true)
	public void setUpClass(ITestContext ctx) {

		/*---------------------< Start Setup Extent Reporting for a Test Class >-------------------*/

		String className = this.getClass().getSimpleName();
		String extentReportPath = PhonixUtility.createPath(test_output_folder_path, className + ".html");
		htmlReporter = new ExtentSparkReporter(extentReportPath);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Environment", "Automation Testing");

		try {
			htmlReporter.loadXMLConfig("src/test/resources/extent-config.xml");
		} catch (IOException e) {
			e.printStackTrace();
			LOGGER.info("Unable to load extent-config.xml " + e.getMessage());
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

		htmlReporter.config().setReportName("Test - " + className);

		/*---------------------< / End Setup Extent Reporting for a Test Class >-------------------*/
	}
	
	/**
	 * startTest() Method executes before any Test method start executing. This Method initialize Web Driver
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @return Void
	 * 
	 */
	@BeforeMethod(alwaysRun = true)
	public void startTest()
	{
		/*------------------------------< Start Loading Browser Drivers >-------------------------*/
		try {
			String browserType = configLoader.getProperty("default_browser_type");

			// If browser property available while executing or building the code
			if (System.getProperty("browser") != null) {
				browserType = System.getProperty("browser");
			}

			LOGGER.info("Browser type is " + browserType);

			try {
				if (browserType.equalsIgnoreCase(configLoader.getProperty("edge_broswer"))) {
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					driver.manage().window().maximize();

				} else if (browserType.equalsIgnoreCase(configLoader.getProperty("chrome_broswer"))) {
					ChromeOptions chromeOptions = new ChromeOptions();
//					chromeOptions.addArguments("--headless");
//					chromeOptions.addArguments("window-size=1920,1080");
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver(chromeOptions);
					driver.manage().window().maximize();
				} else {
					throw new IllegalArgumentException("Unknown driver specified");
				}

			} catch (WebDriverException e) {
				LOGGER.info("Unable to launch web browser " + e.getMessage());
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
		}

		/*------------------------------< / End Loading Browser Drivers >-------------------------*/
	}

	/**
	 * getResult() method run after a test case method has been executed. This
	 * method will log all Failure, Skip and Success messages into console and
	 * extent reports. If the test cases method failed then this method screenshot
	 * web browser screen and and attach into extent reports. This Method also close the Webdriver
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 * 
	 * @param result
	 */
	@AfterMethod(alwaysRun = true)
	public void getResult(ITestResult result) {
		// If Result status failed
		if (result.getStatus() == ITestResult.FAILURE) {
			extentTestLogger.log(Status.FAIL, result.getMethod().getMethodName());
			extentTestLogger.log(Status.FAIL, result.getThrowable());

			String screenshotPath;
			try {
				screenshotPath = Multimedia.getWebBrowserScreenshot(test_screenshot_folder_path,
						result.getMethod().getMethodName(), driver);
				extentTestLogger.addScreenCaptureFromPath("../." + screenshotPath);
			} catch (Exception e) {
				LOGGER.info(e.getMessage());
			}

			failedTestCasesCount++;

			LOGGER.info("Number of failed test cases " + failedTestCasesCount);

		} else if (result.getStatus() == ITestResult.SKIP) {
			// If Result status SKIP
			extentTestLogger.log(Status.SKIP, result.getMethod().getMethodName());
			skippedTestCasesCount++;
			LOGGER.info("Number of skiped test cases " + skippedTestCasesCount);

		} else if (result.getStatus() == ITestResult.SUCCESS) {
			// If Result status is success
			passedTestCasesCount++;
			extentTestLogger.log(Status.PASS, result.getMethod().getMethodName() + "Is Successfully Passes");
			LOGGER.info("Number of passed test cases " + passedTestCasesCount);
		}
		
		// Close WebDriver
		//driver.close();
	}

	/**
	 * endReport() method runs when a test class has been executed. It is use for
	 * flush the extent report instance
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 */
	@AfterClass(alwaysRun = true)
	public void endReport() {
		extent.flush();
	}

	/**
	 * tearDown() method call after test suit has been completed. This method
	 * creates zip file of reports folder
	 * 
	 * @author Arif Khan
	 * @version 1.0
	 * @since 23-07-2024
	 */
	@AfterSuite
	public void tearDown() {
		
		// Create Zip of Report Folder
		ZipFolder zipFolder = new ZipFolder(test_report_folder_path,emailable_report_file_name_path);
		zipFolder.zipFile();
	}
}
