package com.milanoo.tests;

import static org.testng.AssertJUnit.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.milanoo.configure.Global;
import com.milanoo.pages.HomePage;
import com.milanoo.pages.Operations;
import com.milanoo.pages.ProductPage;
import com.milanoo.pages.ShoppingCartPage;
import com.milanoo.prepare.BrowserStackMarker;

/**
 * 
 * @author Administrator 基本流程兼容性
 */
public class BaseProgress {

	WebDriver driver;
	
	BrowserStackMarker bsm;

	/**
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 *             前置操作，启动browser
	 * @throws InterruptedException
	 */
	@BeforeTest
	@Parameters({ "site", "browser", "version", "platform", "project",
			"pageUrl" })
	public void setUp(String site, String browser, String version,
			String platform, String project, String pageUrl) throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", platform);
		capability.setCapability("browserName", browser);
		capability.setCapability("browserVersion", version);
		capability.setCapability("project", project);
		capability.setCapability("name", site + "_" + browser);
		capability.setCapability("build", "1.0");
		capability.setCapability("browserstack.debug", "true");
		driver = new RemoteWebDriver(new URL(Global.HUB), capability);

		driver.get(pageUrl);

		driver.manage().window().maximize();
		
		/** 装载marker */
		
		SessionId sessionId = ((RemoteWebDriver)driver).getSessionId();
		bsm = new BrowserStackMarker(sessionId.toString());
	}

	/**
	 * 商品加入购物车 - checkout
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	@Parameters({ "productUrl" })
	public void testBaseProgress(String productUrl) throws Exception {
		try {
			HomePage hp = PageFactory.initElements(driver, HomePage.class);
			hp.click_close_icon();
			Thread.sleep(2000);

			assertEquals(hp.verify_searchicon_visible(), true);

			Operations ops = new Operations(driver);

			ops.NavigateTo(productUrl);

			// waitng for 2 secs
			ops.WaitForPageToLoad(2);

			ProductPage pp = PageFactory
					.initElements(driver, ProductPage.class);

			assertEquals(pp.button_addtocart_displayed(), true);

			pp.click_addtocart();

			ops.WaitForPageToLoad(2);

			ShoppingCartPage scp = PageFactory.initElements(driver,
					ShoppingCartPage.class);

			assertEquals(scp.milanoo_checkout_button_exists(), true);
			
			ops.ScrollDownByOffset(350);
			
			scp.click_milanoo_checkout_button();
			
			assertEquals(scp.login_form_exists(), true);
			
			ops.WaitForPageToLoad(5);
		} catch (AssertionError e) {
			bsm.markValidationError();
		} catch(Exception e) {
			bsm.markErrorWithMessage(e.getMessage());
		}

	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
