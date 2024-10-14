package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * This Class is the Page Object Model Representation of Login Page Business Logic
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 28-07-2024
 */

public class PhoenixLoginPage {

	final WebDriver driver;

	@FindBy(id = "login-form-email")
	WebElement email;

	@FindBy(id = "login-form-password")
	WebElement password;
	
	@FindBy(xpath = "//button[@class='btn btn-block btn-black']")
	WebElement signInButton;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement passwordValidation;

	@FindBy(xpath = "//div[@class='alert alert-danger']")
	WebElement usernameValidation;

	@FindBy(id = "form-email-error")
	WebElement userPassValidation;

	@FindBy(id = "form-email-error")
	WebElement blankEmailValidation;

	@FindBy(id = "account-dropdown")
	WebElement myAccountAfterLogin;

	@FindBy(xpath = "//li[@class='dropdown-item'][1]")
	WebElement myAccountText;

	@FindBy(xpath = "//a[@class='link'][1]")
	WebElement termsAndConditions;

	@FindBy(xpath = "//a[@class='btn btn-block btn-outline-black']")
	WebElement createAnAccountButton;

	@FindBy(id = "password-reset")
	WebElement forgotPassword;

	@FindBy(xpath = "//form[@id='email-form']/..//p[@class='modal-heading']")
	WebElement verifyForgotPopupText;

	public PhoenixLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(email));
		wait.until(ExpectedConditions.visibilityOf(signInButton));
	}

	public void doLogin(String email, String password) {
		
		this.email.sendKeys(email);
		this.password.sendKeys(password);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(signInButton));
		signInButton.click();
	}

	public String getValidationTextIfPasswordIncorrect() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(passwordValidation));
		return passwordValidation.getText();
	}

	public String getValidationTextIfUsernameIncorrect() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(usernameValidation));
		return usernameValidation.getText();
	}

	public String getValidationTextIfBothIncorrect() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(userPassValidation));
		return userPassValidation.getText();
	}

	public String getValidationTextIfEmailIsBlank() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(blankEmailValidation));
		return blankEmailValidation.getText();
	}

	public String homePageAssertAfterLogin() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(myAccountAfterLogin));

		myAccountAfterLogin.click();
		Actions a = new Actions(driver);
		a.moveToElement(myAccountAfterLogin).click().build().perform();
		wait.until(ExpectedConditions.visibilityOf(myAccountText));
		return myAccountText.getText();

	}

	public void clickOnTermsAndConditions() {
		termsAndConditions.click();
	}

	public String getCurrentUrl() {

		return driver.getCurrentUrl();
	}

	public void clickOnCreateAnAccountButton() {
		Actions a = new Actions(driver);
		a.moveToElement(createAnAccountButton).click().build().perform();

	}

	public void clickOnForgotPasswordLink() {
		
		forgotPassword.click();
	}

	

}
