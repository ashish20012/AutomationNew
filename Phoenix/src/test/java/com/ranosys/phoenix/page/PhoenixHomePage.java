package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This Class is the Page Object Model Representation of Home Page Business Logic
 *
 * @author Ashish kumar
 * @version 1.0
 * @since 23-07-2024
 */
public class PhoenixHomePage {

	final WebDriver driver;

	// Used to locate elements on the web page. It can be used to specify how to
	// locate a web element using different strategies like ID, name, XPath, CSS
	// selector, etc.
	// This will locate the cookie accept button on Home Page using the "cookie-accept" ID element of HTML
	@FindBy(id = "cookie-accept")
	WebElement cookieAcceptButton;

	// This will locate the men category link on Home Page using the "men" ID element of HTML
	@FindBy(id = "men")
	WebElement menButton;

	@FindBy(id = "account-dropdown")
	WebElement myAccountIcon;

	@FindBy(xpath = "//*[@id=\"homepage\"]/div/div/div/div[2]/div/div/div/h4")
	WebElement topCategories;

	/**
	 * PhoenixHomePage() is a class constructor
	 * 
	 * @param driver
	 */
	public PhoenixHomePage(WebDriver driver) {
		this.driver = driver;
		// initialize the elements of a Page Object class. This method looks for all the
		// @FindBy annotated fields and initializes them using the WebDriver.
		PageFactory.initElements(driver, this);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(cookieAcceptButton));
	
	}

	/**
	 * clickOnCooKieButton()
	 * 
	 * @return Nothing
	 */
	public void clickOnCooKieButton() {
		cookieAcceptButton.click();
	}

	public void clickOnMenButtonUnderMegaMenu() {
		menButton.click();
	}

	public void clickOnMyAccountIcon() {

		myAccountIcon.click();
	}

	public String getTopCategoriesText() {

		return topCategories.getText();
	}

}
