package com.ranosys.phoenix.script;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.ranosys.commons.DriverBase;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixQuickViewPLPPopupPage;

/**
 * Phoenix PLP Quick View smoke testing
 * 
 * @author Ashish Kumar
 * @version 1.0
 * @since 01-08-2024
 *
 */
public class PhoenixQuickViewPLPPopupSmoke extends DriverBase {

	@Test(priority = 1)
	@Parameters({ "url", "productNameOnQuickView"})
	public void verifyProductName(String url, String productNameOnQuickView) throws InterruptedException {
		
		extentTestLogger = extent.createTest("Product name verification from Quick View POPUP");
		driver.get(url);
		
		// Assert product name on quick view popup
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixQuickViewPLPPopupPage phoenixQuickView = new PhoenixQuickViewPLPPopupPage(driver);
		phoenixQuickView.clickOnQuickViewIcon();
		String myWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(myWindowHandle);
		String actualProductName = phoenixQuickView.verifyProductName();
		String expectedProductName = productNameOnQuickView;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(actualProductName, expectedProductName, "Actual product name is not as per the expected.");
		sa.assertAll();
	}
	@Test(priority = 2)
	@Parameters({"url"})
	public void addToCartproductFromQuickView(String url) {
		
		extentTestLogger = extent.createTest("Add to cart from Quick View");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixQuickViewPLPPopupPage phoenixQuickView = new PhoenixQuickViewPLPPopupPage(driver);
		phoenixQuickView.clickOnQuickViewIcon();
		String myWindowHandle = driver.getWindowHandle();
		driver.switchTo().window(myWindowHandle);
		phoenixQuickView.clickOnAddToCartButtonOnPopup();
	}

}
