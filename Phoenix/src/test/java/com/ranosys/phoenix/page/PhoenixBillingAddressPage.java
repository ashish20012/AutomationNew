package com.ranosys.phoenix.page;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixBillingAddressPage {

	final WebDriver driver;

	@FindBy(xpath = "//button[@value='submit-payment']")
	WebElement placeOrderButton;

	@FindBy(xpath = "//div[@class='selectric-wrapper selectric-form-control selectric-floating-input selectric-floating-select selectric-shippingCountry']")
	WebElement newAddressDropDown;

	@FindBy(id = "billingFirstName")
	WebElement firstNameBilling;

	@FindBy(id = "billingLastName")
	WebElement lastNameBilling;

	@FindBy(id = "billingAddressOne")
	WebElement addressLineOneBilling;

	@FindBy(id = "billingAddressTwo")
	WebElement addressLineTwoBilling;

	@FindBy(id = "billingZipCode")
	WebElement zipCodeBilling;

	@FindBy(id = "phoneNumber")
	WebElement phoneBilling;

	@FindBy(xpath = "//a[@class='nav-link adyen-tab active']")
	WebElement adynpayment;

	@FindBy(xpath = "//input[starts-with(@id,'adyen-checkout-encryptedCardNumber-')]")
	WebElement creditCardNumber;

	@FindBy(xpath = "//iframe[@title='Iframe for card number'][@class='js-iframe']")
	WebElement cardNumberIfrme;

	@FindBy(xpath = "//iframe[@title='Iframe for expiry date'][@class='js-iframe']")
	WebElement expiryDateIframe;

	@FindBy(xpath = "//input[starts-with(@id,'adyen-checkout-encryptedExpiryDate-')]")
	WebElement expiryDateInput;

	@FindBy(xpath = "//iframe[@title='Iframe for security code'][@class='js-iframe']")
	WebElement securityCodeiframe;

	@FindBy(xpath = "//input[starts-with(@id,'adyen-checkout-encryptedSecurityCode-')]")
	WebElement securityCodeInput;

	@FindBy(xpath = "//input[starts-with(@id,'adyen-checkout-holderName-')]")
	WebElement nameOnCreditCard;

	@FindBy(css = "h2.order-thank-you-msg")
	WebElement thankYouText;

	public PhoenixBillingAddressPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
	}

	public void enterBillingAddress(String fristNameBilling, String lastNameBilling, String addressLineOneBilling,
			String addressLineTwoBilling, String zipCodeBilling, String phoneBilling, String cardNumber,
			String expiryDate, String securityCode, String nameOnCard) throws InterruptedException {

//		// Select New Address On Billing
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		WebElement selectricDropdownNewAddress = driver
//				.findElement(By.xpath("//span[@for='billingAddressSelector']/..//div[@class='selectric']"));
//		selectricDropdownNewAddress.click();
//		Thread.sleep(2000);
//		// Wait for the dropdown options to be visible (you might need to adjust this
//		// locator)
//		WebElement optionAddress = driver.findElement(
//				By.xpath("//span[@for='billingAddressSelector']/..//ul/li[contains(text(),'New Address')]"));
//		optionAddress.click();
//		Thread.sleep(3000);
//
//		// First Name Billing
//		firstNameBilling.clear();
//		firstNameBilling.sendKeys(fristNameBilling);
//
//		// LastName Billing
//		this.lastNameBilling.clear();
//		this.lastNameBilling.sendKeys(lastNameBilling);
//
//		// Address Line
//		this.addressLineOneBilling.clear();
//		this.addressLineOneBilling.sendKeys(addressLineOneBilling);
//		this.addressLineTwoBilling.clear();
//		this.addressLineTwoBilling.sendKeys(addressLineTwoBilling);
//
//		// Country dropdown
//		WebElement selectricDropdownCountry = driver.findElement(
//				By.xpath("//div[@class='selectric-wrapper selectric-form-control selectric-floating-select selectric-floating-input selectric-billingCountry']//div[@class='selectric']"));
//		// Click to open the dropdown
//		selectricDropdownCountry.click();
//		Thread.sleep(2000);
//		// Select country
//		//div[@class='selectric-wrapper selectric-form-control selectric-floating-select selectric-floating-input selectric-billingCountry']//div[@class='selectric-items']//div//ul//li[contains(text(),'Singapore')]
//		WebElement optionCountry = driver.findElement(By.xpath(
//				"//*[@id=\"dwfrm_billing\"]/fieldset[1]/fieldset[2]/div[4]/div[1]/div/div[1]/div[3]/div/ul/li[2]"));
//		optionCountry.click();
//		//wait.until(ExpectedConditions.elementToBeSelected(optionCountry));
//		
//
//		// ZIP Code
//		this.zipCodeBilling.clear();
//		this.zipCodeBilling.sendKeys(zipCodeBilling);
//
//		// Phone Number
//		this.phoneBilling.clear();
//		this.phoneBilling.sendKeys(phoneBilling);

		// Click on Payment getway
		Thread.sleep(3000);
		this.adynpayment.click();

		// Enter Card Number
		Thread.sleep(3000);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(cardNumberIfrme));
		// driver.switchTo().frame(cardNumberIfrme);
		this.creditCardNumber.sendKeys(cardNumber);
		driver.switchTo().defaultContent();

		// Enter Expiry Date
		// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(expiryDateIframe));
		this.expiryDateInput.sendKeys(expiryDate);
		driver.switchTo().defaultContent();

		// Enter Security Code
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(securityCodeiframe));
		this.securityCodeInput.sendKeys(securityCode);
		driver.switchTo().defaultContent();

		// Name On Card
		this.nameOnCreditCard.sendKeys(nameOnCard);
		driver.switchTo().defaultContent();

		// Place Order button
		wait.until(ExpectedConditions.visibilityOf(placeOrderButton));
		placeOrderButton.click();

	}

	// Verify Thank you text on order history
	public String getThankYouText() {
		return thankYouText.getText();
	}

	public void handleSavePasswordPopup() throws Exception {
		Robot robot = new Robot();
		robot.delay(2000); // Wait for the popup to appear

		// Press 'Tab' key to shift focus if necessary
		robot.keyPress(KeyEvent.VK_TAB);
		robot.keyRelease(KeyEvent.VK_TAB);

		// Press 'Enter' to dismiss the save password prompt
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

}
