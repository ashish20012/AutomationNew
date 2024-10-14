package com.ranosys.phoenix.script;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;
import com.ranosys.commons.DriverBase;
import com.ranosys.commons.PhonixUtility;
import com.ranosys.phoenix.page.PhoenixHomePage;
import com.ranosys.phoenix.page.PhoenixLoginPage;
import com.ranosys.phoenix.page.PhoenixPLPPage;
import com.ranosys.phoenix.page.PhoenixPostLoginHomePage;

/**
 * PhoenixPLPSmoke Class doing the Smoke Testing of PLP Page
 *
 * @author Ashish Kumar
 * @version 1.0
 * @since 23-07-2024
 */
public class PhoenixPLPSmoke extends DriverBase {

	/**
	 * showProductCountText() method is use to show product count on PLP page.
	 *
	 * @param url      an absolute URL giving the base location of the image
	 * @param email    an absolute URL giving the base location of the image
	 * @param password the location of the image, relative to the url argument
	 * @return Nothing
	 */
	@Test(priority = 1, enabled = true)
	@Parameters({ "url", "email", "password" }) // TestNG annotation. We have defined these parameters in testNG.xml
	public void showProductCountText(String url, String email, String password) {

		// Create Test in Extent Report
		extentTestLogger = extent.createTest("PLP Page Verification");

		// Open PLP Page URL
		driver.get(url);

		// Perform Click on Accept Cookie Button and My Account Icon
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();

		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);

		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();

		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		String actualProductCountText = phoenixPLPPage.getproductCountText().toUpperCase();

		// Write Down Actual Product Count Text into Extent Report HTML with Pass Status
		extentTestLogger.log(Status.PASS, actualProductCountText);
	}

	@Test(priority = 2)
	@Parameters({ "url", "email", "password", "productName", "productColor", "productSize" })
	public void clickOnQuickAddButtonPLP(String url, String email, String password, String productName,
			String productColor, String productSize) throws InterruptedException {
		extentTestLogger = extent.createTest("PLP Click On Quick Add Button");
		driver.get(url);

		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMyAccountIcon();
		
		// Login and click on add to cart button
		PhoenixLoginPage phoenixLoginPage = new PhoenixLoginPage(driver);
		phoenixLoginPage.doLogin(email, password);
		PhoenixPostLoginHomePage phoenixPostLoginHomePage = new PhoenixPostLoginHomePage(driver);
		phoenixPostLoginHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnQuickAddButtonPLP(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductColourSwatch(productName, productColor);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnProductSizeSwatch(productName);
		PhonixUtility.staticWaitForSeconds(2);
		phoenixPLPPage.clickOnAddToCart(productName);
		PhonixUtility.staticWaitForSeconds(4);

	}

	@Test(priority = 3)
	@Parameters({ "url", "priceFilter"})
	public void checkPriceFilter(String url, String priceFilter) throws InterruptedException {

		extentTestLogger = extent.createTest("PLP Click on Quick View Icon");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnPriceFilter(priceFilter);
		Thread.sleep(4000);
	}

	@Test(priority = 4)
	@Parameters({"url", "categoryName"})
	public void checkCategoryFiletr(String url, String categoryName) {
		extentTestLogger = extent.createTest("Check category filter is working or not");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnCategoryFilter(categoryName);
	}

	@Test(priority = 5)
	@Parameters({"url", "productSize"})
	public void checkSizeFilter(String url, String productSize) {

		extentTestLogger = extent.createTest("Check size filter is working or not");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnSizeFilter(productSize);
		PhonixUtility.staticWaitForSeconds(4);
	}

	@Test(priority = 6)
	@Parameters({"url", "productColor"})
	public void checkColorFilter(String url, String productColorFilter) {

		extentTestLogger = extent.createTest("Check product color option is working or not");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnColorFilter(productColorFilter);

	}
	@Test(priority = 7)
	@Parameters({"url", "productColorFilter"})
	public void checkClearFilterButton(String url, String productColorFilter) throws InterruptedException {
		
		extentTestLogger = extent.createTest("Check clear filter button is working or not");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		phoenixPLPPage.clickOnColorFilter(productColorFilter);
		Thread.sleep(5000);
		phoenixPLPPage.clickOnClearFilter();
		Thread.sleep(5000);
		
	}

	@Test(priority = 8, enabled = false)
	@Parameters({ "url" })
	public void checkShowMoreIsPresentOrNot(String url) throws InterruptedException {

		extentTestLogger = extent.createTest("PLP Check Show More Button");
		driver.get(url);
		PhoenixHomePage phoenixHomePage = new PhoenixHomePage(driver);
		phoenixHomePage.clickOnCooKieButton();
		phoenixHomePage.clickOnMenButtonUnderMegaMenu();
	
		PhoenixPLPPage phoenixPLPPage = new PhoenixPLPPage(driver);
		int getProductCount1 = phoenixPLPPage.getProductCount();
		int expectedProduct = 15;
		SoftAssert sa = new SoftAssert();
		sa.assertEquals(getProductCount1, expectedProduct, "Actual product count is not as per expected product count");
		sa.assertAll();
		boolean isShowMoreButtonPresent = phoenixPLPPage.isShowMoreButtonPresent();
		if (getProductCount1==15) {
			sa.assertEquals(isShowMoreButtonPresent, "Show More button is present on the page");
			phoenixPLPPage.clickOnShowMoreButton();
		} else {
			sa.assertFalse(isShowMoreButtonPresent, "Show More button is not present on the page");
		}
	}

}
