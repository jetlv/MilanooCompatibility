package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

//
public class SearchedPage {
	@FindBy(how= How.CLASS_NAME, using="goods_item")
	WebElement first_item; //第一个商品

	/**
	 * 点击第一个商品
	 */
	public void click_first_item() {
		first_item.click();
	}
}
