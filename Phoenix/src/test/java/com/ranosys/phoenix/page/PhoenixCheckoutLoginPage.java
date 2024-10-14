package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixCheckoutLoginPage {

	final WebDriver driver;

	@FindBy(id = "email-guest")
	WebElement guestEmail;

	@FindBy(css = ".btn.btn-black.btn-block.submit-customer")
	WebElement continueAsGuestButton;
	
	@FindBy (id = "guestEmailInvalidMessage")
	WebElement emailValidationOnGuestLogin;
	
	@FindBy (xpath ="//label[@class='form-check-label checkout-step-label']")
	WebElement shippingText;
	
	@FindBy (xpath = "//button[@class='btn btn-primary btn-black promo-code-btn']")
	WebElement applyButton;
	
	@FindBy (id = "couponCode")
	WebElement couponCodeTextBox;
	
	@FindBy (id = "missingCouponCode")
	WebElement validationMessageCouponCode;
	
	@FindBy (css = ".coupon-applied")
	WebElement couponAppliedtext;

	public PhoenixCheckoutLoginPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void enterValueInEmail(String email) {

		guestEmail.clear();
		guestEmail.sendKeys(email);
	}
	
	public void clickOnContinueOnGuesButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(continueAsGuestButton));
		continueAsGuestButton.click();
	}
	
	public String getEmailValidationMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(emailValidationOnGuestLogin));
		return emailValidationOnGuestLogin.getText();
	}
	
	public String getShippingText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(shippingText));
		return shippingText.getText();
	}
	
	public void clickOnApplyButton() {
		
		applyButton.click();
	}
	
	public void verifyPromoCodeTextBox(String couponCodeTextBoxOnGuestLogin) {
		
		couponCodeTextBox.sendKeys(couponCodeTextBoxOnGuestLogin);
	}
	
	public String verifyCouponSuccessmessage() {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(couponAppliedtext));
		return couponAppliedtext.getText();
	}
	
	public String verifyPromoCodeValidationMessage() {
		
		return validationMessageCouponCode.getText();
	}
	
}
