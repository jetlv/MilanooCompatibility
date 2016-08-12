package com.milanoo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.milanoo.exceptions.ValidationException;
import com.milanoo.utils.Exceptions;

//@Deprecated

/**
 * 
 * @author Administrator 通用的方法封装
 */
public class Operations {
	
	WebDriver driver;
	
	
	
	public Operations(WebDriver driver) {
		super();
		this.driver = driver;
	}
	
	/**
	 * 等待元素出现
	 * @param selector
	 * @param key
	 * @param seconds
	 * @throws ValidationException
	 */
	public void WaitElement(String selector, String key, int seconds) throws ValidationException {
		Integer time = seconds;

		WebElement element = null;

		while (time > 0) {

			try {
				element = getSpecificWebElement(selector, key);

				break;
			} catch (ValidationException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				time--;
			}
		}

		if (element == null && time <= 0) {
			throw new ValidationException("元素 " + key + " 在" + seconds
					+ "秒内没有出现");
		}
	}
	
	/**
	 * 悬浮至某元素
	 * @param selector
	 * @param key
	 * @throws ValidationException
	 */
	public void HoverTo(String selector, String key) throws ValidationException {
		WebElement element = getSpecificWebElement(selector, key);

		Actions a = new Actions(driver);

		a.moveToElement(element).perform();
	}
	
	/**
	 * 
	 * @param selector
	 * @param key
	 * @throws ValidationException
	 * @author 吕超
	 * @date 2016年3月24日 下午5:44:44
	 * 
	 * 清空input
	 */
	public void ClearInput(String selector, String key) throws ValidationException {
		
		WebElement input = getSpecificWebElement(selector, key);

		input.clear();
	}
	
	/**
	 * 
	 * 
	 * @author 吕超
	 * @date 2016年3月24日 下午5:46:02
	 * 
	 * 清空当前driver页面的所有cookie
	 */
	public void ClearAllCookis() {
		driver.manage().deleteAllCookies();
	}
	/**
	 * 
	 * @param selector
	 *            支持三种 id, xpath, css
	 * @param key
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年10月29日 上午11:27:47
	 * 
	 *       鼠标悬浮至某元素并点击不释放
	 */
	public void ClickAndHold(String selector, String key) throws ValidationException {
		WebElement element = getSpecificWebElement(selector, key);

		Actions a = new Actions(driver);

		a.clickAndHold(element).perform();
	}

	/**
	 * 
	 * @param selector
	 *            支持三种 id, xpath, css
	 * @param key
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年10月23日 下午2:37:23
	 */
	public void ClickBy(String selector, String key) throws ValidationException {
		WebElement element = getSpecificWebElement(selector, key);

		element.click();
	}

	/**
	 * 
	 * @param selector
	 *            支持三种 id, xpath, css
	 * @param key
	 * @param text
	 *            要输入的内容
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年10月23日 下午2:37:23
	 */
	public void InputText(String selector, String key, String text)
			throws ValidationException {
		WebElement element = getSpecificWebElement(selector, key);

		element.sendKeys(text);
	}

	/**
	 * 
	 * @param selector
	 * @param expression
	 * @param value
	 * @throws ValidationException
	 * @author 吕超
	 * @date 2015年11月18日 下午5:08:31
	 * 
	 *       根据 value在select下拉框中选择某项
	 */
	public void SelectValueFromDropdown(String selector, String expression,
			String value) throws ValidationException {
		WebElement selectElement = getSpecificWebElement(selector, expression);

		Select select = new Select(selectElement);

		select.selectByValue(value);
	}

	/**
	 * 
	 * @param selector
	 * @param key
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年10月29日 下午1:53:38
	 * 
	 *       验证含有某元素
	 */
	public void VerifyContainsElement(String selector, String key)
			throws ValidationException {

		WebElement element = getSpecificWebElement(selector, key);

		// 实际上执行不了if里的语句
		if (element == null) {
			throw new ValidationException("不存在这个元素");
		}
	}

	/**
	 * 
	 * @param selector
	 * @param key
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年10月29日 下午1:53:38
	 * 
	 *       验证不含有某元素
	 */
	public void VerifyNotContainsElement(String selector, String key)
			throws ValidationException {

		WebElement element = null;

		try {
			element = getSpecificWebElement(selector, key);
		} catch (ValidationException e) {
			// do nothing 后面来判断
		}

		// 实际上执行不了if里的语句
		if (element != null) {
			throw new ValidationException(selector + " 为 " + key + "的元素存在于页面上");
		}
	}

	/**
	 * 
	 * @param selector
	 * @param key
	 * @throws ValidationException
	 * @author 吕超
	 * @date 2015年10月29日 下午4:51:00 进入iframe
	 */
	public void SwitchToIframe(String selector, String key)
			throws ValidationException {

		WebElement iframElement = getSpecificWebElement(selector, key);

		driver.switchTo().frame(iframElement);

	}

