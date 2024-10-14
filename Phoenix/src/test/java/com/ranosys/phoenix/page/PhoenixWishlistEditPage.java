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

public class PhoenixWishlistEditPage {

	final WebDriver driver;

	@FindBy(css = "a.pl-0.edit.btn.link")
	WebElement editWishlist;

	@FindBy(xpath = "//button[@class='btn-update-wishlist-product update-cart-product-global btn btn-black btn-block mb-0 ']")
	WebElement updateButton;

	@FindBy(css = "span[data-attr-value='black'].color-value.swatch-circle.swatch-value.selectable")
	WebElement colorSwatch;

	@FindBy(css = "button[aria-label='Select size s'].btn.btn-outline-dark.size-attribute")
	WebElement sizeButton;

	@FindBy(css = "button.select-attributes-btn.btn.btn-black")
	WebElement selectAttribute;

	public PhoenixWishlistEditPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnEditButton() {
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.cssSelector("a.pl-0.edit.btn.link"))).click().perform();
		// editWishlist.click();

	}

	public void clickOnColorSwatch() {

		colorSwatch.click();
	}

	public void clickOnSize() {

		sizeButton.click();
	}

	public void clickOnUpdateButton() {

		if (updateButton != null && updateButton.isDisplayed()) {
			updateButton.click();
		} else {
		}
	}

	public void clickOnSelectAttribute() {
		if(selectAttribute !=null && selectAttribute.isDisplayed()) {
			selectAttribute.click();
		}
	}

}
