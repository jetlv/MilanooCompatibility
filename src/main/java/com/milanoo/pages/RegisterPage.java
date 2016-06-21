package com.milanoo.pages;

import java.util.Random;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class RegisterPage {
	@FindBy(how= How.ID, using="email")
	WebElement input_username;
	@FindBy(how= How.ID, using="UserPass")
	WebElement input_password;
	@FindBy(how= How.ID, using="UserPass2")
	WebElement input_password1;
	@FindBy(how= How.ID, using="choose02")
	WebElement ckbox_agree;
	@FindBy(how=How.XPATH, using="//button[contains(@class, 'submitbtn')][contains(@class, 'creatbut')]/span")
	WebElement submit_button;
	
	/**
	 * 
	 * @param length
	 * @return
	 * 随机数工具类
	 */
	private String getRandomString(int length) {
		String scope = "qwertyuiopasdfghjklzxcvbnm123456789";
		StringBuffer result= new StringBuffer();
		for(int i=0; i < length; i++) {
			result.append(scope.charAt(new Random().nextInt(scope.length())));
		}
		return result.toString();
	}
	
	/**
	 * 随机邮箱注册 密码123456
	 * @throws InterruptedException 
	 */
	public void regist_randomemail() throws InterruptedException {
		String mail = getRandomString(8) + "@milanoo.cn";
		String psw = "123456";
		input_username.sendKeys(mail);
		input_password.sendKeys(psw);
		input_password1.sendKeys(psw);
		Thread.sleep(1000);
		ckbox_agree.click();
		Thread.sleep(1000);
		submit_button.click();
		Thread.sleep(2000);
	}
}
