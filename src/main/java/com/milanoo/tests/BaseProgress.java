package com.milanoo.tests;

import static org.testng.AssertJUnit.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.milanoo.pages.HomePage;

/**
 * 
 * @author Administrator 基本流程兼容性
 */
public class BaseProgress {

	WebDriver driver;

	/**
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 *             前置操作，启动browser
	 * @throws InterruptedException
	 */
	@BeforeTest
	@Parameters({ "browser", "version", "platform", "pageUrl" })
	public void setUp(String browser, String version, String platform, String pageUrl)
			throws Exception {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", platform);
		capability.setCapability("browserName", browser);
		capability.setCapability("browserVersion", version);
		capability.setCapability("project", "base");
		capability.setCapability("build", "1.0");
		driver = new RemoteWebDriver(
				new URL(
						"https://jetlyu1:kjikHKiKe4NfPwsz2rkH@hub-cloud.browserstack.com/wd/hub"),
				capability);
		
		driver.get(pageUrl);
	}

	/**
	 * 
	 * @throws InterruptedException
	 *             测试拿红包
	 */
	@Test
	public void testAquireEnvelop() throws InterruptedException {
		HomePage hp = PageFactory.initElements(driver,
				HomePage.class);
		hp.click_close_icon();
		Thread.sleep(2000);
	
		assertEquals(hp.verify_searchicon_visible(), true);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
