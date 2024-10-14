package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixBillingAddressPage;
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
 * @since 28-Oct-2024
 */

public class PhoenixBillingAddressSmoke extends DriverBase {
	/**
	 *
	 * @param url      an absolute URL giving the base location of the image
	 * @param email    an absolute URL giving the base location of the image
	 * @param password the location of the image, relative to the url argument
	 * @return Nothing
	 * @throws InterruptedException
	 */

	public void commanCodeForAddToCart(String productName, String productColor, String email)
			throws InterruptedException {

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
		// Click on checkout button
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);
		// Guest login
		phoenixCheckoutLoginPage.enterValueInEmail(email);
		Thread.sleep(1000);
		phoenixCheckoutLoginPage.clickOnContinueOnGuesButton();

	}
	@Test(priority = 1)
	@Parameters({"url", "productName", "productColor", "email", "firstNameBilling", "lastNameBilling", "addressLineOneBilling", "addressLineTwoBilling", "zipCodeBilling", "phoneBilling", "cardNumber", "expiryDate", "securityCode", "nameOnCard"})
	public void verifyBillingAddress(String url, String productName, String productColor, String email, String firstNameBilling, String lastNameBilling, String addressLineOneBilling, String addressLineTwoBilling, String zipCodeBilling, String phoneBilling, String cardNumber, String expiryDate, String securityCode, String nameOnCard) throws Exception {
		
		extentTestLogger = extent.createTest("Verify Payment with Adyen payment Getway");
		driver.get(url);
		// Perform Click on Accept Cookie Button and My Account Icon
		commanCodeForAddToCart(productName, productColor, email);
		Thread.sleep(3000);
		PhoenixShippingAddressPage phoenixShippingAddressPage = new PhoenixShippingAddressPage(driver);
		phoenixShippingAddressPage.enterValueInShippingAddress(firstNameBilling, lastNameBilling, addressLineOneBilling, addressLineTwoBilling, zipCodeBilling, phoneBilling);
		Thread.sleep(3000);
		// Enter billing address
		
		PhoenixBillingAddressPage phoenixBillingAddressPage = new PhoenixBillingAddressPage(driver);
		phoenixBillingAddressPage.enterBillingAddress(firstNameBilling, lastNameBilling, addressLineOneBilling, addressLineTwoBilling, zipCodeBilling, phoneBilling, cardNumber, expiryDate, securityCode, nameOnCard);
		
		// Popup handel
		phoenixBillingAddressPage.handleSavePasswordPopup();
		
		String actualThankYouText = phoenixBillingAddressPage.getThankYouText();
		String expectedThankYouText = "Thank you for the order.";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualThankYouText, expectedThankYouText, "The user is not on thank you page ");
		sa.assertAll();
		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Thank You text is verified successfully", actualThankYouText, expectedThankYouText));
	}

}
