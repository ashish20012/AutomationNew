package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixMiniCartPage {

	final WebDriver driver;

	@FindBy(xpath = "//span[@class='product-item-name']")
	WebElement productNameMiniCart;

	@FindBy(css = ".line-item-attributes.mini-variationcolor")
	WebElement productColorOnMiniCart;

	@FindBy(css = ".line-item-attributes.mini-variationsize")
	WebElement productSizeOnMiniCart;

	@FindBy(xpath = "//button[@aria-label='Add one']")
	WebElement increaseQty;

	@FindBy(css = ".form-control.quantity.quantity-select")
	WebElement qtyBoxMiniCart;

	@FindBy(css = ".remove-btn.remove-product.btn")
	WebElement removeMiniCart;

	@FindBy(css = ".btn.btn-primary.cart-delete-confirmation-btn.btn-black")
	WebElement removeConfirmYes;

	@FindBy(css = ".col-12.mini-cart-product-count>h1>span")
	WebElement miniCartCounter;

	@FindBy(css = ".btn.btn-outline-black.mt-0")
	WebElement viewCart;

	@FindBy(css = ".text-uppercase.btn.btn-primary.btn-black.mt-0.btn-block.mb-0.checkout-btn ")
	WebElement checkoutMiniCart;

	@FindBy(xpath = "//li[@class='breadcrumb-item'][3]")
	WebElement breadcrumbCheckout;

	@FindBy(xpath = "//li[@class='breadcrumb-item'][2]")
	WebElement breadcrumbViewCart;

	@FindBy(xpath = "//div[@data-shipment-uuid='6e64a1e8b2eeb755e98c9bbc78']//div//h2")
	WebElement shippingText;

	public PhoenixMiniCartPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	public String getProductNameOnMiniCart() {

		return productNameMiniCart.getText();
	}

	public String getProductColorOnMiniCart() {
		return productColorOnMiniCart.getText();
	}

	public String getProductSizeOnMiniCart() {
		return productSizeOnMiniCart.getText();
	}

	public void clickOnPlusIcon() {

		increaseQty.click();
	}

	public String getTextFromQtyBox() {
		String value = qtyBoxMiniCart.getAttribute("value");
		return (value);
	}

	public void clickOnRemoveButtonOnMiniCart() {

		removeMiniCart.click();
	}

	public void clickOnRemoveConfirmButtonOnMiniCart() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector(".btn.btn-primary.cart-delete-confirmation-btn.btn-black")))
				.click();
	}

	public String getItemCounterGetCounter() {

		return miniCartCounter.getText();
	}

	public void clickOnViewCartButtonOnMiniCart() {

		if (viewCart.isEnabled() == true) {
			viewCart.click();
		}
	}

	public void clickOnCheckoutButtonOnMiniCart() {

		if (checkoutMiniCart.isEnabled() == true) {
			checkoutMiniCart.click();
		}
	}

	public String getCheckoutBreadCrumbText() {

		return breadcrumbCheckout.getText();
	}

	public String getViewCartBreadCrumbText() {

		return breadcrumbViewCart.getText();
	}

	public String getShippingText() {

		return shippingText.getText();
	}

}
