package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductPage {
	
	@FindBy(how= How.ID, using="addtobag")
	WebElement button_addtocart; //加入购物车按钮

	/**
	 * 加入购物车按钮是否存在于屏幕上
	 */
	public boolean button_addtocart_displayed() {
		return button_addtocart.isDisplayed();
	}
	
	/**
	 * 点击加入购物车
	 */
	public void click_addtocart() {
		button_addtocart.click();
	}

}
