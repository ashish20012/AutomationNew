package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.ranosys.commons.DriverBase;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixLoginPage;
import com.ranosys.phoenix.page.PhoenixPostLoginHomePage;
import com.ranosys.phoenix.page.PhoenixWishlistEditPage;
import com.ranosys.phoenix.page.PhoenixWishlistPage;

/**
 * This Class is the Page Object Model Representation of Wishlist edit Test Cases
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 01-08-2024
 */

public class PhoenixWishlistEditSmoke extends DriverBase {

	@Test(priority = 1)
	@Parameters({ "url", "email", "password" })
	public void verifyEditWishlistButton(String url, String email, String password) throws InterruptedException {

		extentTestLogger = extent.createTest("Verify edit button on wishlist popup");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		// phoenixHomePage.clickOnMyAccountIcon();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishlistOnPLP();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		phoenixWishlistPage.clickOnWishListIconOnHeader();

		PhoenixWishlistEditPage phoenixWishlistEditPage = new PhoenixWishlistEditPage(driver);
		Thread.sleep(2000);
		phoenixWishlistEditPage.clickOnEditButton();
		String myWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(myWindowHandle);
		Thread.sleep(2000);
		phoenixWishlistEditPage.clickOnColorSwatch();
		Thread.sleep(2000);
		phoenixWishlistEditPage.clickOnSize();
		phoenixWishlistEditPage.clickOnUpdateButton();
	}

	@Test(priority = 2)
	@Parameters({ "url", "email", "password" })
	public void verifyClickOnSelectAttribute(String url, String email, String password) throws InterruptedException {

		extentTestLogger = extent.createTest("Verify select attribute button on wishlist");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixWishlistPage phoenixWishlistPage = new PhoenixWishlistPage(driver);
		phoenixWishlistPage.clickOnWishlistOnPLP();
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		phoenixWishlistPage.clickOnWishListIconOnHeader();
		PhoenixWishlistEditPage phoenixWishlistEditPage = new PhoenixWishlistEditPage(driver);
		phoenixWishlistEditPage.clickOnSelectAttribute();
		Thread.sleep(2000);
		phoenixWishlistEditPage.clickOnColorSwatch();
		Thread.sleep(2000);
		phoenixWishlistEditPage.clickOnSize();
		phoenixWishlistEditPage.clickOnUpdateButton();
	}
}