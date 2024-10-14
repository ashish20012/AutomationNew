package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixHomePage;

/**
 * This Class is the Page Object Model Representation of Home Page Test Cases
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 23-07-2024
 */

public class PhoenixHomePageSmoke extends DriverBase {

	//private static WebDriver driver;

	@Test(priority = 1)
	@Parameters({"url"})
	public void navigateToHomePage(String url) {

		extentTestLogger = extent.createTest("Load Home Page");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);

		SoftAssert sa = new SoftAssert();
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		// Assert the home page title
		String expTitleOfHomePage = driver.getTitle();
		String actTitleOfHomePage = "Homepage1";
		
		// To print the logs
		sa.assertEquals(actTitleOfHomePage, expTitleOfHomePage, "Verify that Homepage title is correct.");
		extentTestLogger.log(Status.PASS,
				PhonixUtility.formatTestSuccessMessage("Verify that Homepage title is correct.", actTitleOfHomePage, expTitleOfHomePage));

		sa.assertAll();
	}
		
	@Test(priority = 2)
	@Parameters({"url"})
	public void compareTopCategoriesText(String url) {
	
		extentTestLogger = extent.createTest("Verify Top Categories text");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.getTopCategoriesText();
		String expectedTopCategories = phoenixHomePage.getTopCategoriesText();
		String actualTopCategories = "TOP CATEGORIES";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualTopCategories, expectedTopCategories, "Text is not matched.");
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Verify that the message is correct", actualTopCategories, expectedTopCategories));
		sa.assertAll();
	}

}
