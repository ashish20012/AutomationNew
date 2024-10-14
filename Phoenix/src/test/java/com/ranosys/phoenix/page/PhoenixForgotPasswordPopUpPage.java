package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Phoenix Forgot Password Popup smoke test cases
 * 
 * @author Ashish Kumar
 * @version 1.0
 * @since 05-08-2024
 *
 */

public class PhoenixForgotPasswordPopUpPage {

	final WebDriver driver;

	@FindBy(id = "password-reset")
	WebElement forgotPasswordLink;

	@FindBy(xpath = "//*[@id=\"requestPasswordResetModal\"]/div/div/div/p")
	WebElement verifyForgotPopupText;

	@FindBy(id = "reset-password-email")
	WebElement emailForgotPasswordTextBox;

	@FindBy(id = "submitEmailButton")
	WebElement submitEmail;

	public PhoenixForgotPasswordPopUpPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// wait.until(ExpectedConditions.visibilityOf(email));
		// wait.until(ExpectedConditions.visibilityOf(signInButton));
	}

	public void clickOnForgotPasswordLink() {

		forgotPasswordLink.click();
	}

	public String verifyForgotPasswordTextOnPopup() {

		return verifyForgotPopupText.getText();

	}

	public void enterEmailOnForgotPassword(String forgotEmail) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reset-password-email")));
		emailForgotPasswordTextBox.sendKeys(forgotEmail);
	}

	public void clickOnSendButton() {
		
		submitEmail.click();

	}

}