	/**
	 * 
	 * 
	 * @author 吕超
	 * @date 2015年10月29日 下午4:51:40 跳出iframe
	 */
	public void QuitIframe() {

		driver.switchTo().defaultContent();
	}
	/**
	 * 
	 * @param seconds
	 * @author 吕超
	 * @throws InterruptedException
	 * @date 2015年10月14日 上午9:06:53
	 * 
	 *       让线程等待一定的时间.针对个别操作可以使用。
	 */
	public void WaitForPageToLoad(int seconds) throws InterruptedException {

		Long millseconds = Long.valueOf(seconds) * 1000;

		Thread.sleep(millseconds);
	}

	/**
	 * 
	 * @param selector
	 * @param key
	 * @param seconds
	 * @author 吕超
	 * @throws ValidationException
	 * @date 2015年11月13日 上午9:26:23
	 * 
	 *       在一定时间范围内等待某元素出现
	 */
	public void WaitForElementToLoad(String selector, String key, String seconds)
			throws ValidationException {

		Integer time = Integer.valueOf(seconds);

		WebElement element = null;

		while (time > 0) {

			try {
				element = getSpecificWebElement(selector, key);

				break;
			} catch (ValidationException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				time--;
			}
		}

		if (element == null && time <= 0) {
			throw new ValidationException("元素 " + key + " 在" + seconds
					+ "秒内没有出现");
		}
	}

	/**
	 * 
	 * @param selector
	 * @param key
	 * @param seconds
	 * @author 吕超
	 * @throws ValidationException
	 * @throws InterruptedException
	 * @date 2015年11月13日 上午9:26:23
	 * 
	 *       在一定时间范围内等待某元素Visible
	 */
	public void WaitForElementToVisible(String selector, String key,
			String seconds) throws ValidationException {

		Integer time = Integer.valueOf(seconds);

		WebElement element = null;

		while (time > 0) {

			try {
				element = getSpecificWebElement(selector, key);

				break;
			} catch (ValidationException e) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				time--;
			}
		}

		if (element == null && time <= 0) {
			throw new ValidationException("元素 " + key + " 在" + seconds
					+ "秒内没有出现");
		}

		while (time > 0 && (!element.isDisplayed())) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			time--;
		}
		if (time <= 0) {
			throw new ValidationException("元素 " + key + " 在等待" + seconds
					+ "秒后仍不可见");
		}
	}

	/**
	 * 
	 * 
	 * @author 吕超
	 * @date 2015年10月15日 下午1:36:54
	 * 
	 *       让页面滑动至最底部（主要针对商品页要加入购物车出现的悬浮框设计）
	 */

	public void ScrollToBottom() {

		String js = "var q=document.documentElement.scrollTop=10000";

		((JavascriptExecutor) driver).executeScript(js);

		// 附加另一个赋值，不会产生负面影响： 针对Chrome不认识第一个js代码的问题
		String jsForChrome = "var e = document.body.scrollTop = 10000";

		((JavascriptExecutor) driver).executeScript(jsForChrome);

	}
	
	/**
	 * 提供一个数字 让页面按照这个偏移量下滑
	 */
	public void ScrollDownByOffset(int offset) {

		String ofs = String.valueOf(offset);
//		String js = "var q=document.documentElement.scrollTop=10000";
//
//		((JavascriptExecutor) driver).executeScript(js);

		// 附加另一个赋值，不会产生负面影响： 针对Chrome不认识第一个js代码的问题
		String jsForChrome = "var e = document.body.scrollTop = " + ofs;

		((JavascriptExecutor) driver).executeScript(jsForChrome);

	}

	/**
	 * 
	 * 
	 * @author 吕超
	 * @date 2015年10月16日 上午9:58:47
	 * 
	 *       刷新当前页面
	 */
	public void Refresh() {

		driver.navigate().refresh();
	}

	/**
	 * 
	 * @param url
	 * @author 吕超
	 * @date 2015年10月19日 下午2:37:59
	 * 
	 *       直接访问到某URL
	 */
	public void NavigateTo(String url) {

		driver.get(url);
	}

	
	
	
	
	//私有方法开始############################################################################
	private WebElement getSpecificWebElement(String selector, String key)
			throws ValidationException {
		WebElement element = null;

		By by = composeSelector(selector, key);

		try {
			element = FindElementBy(by);
		} catch (TimeoutException e) {

			throw new ValidationException("并没有很据 " + selector + " 找到元素 " + key);
		} catch (Exception e) {
			throw new ValidationException(
					Exceptions.getShortStackTraceAsString(e));
		}

		return element;
	}
	
	private WebElement FindElementBy(By by) {

		// 建立final变量给匿名内部类使用
		final By selector = by;

		ExpectedCondition<WebElement> ec = new ExpectedCondition<WebElement>() {

			@Override
			public WebElement apply(WebDriver driver) {

				return driver.findElement(selector);

			}

		};

		WebDriverWait wait = new WebDriverWait(driver, 10);

		return wait.until(ec);
	}
	
	private By composeSelector(String selector, String key)
			throws ValidationException {

		By by = null;
		switch (selector) {

		case "id":
			by = By.id(key);
			break;
		case "xpath":
			by = By.xpath(key);
			break;
		case "css":
			by = By.cssSelector(key);
			break;
		default:
			throw new ValidationException("提供的选择器不正确，请提供 id xpath 和 css的其中一种");
		}

		return by;
	}
	
	//私有方法结束############################################################################
}
