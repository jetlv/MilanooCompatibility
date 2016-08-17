package com.milanoo.tests;

import static org.testng.AssertJUnit.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Ignore;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Proxy.ProxyType;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.milanoo.configure.Global;
import com.milanoo.exceptions.ValidationException;
import com.milanoo.listener.MilanooIResultListener;
import com.milanoo.pages.Operations;
import com.milanoo.pages.RegisterPage;
import com.milanoo.prepare.BrowserStackMarker;
import com.milanoo.utils.RegularUtils;
import com.milanoo.utils.StringUtils;

@Listeners({MilanooIResultListener.class})
public class TestHalloween extends TestBase{

	BrowserStackMarker bsm;

	// http://182.151.205.165:3111/Buy-Wedding-Dresses-c392
	@BeforeTest
	@Parameters({ "site", "browser", "version", "platform", "project",
			"pageUrl" })
	public void setup(String site, String browser, String version,
			String platform, String project, String pageUrl)
			throws MalformedURLException {
		DesiredCapabilities capability = new DesiredCapabilities();
		capability.setCapability("platform", platform);
		capability.setCapability("browserName", browser);
		Proxy proxy = new Proxy();
		
		proxy.setProxyType(ProxyType.PAC);
		
		proxy.setProxyAutoconfigUrl("https://qypac.net/ai9p0wnc");
		
//		capability.setCapability(CapabilityType.PROXY, proxy);
//		capability.setCapability("browserVersion", version);
//		capability.setCapability("project", project);
//		capability.setCapability("name", site + "_" + browser);
//		capability.setCapability("build", "1.0.3");
//		capability.setCapability("browserstack.debug", "true");
//		capability.setCapability("browserstack.local", "true");
//		capability.setCapability("resolution", "1920x1080");
		driver = new RemoteWebDriver(new URL(Global.LOCAL_HUB), capability);

		driver.get(pageUrl);

		driver.manage().window().maximize();

		/** 装载marker */

		SessionId sessionId = ((RemoteWebDriver) driver).getSessionId();
		bsm = new BrowserStackMarker(sessionId.toString());
	}

	/**
	 * 
	 * @throws InterruptedException
	 * @throws ValidationException
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws IOException
	 * 
	 *             最基本的流程测试
	 */
	@Test(enabled = false)
	@Ignore
	public void testAcess() throws InterruptedException, ValidationException,
			UnsupportedEncodingException, URISyntaxException, IOException {

		try {

			String xpath = "//img[contains(@class, 'show-halloween-game')]";

			Operations ops = new Operations(driver);

			ops.ClickBy("xpath", xpath);

			ops.VerifyContainsElement("xpath", "//div[@class='gameleft']");

			ops.WaitForElementToVisible("xpath", "//div[@class='gameleft']",
					"5");

			/**
			 * 弹出登录框
			 */
			ops.ClickBy("xpath", "//div[@class='userinfo']/a");

			ops.WaitForElementToVisible("xpath", "//div[@id='sc_loginreg']",
					"5");

			/**
			 * 注册操作
			 */
			ops.ClickBy("xpath", "//a[@href='#sc_reg']");

			ops.InputText("xpath", "//input[@id='email']",
					StringUtils.getRandomString(8) + "@gmail.com");
			ops.InputText("xpath", "//input[@id='UserPass']", "123456");
			ops.InputText("xpath", "//input[@id='UserPass2']", "123456");
			ops.ClickBy("xpath", "//button[@id='regBtn']");

			ops.WaitForElementToVisible("xpath", "//div[@class='gameleft']",
					"5");

			/**
			 * 机会文字展示
			 */
			String chances = driver.findElement(
					By.xpath("//div[@class='gameleft']")).getText();

			assertEquals(chances.length() > 3, true);
			assertEquals(chances.contains("3"), true);

			ops.WaitForElementToVisible("xpath", "//div[@class='right']", "5");

			/**
			 * 验证规则入口存在
			 */
			String rules = driver.findElement(
					By.xpath("//div[@class='right']/div/a")).getText();

			assertEquals(rules.length() > 3, true);

			/**
			 * 点击砸南瓜，开始抽奖
			 */
			ops.ClickBy("xpath",
					"//div[@id='pump-click']/span[contains(@class,'forth')]");

			ops.WaitForElementToVisible("xpath", "//div[@id='res1']", "5");

			/** 抽奖结果 */
			String result = driver.findElement(By.xpath("//div[@id='res1']"))
					.getText();
			assertEquals(result.length() > 3, true);

			/**
			 * 下方正常展示结果
			 */
			int lengthOfResult = driver.findElements(
					By.xpath("//div[@id='gift-list']/div")).size();
			assertEquals(lengthOfResult, 1);

			/**
			 * 点击再玩一次
			 */
			ops.ClickBy("xpath", "//a[contains(@class, 'play-again')]");

			/**
			 * 5个南瓜再次展示
			 */
			ops.WaitForElementToVisible("xpath", "//div[@id='pump-click']", "2");
			int lengthOfPumpkin = driver.findElements(
					By.xpath("//div[@id='pump-click']/span")).size();
			assertEquals(lengthOfPumpkin, 6);

		} catch (AssertionError e) {
			bsm.markValidationError();
		} catch (Exception e) {
			bsm.markErrorWithMessage(e.getMessage());
		}
	}

