package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixLoginPage;
import com.ranosys.phoenix.page.PhoenixPostLoginHomePage;
import com.ranosys.phoenix.page.PhoenixWishlistPage;

/**
 * This Class is the Page Object Model Representation of Wishlist Test Cases
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 28-07-2024
 */

public class PhoenixWishlistSmoke extends DriverBase {
	
	@Test(priority = 1)
	@Parameters({"url", "email", "password"})
	public void verifyWishlistAddProductFromPLP(String url, String email, String password) throws InterruptedException {
		
		extentTestLogger = extent.createTest("Navigate to the wishlist page from PLP");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		//phoenixHomePage.clickOnMyAccountIcon();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishlistOnPLP();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		String expectedText = "My Account";
		String actualText = phoenixWishlistPage.verifyBreadCrumb();
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualText, expectedText, "Actual is not as per the Expected");
		sa.assertAll();
	}
	@Test(priority = 2)
	@Parameters({"url", "email", "password"})
	public void verifyContinueShoppingButtonIfnoProducts(String url, String email, String password) throws InterruptedException {
		extentTestLogger = extent.createTest("Navigate to the wishlist page from PLP");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishListIconOnHeader();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);	
		Thread.sleep(2000);
		phoenixWishlistPage.clickOnWishListIconOnHeader();
		Thread.sleep(2000);
		boolean isDisplayed = phoenixWishlistPage.checkIsDisplayShippingbutton();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(isDisplayed, "The button should be displayed.");
	}
	
	@Test(priority = 3)
	@Parameters({"url", "expectedLoginUrl"})
	public void verifyWishlistWithoutLogin(String url, String expectedLoginUrl) throws InterruptedException {
		extentTestLogger = extent.createTest("Navigate to the wishlist page from PLP");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishListIconOnHeader();
		//PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		String actualUrl = phoenixWishlistPage.getLoginUrl();
		String expectedUrl = expectedLoginUrl;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualUrl, expectedUrl, "User is not on login page");
		sa.assertAll();
	}
	@Test(priority = 4)
	@Parameters({"url", "email", "password", "wishlistEmptyMessageText"})
	public void removeProductFromWishlist(String url, String email, String password, String wishlistEmptyMessageText) throws InterruptedException{
		
		extentTestLogger = extent.createTest("Remove product from wishlist");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		//phoenixHomePage.clickOnMyAccountIcon();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishlistOnPLP();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		phoenixWishlistPage.clickOnWishListIconOnHeader();
		Thread.sleep(2000);
		phoenixWishlistPage.clickOnRemoveButton();
		Thread.sleep(2000);
		String expectedMessage = phoenixWishlistPage.emptyCartMessageText();
		String actualMessage = wishlistEmptyMessageText;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualMessage, expectedMessage, "Actual is not as per the expected message.");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Verify that the message is correct", actualMessage, expectedMessage));
		
	}
	
	
	
	

}
