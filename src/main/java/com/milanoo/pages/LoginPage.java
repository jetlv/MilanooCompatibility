package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * 
 * @author 吕超 <br><br>
 * 页面描述： 登录页面封装
 *
 */
public class LoginPage {
	@FindBy(how= How.NAME, using="loginusername")
	WebElement input_username;
	@FindBy(how= How.NAME, using="loginuserpass")
	WebElement input_password;
	@FindBy(how=How.XPATH, using="//button[contains(@class, 'submitbtn')][contains(@class, 'loginbut')]/span")
	WebElement submit_button;
	
	/**
	 * 
	 * @param usr
	 * @param psw
	 * 
	 * <br> 登录 
	 */
	public void login(String usr, String psw) {
		input_username.sendKeys(usr);
		
		input_password.sendKeys(psw);
		
		submit_button.click();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
		}
	}
	
}
