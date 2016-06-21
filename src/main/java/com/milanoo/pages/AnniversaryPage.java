package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AnniversaryPage {
	
	@FindBy(how = How.XPATH, using = "//div[@id='coupon']/ul/li[1]")
	WebElement coupon_first; //第一个红包
	@FindBy(how = How.XPATH, using = "//div[@class='redpocket-result']")
	WebElement confirm_popup; //领取红包成功的弹出框
	
	/**
	 * 点击第一个红包
	 */
	public void click_firstcoupon() {
		coupon_first.click();
	}
	
	/**
	 * 
	 * @return
	 * 
	 * 领取成功的弹出框是否出现
	 */
	public boolean if_popuoshows() {
		return confirm_popup.isDisplayed();
	}
	
}