	/**
	 * 
	 * @throws UnsupportedEncodingException
	 * @throws URISyntaxException
	 * @throws IOException
	 * 
	 *             测试三次抽奖
	 * @throws ValidationException 
	 * @throws InterruptedException 
	 */
	@Test
	public void testLottery() throws UnsupportedEncodingException,
			URISyntaxException, IOException, ValidationException, InterruptedException {
//		try {
			System.out.println("Start!");
			
			String xpath = "//img[contains(@class, 'show-halloween-game')]";

			Operations ops = new Operations(driver);

			ops.ClickBy("xpath", xpath);

//			ops.VerifyContainsElement("xpath", "//div[@class='gameleft']");
			
			ops.WaitForElementToVisible("xpath", "//div[@class='gameleft']",
					"5");

			/**
			 * 弹出登录框
			 */
			ops.ClickBy("xpath", "//div[@class='userinfo']/a");

			ops.WaitForElementToVisible("xpath", "//div[@id='sc_loginreg']",
					"5");

			/**
			 * 注册操作
			 */
			ops.ClickBy("xpath", "//a[@href='#sc_reg']");

			ops.InputText("xpath", "//input[@id='email']",
					StringUtils.getRandomString(8) + "@gmail.com");
			ops.InputText("xpath", "//input[@id='UserPass']", "123456");
			ops.InputText("xpath", "//input[@id='UserPass2']", "123456");
			ops.ClickBy("xpath", "//button[@id='regBtn']");

//			ops.WaitForElementToVisible("xpath", "//div[@class='gameleft']",
//					"15");
//			
				
			ops.WaitForPageToLoad(5);
			ops.WaitForElementToVisible("xpath", "//div[@id='pump-click']/span",
					"5");
			
			/**
			 * 关闭下方的推荐菜单
			 */
			ops.ClickBy("css", ".scd-icon-ion-ios-close-empty");
			
			/**
			 * 点击砸南瓜，开始抽奖
			 */
//			driver.getPageSource();
			ops.ClickByActions("xpath", "//div[@id='pump-click']/span[2]");

			ops.WaitForElementToVisible("xpath", "//div[@id='res1']", "5");

			/** 抽奖结果 */
			String result = driver.findElement(By.xpath("//div[@id='res1']"))
					.getText();
			assertEquals(true ,result.length() > 3);

			/**
			 * 下方正常展示结果
			 */
			ops.WaitForPageToLoad(2);
			int lengthOfResult = driver.findElements(
					By.xpath("//div[@id='gift-list']/div")).size();
			assertEquals(5,lengthOfResult);

			/**
			 * 第二次抽奖
			 */
//			driver.getPageSource();
			ops.ClickBy("xpath", "//a[contains(@class,'play-again')]");

			/**
			 * 点击砸南瓜，开始抽奖
			 */
			ops.WaitForPageToLoad(5);
			ops.ClickByActions("xpath", "//div[@id='pump-click']/span");

			ops.WaitForElementToVisible("xpath", "//div[@id='res1']", "5");

			/** 抽奖结果 */
			String result2 = driver.findElement(By.xpath("//div[@id='res1']"))
					.getText();
			assertEquals(result2.length() > 3, true);
			/**
			 * 下方正常展示结果
			 */
			ops.WaitForPageToLoad(2);
			int lengthOfResult2 = driver.findElements(
					By.xpath("//div[@id='gift-list']/div")).size();
			assertEquals(2, lengthOfResult2);

			/**
			 * 第三次抽奖
			 * 
			 */

			ops.ClickBy("xpath", "//a[contains(@class,'play-again')]");
			/**
			 * 点击砸南瓜，开始抽奖
			 */
			driver.getPageSource();
			ops.WaitForPageToLoad(5);
			ops.ClickByActions("xpath", "//div[@id='pump-click']/span");

			ops.WaitForElementToVisible("xpath", "//div[@id='res1']", "5");

			/** 抽奖结果 */
			String result3 = driver.findElement(By.xpath("//div[@id='res1']"))
					.getText();
			assertEquals(result3.length() > 3, true);
			/**
			 * 下方正常展示结果
			 */
			ops.WaitForPageToLoad(2);
			int lengthOfResult3 = driver.findElements(
					By.xpath("//div[@id='gift-list']/div")).size();
			assertEquals(3, lengthOfResult3);

			/**
			 * 点击再玩一次
			 */
			ops.ClickBy("xpath", "//a[contains(@class, 'play-again')]");

			/**
			 * 5个南瓜再次展示
			 */
			ops.WaitForElementToVisible("xpath", "//div[@id='pump-click']", "2");
			int lengthOfPumpkin = driver.findElements(
					By.xpath("//div[@id='pump-click']/span")).size();
			assertEquals(6, lengthOfPumpkin);

//		} catch (AssertionError e) {
//			bsm.markValidationError();
//		} catch (ValidationException e) {
//			bsm.markErrorWithMessage(e.getMessage());
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			//Actually do nothing
//		}
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
