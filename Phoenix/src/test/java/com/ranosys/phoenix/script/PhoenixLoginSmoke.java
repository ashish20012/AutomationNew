package com.ranosys.phoenix.script;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixLoginPage;

/**
 * This Class is the Page Object Model Representation of Login Page Test Cases
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 28-07-2024
 */

public class PhoenixLoginSmoke extends DriverBase {

	@Test(priority = 1)
	@Parameters({ "url", "email" })
	public void loginWithCorrectUserNameIncorrectPassword(String url, String email) throws InterruptedException {

		extentTestLogger = extent.createTest("Login With Correct UserName And Incorrect Password");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);

		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login with correct username and incorrect password
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, "123456789");
		String actualPasswordValidation = phoenixLoginPage.getValidationTextIfPasswordIncorrect();
		String expectedPasswordValidation = "Invalid login or password. Remember that password is case-sensitive. Please try again.";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualPasswordValidation, expectedPasswordValidation, "Validation message is not matched");
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Verify that validation message is correct.", actualPasswordValidation, expectedPasswordValidation));
		sa.assertAll();
	}

	@Test(priority = 2)
	@Parameters({ "url", "password" })
	public void loginWithInCorrectUserNameCorrectPassword(String url, String password) {

		extentTestLogger = extent.createTest("Login With InCorrect UserName And Correct Password");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login with Incorrect username and correct password
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin("kuma@nosys.com", password);
		String actualUserNameValidation = phoenixLoginPage.getValidationTextIfUsernameIncorrect();
		String expectedUserNameValidation = "This email doesn't exist in our system, please create an account first.";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualUserNameValidation, expectedUserNameValidation, "Validation message is not matched");
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Verify that validation message is correct.", actualUserNameValidation, expectedUserNameValidation));
		sa.assertAll();

	}

	@Test(priority = 3)
	@Parameters({ "url" })
	public void loginWithInCorrectUserNameIncorrectPassword(String url) {

		extentTestLogger = extent.createTest("Login With InCorrect UserName And Incorrect Password");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login with Incorrect username and Incorrect password
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin("kumar.ashshranosys.com", "123456789");
		String actualValidationMessage = phoenixLoginPage.getValidationTextIfBothIncorrect();
		String expectedValidationMessage = "Please include an '@' in the email address. 'kumar.ashshranosys.com' is missing an '@'.";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualValidationMessage, expectedValidationMessage, "Validation message is not correct");
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Verify that validation message is correct.", actualValidationMessage, expectedValidationMessage));
		sa.assertAll();
	}

	@Test(priority = 4)
	@Parameters({ "url" })
	public void loginWithBlankUserNameBlankPassword(String url) {

		extentTestLogger = extent.createTest("Login With Blank UserName And Blank Password");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);

		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login with blank username and blank password
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(" ", " ");
		String actualEmailValidationMessage = phoenixLoginPage.getValidationTextIfEmailIsBlank();
		String expectedEmailValidationMessage = "Please enter Email";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualEmailValidationMessage, expectedEmailValidationMessage,
				"Validation message is not correct");
		extentTestLogger.log(Status.PASS,
				PhonixUtility.formatTestSuccessMessage("Verify that validation message is correct.",
						actualEmailValidationMessage, expectedEmailValidationMessage));
		sa.assertAll();

	}

	@Test(priority = 5)
	@Parameters({ "url", "email", "password" })
	public void loginWithCorrectCredentials(String url, String email, String password) {

		extentTestLogger = extent.createTest("Login Test with Valida Email and Password");
		// We are Moving getDriver() method into @BeforeSuit Annotations
		// driver = getDriver();
		driver.get(url);

		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login with correct username and correct password
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		phoenixLoginPage.homePageAssertAfterLogin();
		String actualMyAccountMessage = phoenixLoginPage.homePageAssertAfterLogin();
		String expectedMyAccountMessage = "My Account";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualMyAccountMessage, expectedMyAccountMessage, "Text is not correct");
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Verify that the text is correct.",
				actualMyAccountMessage, expectedMyAccountMessage));
		sa.assertAll();

	}

	@Test(priority = 6)
	@Parameters({ "url", "expectedTermsAndConditionsUrl" })
	public void verifyTermsAndConditionsLink(String url, String expectedTermsAndConditionsUrl) {

		extentTestLogger = extent.createTest("Terms and conditions link verification.");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();

		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.clickOnTermsAndConditions();
		
		//Switch one tab to another
		Set<String> windowHandles = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<>(windowHandles);
		driver.switchTo().window(windowHandlesList.get(1));
		String actualUrl = phoenixLoginPage.getCurrentUrl();
		String expectedUrl = expectedTermsAndConditionsUrl;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualUrl, expectedUrl, "Actual Terms And Conditions URL is not as per expected");
		sa.assertAll();
	}
	
	@Test(priority = 7)
	@Parameters({ "url", "expectedCreateAnAccountUrl" })
	public void verifyCreateAnAccountButton(String url, String expectedCreateAnAccountUrl) {

		extentTestLogger = extent.createTest("Create an account link verification.");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();

		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.clickOnCreateAnAccountButton();
		String actualUrl = phoenixLoginPage.getCurrentUrl();
		String expectedUrl = expectedCreateAnAccountUrl;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualUrl, expectedUrl, "Actual Create and Account URL is not as per expected");
		sa.assertAll();
	}
	@Test(priority = 8)
	@Parameters({"url"})
	public void verifyForgotPassword(String url) throws InterruptedException {
		
		extentTestLogger = extent.createTest("Forgot password verification");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.clickOnForgotPasswordLink();
		
	}

}
