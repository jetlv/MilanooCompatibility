package com.milanoo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import com.milanoo.configure.Global;

public class HomePage {
	@FindBy(how= How.CLASS_NAME, using="closetag")
	WebElement icon_close_welcome;
	@FindBy(how=How.CLASS_NAME, using="go_search")
	WebElement icon_search;
	
	/**
	 * 关闭欢迎新人的弹窗
	 */
	public void click_close_icon() {
		icon_close_welcome.click();
	}
	
	/**
	 * 验证搜索按钮是存在的
	 * @return
	 */
	public boolean verify_searchicon_visible()  {
		return icon_search.isDisplayed();
	}
	
	/**
	 * 点击搜索框
	 */
	public void click_searchicon() {
		icon_search.click();
		System.out.println("Searched icon was clicked");
	}
	
//	public void 
}
