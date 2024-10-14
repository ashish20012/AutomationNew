package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixPostLoginHomePage {

	final WebDriver driver;

	@FindBy(id = "men")
	WebElement menButton;

	@FindBy(id = "account-dropdown")
	WebElement myAccountIcon;

	@FindBy(xpath = "(//div[@class='editorialRichText-component-container container']//h4)[1]")
	WebElement topCategories;

	public PhoenixPostLoginHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(menButton));
		wait.until(ExpectedConditions.elementToBeClickable(topCategories));
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
