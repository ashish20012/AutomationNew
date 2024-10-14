package com.ranosys.phoenix.page;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixPLPPage {

	final WebDriver driver;

	@FindBy(id = "login-form-email")
	WebElement email;

	@FindBy(xpath = "//h2[@class='filter-text mb-0 ml-2']")
	WebElement filterText;

	@FindBy(xpath = "//button[@class='btn btn-black col-12 col-sm-4 more']")
	WebElement showMore;

	@FindBy(xpath = "//*[@id=\"men\"]/span[1]")
	WebElement clickOnMegaMenu;

	@FindBy(xpath = "//span[@class='product-count-text']")
	WebElement productCount;

	@FindBy(css = "//a[contains(text(),'Men Bomber Jacket')]/../../../..//button[@class='button btn-toggle btn btn-black']")
	WebElement quickAddPLP;

	@FindBy(xpath = "//button[@class='color-attribute product-color-attribute'][1]")
	WebElement clickColorSwatches;

	@FindBy(css = "i.fa.fa-shopping-bag")
	WebElement clickAddToCart;

	@FindBy(xpath = "//button[@class='btn btn-black col-12 col-sm-4 more']")
	WebElement showMoreButton;

	@FindBy(css = "div.row.product-grid>div.col-6.col-sm-4.gaussian-product-tiles")
	List<WebElement> productGrid;

	public PhoenixPLPPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(filterText));
		wait.until(ExpectedConditions.visibilityOf(showMore));
	}

	public void clickOnMegaMenu() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		Actions a = new Actions(driver);
		a.moveToElement(clickOnMegaMenu).click().build().perform();
	}

	public String getproductCountText() {

		return productCount.getText();
	}

	public void clickOnQuickAddButtonPLP(String productName) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.elementToBeClickable(quickAddPLP));
//		quickAddPLP.click();
		
		driver.findElement(By
				.xpath("//a[contains(text(),'" + productName + "')]/../../../..//button[@class='button btn-toggle btn btn-black']"))
				.click();
	}

//	public void clickSizePLP() {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
//		wait.until(ExpectedConditions.elementToBeClickable(clickSize));
//		clickSize.click();
//	}

	public void clickOnProductColourSwatch(String productName, String productColour) {
		driver.findElement(By
				.xpath("//img[@title='" + productName + "']/../../..//span[@data-attr-value='" + productColour + "']"))
				.click();
	}

	public void clickOnProductSizeSwatch(String productName) {

		driver.findElement(
				By.xpath("//img[@title='" + productName + "']/../../..//button[@aria-label='Select size M']"))
				.click();
	}

	public void clickOnAddToCart(String productName) {
		Actions action = new Actions(driver);

		action.moveToElement(driver.findElement(By.xpath(
				"//img[@title='" + productName + "']/../../..//div[@class='col-12 pdp-checkout-button']/button")))
				.click().perform();
	}

	public void clickOnPriceFilter(String priceFilter) {

		driver.findElement(By.xpath("//div[@id='refinement-price']//span[contains(text(),'" + priceFilter + "')][1]"))
				.click();
		// priceRadioButton.click();
	}

	public void clickOnCategoryFilter(String categoryName) {

		driver.findElement(
				By.xpath("//div[@id='refinement-category']//span[contains(text(),'" + categoryName + "')][1]")).click();
	}

	public void clickOnSizeFilter(String productSize) {

		driver.findElement(By.xpath("//div[@id='refinement-size']//span[contains(text(),'" + productSize + "')]"))
				.click();
	}

	public void clickOnColorFilter(String productColorFilter) {

		driver.findElement(
				By.xpath("//div[@id='refinement-color']//span[contains(text(),'" + productColorFilter + "')][1]"))
				.click();
	}

	public void clickOnClearFilter() {
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.cssSelector("button.reset.filter-reset-btn"))).click().perform();
	}

	public boolean isShowMoreButtonPresent() {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", showMoreButton);

		try {
			return showMore.isDisplayed();

		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public void clickOnShowMoreButton() {
		showMoreButton.click();
	}

	public int getProductCount() {

		return productGrid.size();
	}

}