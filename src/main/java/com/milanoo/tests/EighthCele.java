package com.milanoo.tests;

import static org.testng.AssertJUnit.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.milanoo.pages.AnniversaryPage;
import com.milanoo.pages.RegisterPage;

/**
 * 
 * @author Administrator
 * 八周年兼容性测试
 */
public class EighthCele {
	
	WebDriver driver;
	
	/**
	 * 
	 * @param browser
	 * @throws MalformedURLException
	 * 前置操作，启动browser
	 * @throws InterruptedException 
	 */
	@BeforeTest
	@Parameters({"browser","pageUrl"})
	public void setup(String browser, String pageUrl) throws MalformedURLException, InterruptedException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setPlatform(Platform.WINDOWS);
		if(browser.equals("internet explorer") || browser.equals("Microsoft Edge")) {
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
			if(browser.equals("Microsoft Edge")) {
//				dc.setPlatform(platform);
			}
		}
//		if(browser.equals("Microsoft Edge")) {
//			dc = DesiredCapabilities.edge();
//			dc.setPlatform(Platform.WINDOWS);
//		}
		URL url = new URL("http://192.168.12.104:7777/wd/hub");
		driver = new RemoteWebDriver(url, dc);
		driver.get("http://www.milanoo.com/index.php?module=ajax&action=userStatus&act=logout");
		driver.get("https://www.milanoo.com/member/reg.html");
		RegisterPage ap = PageFactory.initElements(driver, RegisterPage.class);
		ap.regist_randomemail();
		Thread.sleep(5000);
		driver.get(pageUrl);
	}
	
	
	/**
	 * 
	 * @throws InterruptedException
	 * 测试拿红包
	 */
	@Test
	public void testAquireEnvelop() throws InterruptedException {
		AnniversaryPage ap = PageFactory.initElements(driver, AnniversaryPage.class);
		ap.click_firstcoupon();
		Thread.sleep(2000);
		assertEquals(ap.if_popuoshows(), true);
	}
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
