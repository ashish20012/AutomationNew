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

/**
 * PhoenixPLPSmoke Class doing the Smoke Testing of PLP Page
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 14-08-2024
 */

public class PhoenixCheckoutLoginSmoke extends DriverBase {

	@Test(priority = 1)
	@Parameters({ "url", "email", "productName", "productColor", "expectedEmailValidationGuestLogin", "productSize" })
	public void verifyBlankEmailAndClickOnContinueAsGuestUserButton(String url, String email, String productName,
			String productColor, String expectedEmailValidationGuestLogin, String productSize) {

		extentTestLogger = extent
				.createTest("Verify continue as guest button without entering email field on checkout");
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
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);

		// Click on checkout button
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();

		// ViewCart
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);
		phoenixCheckoutLoginPage.enterValueInEmail(" ");
		phoenixCheckoutLoginPage.clickOnContinueOnGuesButton();
		String actualEmailValidation = phoenixCheckoutLoginPage.getEmailValidationMessage();
		String expectedEmailValidation = expectedEmailValidationGuestLogin;

		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualEmailValidation, expectedEmailValidation,
				"Actual email validation message is not as per the expected.");
		sa.assertAll();

		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage(
				"Validation message is verified if the user click on continue as guest button without entering email",
				actualEmailValidation, expectedEmailValidation));
	}

	@Test(priority = 2)
	@Parameters({ "url", "email", "productName", "productColor", "productSize" })
	public void verifyContinueAsGuestButton(String url, String email, String productName, String productColor, String productSize) {

		extentTestLogger = extent.createTest("Verify continue as guest button on mini cart");
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
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);

		// Click on checkout button
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();

		// ViewCart
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);
		phoenixCheckoutLoginPage.enterValueInEmail(email);
		phoenixCheckoutLoginPage.clickOnContinueOnGuesButton();
		String actualShippingText = phoenixCheckoutLoginPage.getShippingText();
		String expectedShippingText = "Shipping";
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualShippingText, expectedShippingText, "Actual shipping text is not as per the expected.");
		sa.assertAll();

		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Shipping text verified successfully",
				actualShippingText, expectedShippingText));

	}
	
	
	@Test(priority = 3)
	@Parameters({ "url", "email", "productName", "productColor", "couponCodeTextBoxOnGuestLogin", "couponCodeSuccessMessage", "productSize" })
	public void verifyCouponCode(String url, String email, String productName, String productColor, String couponCodeTextBoxOnGuestLogin, String couponCodeSuccessMessage, String productSize) {

		extentTestLogger = extent.createTest("Verify apply coupon code on guest login button and verify success message");
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
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);

		// Click on checkout button
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();

		// ViewCart
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);
//		phoenixCheckoutLoginPage.enterValueInEmail(email);
//		phoenixCheckoutLoginPage.clickOnContinueOnGuesButton();
		phoenixCheckoutLoginPage.verifyPromoCodeTextBox(couponCodeTextBoxOnGuestLogin);
		phoenixCheckoutLoginPage.clickOnApplyButton();
		
		String actualSuccessMessage = phoenixCheckoutLoginPage.verifyCouponSuccessmessage();
		String expectedSuccessMessage = couponCodeSuccessMessage;
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Actual success message is not as per the expected.");
		sa.assertAll();

		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Success message verified successfully",
				actualSuccessMessage, expectedSuccessMessage));

	}
	
	@Test(priority = 4)
	@Parameters({ "url", "email", "productName", "productColor", "couponCodeTextBoxOnGuestLogin", "couponCodeValidationMessage" })
	public void verifyCouponCodeValidationMessage(String url, String email, String productName, String productColor, String couponCodeTextBoxOnGuestLogin, String couponCodeValidationMessage, String productSize) {

		extentTestLogger = extent.createTest("Verify apply coupon code on guest login button and verify validation message");
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
		PhoenixMiniCartPage phoenixMiniCartPage = new PhoenixMiniCartPage(driver);

		// Click on checkout button
		phoenixMiniCartPage.clickOnCheckoutButtonOnMiniCart();

		// ViewCart
		PhoenixCheckoutLoginPage phoenixCheckoutLoginPage = new PhoenixCheckoutLoginPage(driver);

		phoenixCheckoutLoginPage.verifyPromoCodeTextBox(" ");
		phoenixCheckoutLoginPage.clickOnApplyButton();
		
		String actualValMessage = phoenixCheckoutLoginPage.verifyPromoCodeValidationMessage();
		String expectedValMessage = couponCodeValidationMessage;
		
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualValMessage, expectedValMessage, "Actual validation message is not as per the expected.");
		sa.assertAll();

		extentTestLogger.log(Status.PASS, PhonixUtility.formatTestSuccessMessage("Validation message verified successfully",
				actualValMessage, expectedValMessage));

	}

}
