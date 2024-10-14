package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixCheckoutLoginPage;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixMiniCartPage;
import com.ranosys.phoenix.page.PhoenixPLPPage;
import com.ranosys.phoenix.page.PhoenixShippingAddressPage;

/**
 * Checkout process doing the Smoke Testing
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 26-Oct-2024
 */

public class PhoenixShippingAddressSmoke extends DriverBase{
	
	/**
	 *
	 * @param url      an absolute URL giving the base location of the image
	 * @param email    an absolute URL giving the base location of the image
	 * @param password the location of the image, relative to the url argument
	 * @return Nothing
	 * @throws InterruptedException 
	 */
	public void commanCodeForAddToCart(String productName, String productColor, String email) throws InterruptedException {
		
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
		//Click on checkout button
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);
		//Guest login
		phoenixCheckoutLoginPage.enterValueInEmail(email);
		phoenixCheckoutLoginPage.clickOnContinueOnGuesButton();
		
	}
	
	
	@Test(priority = 1)
	@Parameters({ "url", "productName", "productColor", "email", "firstName", "secondName", "addressLineOne", "addressLineTwo", "zipCode", "phoneNumber"})
	public void enterShippingAddress(String url, String productName, String productColor, String email, String firstName, String secondName, String addressLineOne, String addressLineTwo, String zipCode, String phoneNumber) throws InterruptedException {

		extentTestLogger = extent.createTest("Verify view cart button on mini cart");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		commanCodeForAddToCart(productName, productColor, email);
		//Thread.sleep(9000);
		PhoenixShippingAddressPage phoenixShippingAddressPage = new PhoenixShippingAddressPage(driver);
		phoenixShippingAddressPage.enterValueInShippingAddress(firstName, secondName, addressLineOne, addressLineTwo, zipCode, phoneNumber);
		
		String actualBillingText = phoenixShippingAddressPage.getBillingText();
		String expectedBillingText = "Billing Address";
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualBillingText, expectedBillingText, "The user is not on shipping page ");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Shipping text is verified successfully", actualBillingText, expectedBillingText));
		
	}

}
