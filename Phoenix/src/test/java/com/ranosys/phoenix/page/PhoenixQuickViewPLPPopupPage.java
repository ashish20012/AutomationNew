package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixQuickViewPLPPopupPage {

	final WebDriver driver;

	@FindBy(css = "h1.product-name")
	WebElement productName;

	@FindBy(css = "#increaseStatic")
	WebElement quantityPlusIcon;

	@FindBy(xpath = "(//img[@title='T-Shirt - Short Sleeve']/../../..//span[@class='action-icon-wrapper'])[2]")
	WebElement clickOnQuickView;

	@FindBy(css = ".add-to-cart-global.btn.js-add-to-cart-global.btn-black.mb-0")
	WebElement addToCartOnPopup;

	public PhoenixQuickViewPLPPopupPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
//		clickOnQuickViewIcon();
//		String myWindowHandle = driver.getWindowHandle();
//		driver.switchTo().window(myWindowHandle);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(productName));
//		wait.until(ExpectedConditions.visibilityOf(quantityPlusIcon));
	}

	public void clickOnQuickViewIcon() {

		clickOnQuickView.click();
	}

	public String verifyProductName() {
		WebDriverWait wait = new  WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(productName));
		return productName.getText();
	}

	public void clickOnAddToCartButtonOnPopup() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.cssSelector(".add-to-cart-global.btn.js-add-to-cart-global.btn-black.mb-0"))).click();
	}

}
