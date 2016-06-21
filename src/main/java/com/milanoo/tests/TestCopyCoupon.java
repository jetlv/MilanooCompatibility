package com.milanoo.tests;

import static org.testng.AssertJUnit.assertEquals;

import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.apache.xml.serialize.Printer;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.milanoo.configure.Global;
import com.milanoo.pages.RegisterPage;
import com.milanoo.utils.RegularUtils;

/**
 * 
 * @author 吕超
 * 礼券复制功能兼容性测试(本测试只能顺序执行，因为剪贴板是操作系统共享的)
 * @see http://192.168.0.2:8000/issues/37048
 */
public class TestCopyCoupon {
	
	WebDriver driver;
	
	@BeforeTest
	@Parameters({"browser", "registUrl"})
	public void setup(String browser, String registUrl) throws MalformedURLException {
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setBrowserName(browser);
		dc.setPlatform(Platform.WINDOWS);
		if(browser.equals("internet explorer")) {
			dc.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			dc.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		}
		URL url = new URL(Global.getConfiguration("hub"));
		driver = new RemoteWebDriver(url, dc);
		driver.get("http://test.item.www.milanoo.com/index.php?module=ajax&action=userStatus&act=logout");
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(registUrl);
	}
	
	@Test
	@Parameters({"couponPage"})
	public void testCopiedWell(String couponPage) throws InterruptedException {
		RegisterPage rp = PageFactory.initElements(driver, RegisterPage.class);
		
		rp.regist_randomemail();
		
		driver.get(couponPage);	
//		driver.get("http://test.item.www.milanoo.com/member/CouponPromotion.html");
		
		String couponCode = driver.findElement(By.xpath("//div[@class='couponcode']")).getText();

		System.out.println(couponCode);
		
		WebElement div= driver.findElement(By.xpath("//div[@id='flash_copy_layout_0']/button"));
		((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid red'", div);
		byte[] b = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
		RegularUtils.getFile(b, "D:\\", "screens.png");
		
		
		Actions action = new Actions(driver);
		
		System.out.println("div的left： " + div.getLocation().getX());
		System.out.println("div的top： " + div.getLocation().getY());
		
		action.moveToElement(div).click().perform();
		
		Thread.sleep(15000);
		
		
		
		//用百度搜索框粘贴出来验证一下
		driver.get("http://www.baidu.com");
		WebElement sb = driver.findElement(By.id("kw"));
		sb.sendKeys(Keys.CONTROL, "v");
		
		String actualValue = sb.getAttribute("value");
		
		assertEquals(couponCode.split("\n")[0], actualValue);
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
