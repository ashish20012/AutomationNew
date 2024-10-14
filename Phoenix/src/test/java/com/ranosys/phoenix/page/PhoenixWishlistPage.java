package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixWishlistPage {

	final WebDriver driver;

	@FindBy(xpath = "(//img[@title='T-Shirt - Short Sleeve']/../../..//span[@class='action-icon-wrapper'])[1]")
	WebElement wishlistIcon;

	@FindBy(xpath = "//div[@id='maincontent']/div/div/div/ol/li[2]/span")
	WebElement myAccountBreadCrumb;

	@FindBy(css = ".btn.btn-primary.text-uppercase.btn-black")
	WebElement continueShoppingButton;

	@FindBy(css = ".wishlist-icon.header-icon")
	WebElement wishlistIconHeader;

	@FindBy(css = "div.wishlist-remove>button.remove-from-wishlist.btn")
	WebElement removeButton;

	@FindBy(xpath = "//div[@class='box']/p[text()=\"You have no items in your Wishlist\"]")
	WebElement noItemInCart;

	public PhoenixWishlistPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.visibilityOf(productName));
//		wait.until(ExpectedConditions.visibilityOf(quantityPlusIcon));

	}

	public void clickOnWishlistOnPLP() {

		wishlistIcon.click();
	}

	public String verifyBreadCrumb() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait.until(ExpectedConditions.visibilityOf(myAccountBreadCrumb)).getText();

	}

	public void clickOnWishListIconOnHeader() {

		wishlistIconHeader.click();

	}

	public boolean checkIsDisplayShippingbutton() {

		return continueShoppingButton.isDisplayed();

	}

	public String getLoginUrl() {

		return driver.getCurrentUrl();
	}

	public void clickOnRemoveButton() {

		if (removeButton.isDisplayed()) {

			removeButton.click();
		}
	}

	public String emptyCartMessageText() {

		return noItemInCart.getText();
	}
}
