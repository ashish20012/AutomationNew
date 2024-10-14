package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ranosys.commons.DriverBase;
import com.ranosys.phoenix.page.PhoenixForgotPasswordPopUpPage;
import com.ranosys.phoenix.page.PhoenixHomePage;

/**
 * Phoenix Forgot Password Popup smoke testing
 * 
 * @author Ashish Kumar
 * @version 1.0
 * @since 05-08-2024
 *
 */

public class PhoenixForgotPasswordSmoke extends DriverBase {
	
	@Test(priority = 1)
	@Parameters({"url"})
	public void verifyForgotPasswordText(String url) {
		
		extentTestLogger = extent.createTest("Forgot Password Popup verification");
		driver.get(url);
		//Launching the home page
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		PhoenixForgotPasswordPopUpPage phoenixForgotPasswordPopupPage = new PhoenixForgotPasswordPopUpPage(driver);
		phoenixForgotPasswordPopupPage.clickOnForgotPasswordLink();
		//Assertion
		String actualText = phoenixForgotPasswordPopupPage.verifyForgotPasswordTextOnPopup();
		String expectedText = "Forgot Password?";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText, "Actual text is not as per the Expected text");
		sa.assertAll();
	}
	
	@Test(priority = 2)
	@Parameters({"url", "forgotEmail"})
	public void enterEmailOnForgotEmail(String url, String forgotEmail) {
		
		extentTestLogger = extent.createTest("Enter email on forgot password");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		PhoenixForgotPasswordPopUpPage phoenixForgotPasswordPopupPage = new PhoenixForgotPasswordPopUpPage(driver);
		phoenixForgotPasswordPopupPage.clickOnForgotPasswordLink();
		phoenixForgotPasswordPopupPage.enterEmailOnForgotPassword(forgotEmail);
		phoenixForgotPasswordPopupPage.clickOnSendButton();
		
	}

}
