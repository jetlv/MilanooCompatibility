package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ShoppingCartPage {
	@FindBy(how= How.ID, using="pay_forward_url_link2")
	WebElement milanoo_checkout_button; //购物车米兰板块 checkout
	
	/**
	 * 购物车米兰板块checkout是否存在
	 * @return
	 */
	public boolean milanoo_checkout_button_exists() {
		
		return milanoo_checkout_button.isDisplayed();
	}
	
	/**
	 * 点击购物车米兰板块checkout
	 */
	public void click_milanoo_checkout_button() {
		milanoo_checkout_button.click();
	}
}
