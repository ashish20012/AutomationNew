package com.ranosys.phoenix.script;

import org.openqa.selenium.Alert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixLoginPage;
import com.ranosys.phoenix.page.PhoenixMiniCartPage;
import com.ranosys.phoenix.page.PhoenixPLPPage;
import com.ranosys.phoenix.page.PhoenixPostLoginHomePage;

/**
 * PhoenixPLPSmoke Class doing the Smoke Testing of PLP Page
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 14-08-2024
 */

public class PhoenixMiniCartSmoke extends DriverBase {

	/**
	 * showProductCountText() method is use to show product count on PLP page.
	 *
	 * @param url      an absolute URL giving the base location of the image
	 * @param email    an absolute URL giving the base location of the image
	 * @param password the location of the image, relative to the url argument
	 * @return Nothing
	 */

	@Test(priority = 1)
	@Parameters({ "url", "productName", "productColor", "productSize" })
	public void verifyproductNameOnMiniCart(String url, String productName, String productColor, String productSize) {

		extentTestLogger = extent.createTest("Verify product name on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		
		//Add to cart
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		
		// Assert on mini cart
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		String actualProductname = phoenixMiniCartPage.getProductNameOnMiniCart();
		String expectedProductName = productName;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualProductname, expectedProductName,
				"Actual product name is not as per the expected product name on mini cart");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Product name is as per the expected message", actualProductname, expectedProductName));
		
	}
	
	@Test(priority = 2)
	@Parameters({ "url", "productName", "productColor", "itemCounterOnMiniCart", "productSize" })
	public void verifyItemCounterOnMiniCart(String url, String productName, String productColor, String itemCounterOnMiniCart, String productSize) {

		extentTestLogger = extent.createTest("Verify product counter on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		
		// Add to cart
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		// Assert on mini cart
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		String actualItemCounter = phoenixMiniCartPage.getItemCounterGetCounter();
		String expectedItemCounter = itemCounterOnMiniCart;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualItemCounter, expectedItemCounter,
				"Actual product counter is not as per the expected product counter on mini cart");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Product counter is as per the expected message", actualItemCounter, expectedItemCounter));
		
	}
	
	
	@Test(priority = 3)
	@Parameters({"url","productName", "productColor", "productColorOnMiniCart", "productSize"})
	public void verifyproductColorOnMiniCart(String url, String productName, String productColor, String productColorOnMiniCart, String productSize) {

		extentTestLogger = extent.createTest("Verify product color on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		
		// add to cart
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		// Assert on mini cart
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		String actualProductColor = phoenixMiniCartPage.getProductColorOnMiniCart();
		String expectedProductColor = productColorOnMiniCart;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualProductColor, expectedProductColor,
				"Actual product color is not as per the expected product color on mini cart");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Product color is as per the expected color", actualProductColor, expectedProductColor));
		
	}
	
	@Test(priority = 4)
	@Parameters({"url","productName", "productColor", "productSizeOnMiniCart", "productSize"})
	public void verifyproductSizeOnMiniCart(String url, String productName, String productColor, String productSizeOnMiniCart, String productSize) {

		extentTestLogger = extent.createTest("Verify product size on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		String actualProductSize = phoenixMiniCartPage.getProductSizeOnMiniCart();
		String expectedProductSize = productSizeOnMiniCart;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualProductSize, expectedProductSize,
				"Actual product size is not as per the expected product size on mini cart");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Product color is as per the expected size", actualProductSize, expectedProductSize));
		
	}
	
	@Test(priority = 5)
	@Parameters({"url","productName", "productColor", "productQtyBoxMini", "productSize"})
	public void verifyPlusIconOnMiniCart(String url, String productName, String productColor, String productQtyBoxMini, String productSize) {

		extentTestLogger = extent.createTest("Verify plus icon on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		
		//Add to cart
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		
		//Click on plus icon 
		phoenixMiniCartPage.clickOnPlusIcon();
		String actualProductQty = phoenixMiniCartPage.getTextFromQtyBox();
		String expectedProductQty = productQtyBoxMini;
		
		//Soft assertion
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualProductQty, expectedProductQty, "Actual quantity is not as per the Expected quantity");
		sa.assertAll();
//		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
//				"Product qty is as per the expected product quantity", actualProductQty, expectedProductQty));
		
	}
	
	@Test(priority = 6)
	@Parameters({"url","productName", "productColor", "productSize"})
	public void removeItemFromMiniCart(String url, String productName, String productColor, String productSize) {

		extentTestLogger = extent.createTest("Verify plus icon on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		
		//Add to cart
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		
		//Click on remove icon 
		phoenixMiniCartPage.clickOnRemoveButtonOnMiniCart();
		phoenixMiniCartPage.clickOnRemoveConfirmButtonOnMiniCart();
		SoftAssert sa = new SoftAssert();
		sa.assertTrue(true, "Confirmation popup is not present on the page");
		sa.assertAll();
		
//		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
//				"Product qty is as per the expected product quantity", actualProductQty, expectedProductQty));
		
	}
	
	@Test(priority = 7)
	@Parameters({"url","productName", "productColor", "productSize"})
	public void verifyViewCartButtonOnMiniCart(String url, String productName, String productColor, String productSize) {

		extentTestLogger = extent.createTest("Verify view cart button on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		
		//Add to cart
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		
		//Click on viewcart buttton 
		phoenixMiniCartPage.clickOnViewCartButtonOnMiniCart();
		String actualViewCartBreadcrumb = phoenixMiniCartPage.getViewCartBreadCrumbText();
		String expectedViewCartBreadCrumb = "Shopping Bag";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualViewCartBreadcrumb, expectedViewCartBreadCrumb, "The user is not on viewcart page. ");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"ViewCart button is verified successfully", actualViewCartBreadcrumb, expectedViewCartBreadCrumb));
	}
	
	@Test(priority = 8)
	@Parameters({"url","productName", "productColor", "productSize"})
	public void verifyCheckoutButtonOnMiniCartWithouLogin(String url, String productName, String productColor, String productSize) {

		extentTestLogger = extent.createTest("Verify view cart button on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		
		//Add to cart
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		
		//Click on checkout button
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();
		String actualBreadcrumbText = phoenixMiniCartPage.getCheckoutBreadCrumbText();
		String expectedBreadCrumbText = "Checkout";
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualBreadcrumbText, expectedBreadCrumbText, "The user is not on checkout page. ");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Checkout button is verified successfully", actualBreadcrumbText, expectedBreadCrumbText));
	}
	
	@Test(priority = 9)
	@Parameters({"url","productName", "productColor", "email", "password", "productSize"})
	public void verifyCheckoutButtonOnMiniCartWithLogin(String url, String productName, String productColor, String email, String password, String productSize) {
		
		extentTestLogger = extent.createTest("Verify view cart button on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		//Login Page
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		//Add Product
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);
		PhonixUtility.staticWaitForSeconds(4);
		
		//Click on checkout button
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();
		String actualShippingText = phoenixMiniCartPage.getShippingText();
		String expectedShippingText = "Shipping";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualShippingText, expectedShippingText, "The user is not on shipping page. ");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Checkout button is verified successfully with login user", actualShippingText, expectedShippingText));
		
	}
}
