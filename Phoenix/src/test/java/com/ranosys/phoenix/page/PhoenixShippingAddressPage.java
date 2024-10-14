package com.ranosys.phoenix.page;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PhoenixShippingAddressPage {

	final WebDriver driver;

	@FindBy (id = "shippingFirstNamedefault")
	WebElement firstName;
	
	@FindBy (id = "shippingLastNamedefault")
	WebElement secondName;
	
	@FindBy (id = "shippingAddressOnedefault")
	WebElement addressLineOne;
	
	@FindBy (id = "shippingAddressTwodefault")
	WebElement addressLineTwo;
	
	@FindBy (xpath = "//*[@id=\"dwfrm_shipping\"]/div[1]/fieldset[4]/div[5]/div[1]/div/div[1]/div[2]")
	WebElement selectCountry;
	
	@FindBy (id = "shippingZipCodedefault")
	WebElement zipCode;
	
	@FindBy (id = "shippingPhoneNumberdefault")
	WebElement phoneNumber;
	
	@FindBy (xpath = "//button[@class='btn btn-primary btn-black btn-block submit-shipping']")
	WebElement continueToPaymentButton;
	
	@FindBy (xpath = "//span[@for='billingAddressSelector']")
	WebElement billingText;
	
	@FindBy (xpath = "//button[@value='submit-shipping']")
	WebElement shippingSubmit;
	
	
	public PhoenixShippingAddressPage(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(continueToPaymentButton));
		
	}
	
	public void enterValueInShippingAddress(String firstName, String secondName, String addressLineOne, String addressLineTwo, String zipCode, String phoneNumber ) {

		
		this.firstName.clear();
		this.firstName.sendKeys(firstName);
		
		this.secondName.clear();
		this.secondName.sendKeys(secondName);
		
		this.addressLineOne.clear();
		this.addressLineOne.sendKeys(addressLineOne);
		
		this.addressLineTwo.clear();
		this.addressLineTwo.sendKeys(addressLineTwo);
		
		// Wait for the Selectric dropdown to be visible
        WebElement selectricDropdownCountry = driver.findElement(By.xpath("//div[@class='selectric-wrapper selectric-form-control selectric-floating-input selectric-floating-select selectric-shippingCountry']"));
        // Click to open the dropdown
        selectricDropdownCountry.click();
        // Wait for the dropdown options to be visible (you might need to adjust this locator)
        WebElement optionCountry = driver.findElement(By.xpath("//*[@id=\"dwfrm_shipping\"]/div[1]/fieldset[4]/div[5]/div[1]/div/div[1]/div[3]/div/ul/li[2]"));
        // Select the desired option
        optionCountry.click();

		
		this.zipCode.clear();
		this.zipCode.sendKeys(zipCode);
		
		this.phoneNumber.clear();
		this.phoneNumber.sendKeys(phoneNumber);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(shippingSubmit));
		this.shippingSubmit.click();
		
	}
	
	public String getBillingText() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(billingText));
		return billingText.getText();
	}
}
