package com.milanoo.configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Global {
	
	/**
	 * hub 地址
	 */
	public static final String HUB = "https://jetlyu1:kjikHKiKe4NfPwsz2rkH@hub-cloud.browserstack.com/wd/hub";
	
	/**
	 * 导入UI系统已经封装的所有页面
	 */
	/**
	 * 购物车页面
	 */
	public static final String MilanooCartPage_cartpage_button_checkout="button[@id='pay_forward_url_link']";  //Check out 按钮                                                                                         ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_table_main="table[@class='cartlist']";  //商品列表的table                                                                                           ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_li_subtotal="li[@id='cart_price']";  //Subtotal的li                                                                                          ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_li_shippingprice="li[@id='item_shipping_price']";  //运费的li                                                                                                ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_li_total="li[@id='item_shipping_total']";  //总价的li                                                                                                ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_li_coupon="li[@id='couponItem_price']";  //折扣的li                                                                                                ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_couponcode="input[@id='libkey']";  //折扣编码输入框                                                                                              ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_button_apply="input[@id='libkey_btn']";  //折扣券的apply按钮                                                                                          ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_li_autocoupon="li[@id='autoUsedCoupons_price']";  //自动折扣的li                                                                                              ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_userlogin="input[@id='loginusername']";  //快捷登录的Email框                                                                                          ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_userpassword="input[@id='loginuserpass']";  //快捷登录的密码框                                                                                             ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_button_cancel="form[@id='login']//input[@value='Cancel']";  //快捷登录中登录Tab的Cancel按钮                                                                                  ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_button_login="form[@id='login']//input[@value='Login']";  //快捷登陆框中登录Tab的Login按钮                                                                                  ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_tab_signin="a[@href='#sc_sign' and text()='Sign in']";  //快捷登录框上方的SignIn Tab                                                                                   ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_tab_register="a[@href='#sc_reg' and text()='Register']";  //快捷登陆框上方的Register Tab                                                                                 ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_regemail="input[@id='email']";  //快捷注册Email                                                                                            ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_regpassword="input[@id='UserPass']";  //快捷注册密码                                                                                               ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_input_regconfirmpassword="input[@id='UserPass2']";  //快捷注册确认密码                                                                                             ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_button_cancelreg="form[@id='reg']//input[@value='Cancel']";  //快捷注册框的取消按钮                                                                                           ;;;;;;;;;
	public static final String MilanooCartPage_cartpage_button_reg="input[@id='regBtn']";  //快捷注册框的注册按钮                                                                                           ;;;;;;;;;
	
	/**
	 * 空购物车页面
	 */
	public static final String MilanooEmptyCartPage_emptypage_div_empty="div[contains(@class, 'empty_cart')]";  //空购物车的div                                                                                             ;;;;;;;;;
	
	/**
	 * 主页
	 */
	public static final String MilanooHomePage_homepage_button_signin="a[@class='signIn']";  //右上方登录按钮                                                                                              ;;;;;;;;;
	
	/**
	 * 登录页
	 */
	public static final String MilanooLoginPage_loginpage_input_username="input[@name='loginusername']";  //登录邮箱框                                                                                                ;;;;;;;;;
	public static final String MilanooLoginPage_loginpage_input_password="input[@name='loginuserpass']";  //登录密码框                                                                                                ;;;;;;;;;
	public static final String MilanooLoginPage_loginpage_button_signin="span[text()='{-$lang.login.signIn}']";  //登录按钮                                                                                                 ;;;;;;;;;
	public static final String MilanooLoginPage_loginpage_button_paypallogin="span[@id='myContainer']//b";  //Paypal登录按钮                                                                                           ;;;;;;;;;
	
	/**
	 * 订单确认页
	 */
	public static final String MilanooOrderConfirmPage_confirmpage_button_placeorder="input[@value='{-$lang.orderConfirm.placeOrder}']";  //下单按钮                                                                                                 ;;;;;;;;;
	
	/**
	 * 付款页面
	 */
	public static final String MilanooPayoutPage_paypage_radio_creditcard="input[@id='xyk']";  //选择信用卡付款的radio                                                                                        ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_radio_paypal="input[@id='PayPal']";  //选择Paypal付款的radio                                                                                     ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_radio_western="input[@id='xlmk']";  //选择西联汇款的radio                                                                                         ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_radio_banktobank="input[@id='yhhk']";  //选择银行直汇的radio                                                                                         ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_select_cardtype="select[@id='select_card']";  //选择信用卡类型的select                                                                                       ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_input_cardnum="input[@name='cardno']";  //输入信用卡号的输入框                                                                                           ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_input_ownername="input[@name='cardHolderName']";  //信用卡持有者输入框                                                                                            ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_select_expiremonth="select[@name='expirymonth']";  //信用卡过期月份select                                                                                        ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_select_expireyear="select[@name='expiryyear']";  //信用卡过期年份的select                                                                                       ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_input_securitycode="input[@name='cvc']";  //信用卡安全码输入框                                                                                            ;;;;;;;;;
	public static final String MilanooPayoutPage_paypage_button_pay="input[@value='{-$lang.pay.pay}']";  //确认付款按钮                                                                                               ;;;;;;;;;
	
	/**
	 * 商品详情页
	 */
	public static final String MilanooPDetailPage_pdpage_input_selectcolor="option[contains(text(),'Select  Color')]//following::div[@class='select_please']/parent::div";  //选择颜色的下拉框                                                                                             ;;;;;;;;;
	public static final String MilanooPDetailPage_pdpage_input_selectsize="div[@class='sizechart_wrapper']";  //选择尺码的下拉框                                                                                             ;;;;;;;;;
	public static final String MilanooPDetailPage_pdpage_button_addtocart="button[@id='addtobag']";  //加入购物车按钮                                                                                              ;;;;;;;;;
	
	/**
	 * 搜索结果页
	 */
	
	public static final String MilanooSearchResultPage_rspage_div_itemlist="div[contains(@class, 'proItem')]";  //搜索结果列表                                                                                               ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_popular="div[@class='d_mode']//a[text()='{-$lang.category.sortByPopular}']";  //排序条件Popular                                                                                          ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_newin="div[@class='d_mode']//a[text()='{-$lang.category.sortByNewIn}']";  //排序条件NewIn                                                                                            ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_price="div[@class='d_mode']//a[text()='{-$lang.category.sortByPrice}']";  //排序条件Price                                                                                            ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_mostreviews="div[@class='d_mode']//a[text()='{-$lang.category.sortByMostReviews}']";  //排序条件Most Reviews                                                                                     ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_sale="div[@class='d_mode']//a[text()='{-$lang.category.sortBySale}']";  //排序条件Sale                                                                                             ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_video="div[@class='d_mode']//a[text()='{-$lang.category.sortByVideo}']";  //排序条件Video                                                                                            ;;;;;;;;;
	public static final String MilanooSearchResultPage_rspage_sort_24hourship="div[@class='d_mode']//a[text()='{-$lang.category.sortBy24hourship}']";  //排序条件24-Hours Shipping                                                                                ;;;;;;;;;
	
	/**
	 * 公共头部
	 */
	public static final String MilanooTopHeader_header_logo_milanoo="div[@id='logo']/a";  //主logo 点击可跳回主页                                                                                        ;;;;;;;;;
	public static final String MilanooTopHeader_header_input_search="input[@id='keyword']";  //搜索栏输入框                                                                                               ;;;;;;;;;
	public static final String MilanooTopHeader_header_button_search="input[@class='go_search']";  //搜索按钮                                                                                                 ;;;;;;;;;
	public static final String MilanooTopHeader_header_text_username="div[@class='h_user_info']/a";  //右上方显示用户名的标签                                                                                          ;;;;;;;;;
	public static final String MilanooTopHeader_header_panel_minicart="div[contains(@class, 'mini_cart')]/dl/dt";  //迷你购物车面板                                                                                              ;;;;;;;;;
	public static final String MilanooTopHeader_header_button_entercart="a[@class='m_checkOut']";  //迷你购物车中进入购物车的按钮                                                                                       ;;;;;;;;;
	public static final String MilanooTopHeader_header_nav_panel="ul[@id='catalog']";  //导航栏面板                                                                                                ;;;;;;;;;
	public static final String MilanooTopHeader_header_currency_container="li[@seojs='curreny']";  //选择货币的容器                                                                                              ;;;;;;;;;
	public static final String MilanooTopHeader_header_minicart_price="div[@class='m_t_price']/em/span[@class='smt_price']";  //迷你购物车中商品单价（只有一个商品时）                                                                                  ;;;;;;;;;
	
	
	/**
	 * 后台订单中心
	 */
	public static final String MilanooHTOrderCenterLogin_oclogin_input_username="input[@name='username']";  //用户名输入框                                                                                               ;;;;;;;;;
	public static final String MilanooHTOrderCenterLogin_oclogin_input_password="input[@name='password']";  //密码输入框                                                                                                ;;;;;;;;;
	public static final String MilanooHTOrderCenterLogin_oclogin_button_login="input[@value='登录']";  //登录按钮                                                                                                 ;;;;;;;;;
	
	
	/**
	 * 新版购物车页面
	 */
	public static final String MilanooNewCartPage_newcart_div_milanoo="div[@id='milanoo_temp']";  //购物车米兰站版块                                                                                             ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_subtotal="div[@id='milanoo_temp']//li[@id='cart_price']";  //米兰版块SubTotal                                                                                         ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_shippingprice="div[@id='milanoo_temp']//li[@id='item_shipping_price']";  //米兰版块邮费总计                                                                                             ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_savings="div[@id='milanoo_temp']//li[@id='autoUsedCoupons_price']";  //米兰版块Savings                                                                                          ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_finaltotal="div[@id='milanoo_temp']//li[@id='item_shipping_total']";  //最终的总价                                                                                                ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_coupon="div[@id='milanoo_temp']//em[@class='useCouponTips_tipsbtn']/span[@class='smt_price']";  //米兰手动使用的折扣券                                                                                           ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_codeinput="div[@id='milanoo_temp']//input[@id='libkey2']";  //米兰版块Code输入框                                                                                          ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_applybutton="div[@id='milanoo_temp']//input[@id='libkey_btn2']";  //米兰版块code的Apply按钮                                                                                     ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_clear="div[@id='milanoo_temp']//button[text()='{-$lang.newcart.clear}']";  //米兰版块清空购物车按钮                                                                                          ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_checkout="div[@id='milanoo_temp']//button[@id='pay_forward_url_link2']";  //米兰板块Check out按钮                                                                                      ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_paypalcheckout="a[@class='paypal']";  //米兰版块Paypal快捷支付按钮                                                                                     ;;;;;;;;;
	public static final String MilanooNewCartPage_newcart_milanoo_confirmremovebutton="div[@id='clearCart']//span[text()='{-$lang.cart.confirmremove}']";  //确认清空购物车按钮                                                                                            ;;;;;;;;;
	
	/**
	 * Paypal登录页面
	 */
	public static final String MilanooPaypalLoginPage_pplogin_span_orderid="div[@id='miniCart']//span[@class='desc']";  //订单号的span                                                                                             ;;;;;;;;;
	public static final String MilanooPaypalLoginPage_pplogin_input_email="input[@id='login_email']";  //邮箱输入框                                                                                                ;;;;;;;;;
	public static final String MilanooPaypalLoginPage_pplogin_input_password="input[@id='login_password']";  //密码输入框                                                                                                ;;;;;;;;;
	public static final String MilanooPaypalLoginPage_pplogin_checkbox_isprivate="input[@id='privateDeviceCheckbox']";  //是否是私人计算机勾选框                                                                                          ;;;;;;;;;
	public static final String MilanooPaypalLoginPage_pplogin_button_login="input[@id='submitLogin']";  //登录按钮                                                                                                 ;;;;;;;;;
	
	/**
	 * 注册页面
	 */
	public static final String MilanooRegistPage_regpage_text_newcustomer="h2[text()='{-$lang.reg.textNewCustomer}']";  //注册框文字New Customers                                                                                   ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_input_email="input[@id='email']";  //邮箱输入框                                                                                                ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_input_password="input[@id='UserPass']";  //密码输入框                                                                                                ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_input_confirmpassword="input[@id='UserPass2']";  //确认密码输入框                                                                                              ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_checkbox_agree="input[@name='Conditions']";  //同意条款勾选框                                                                                              ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_checkbox_newsletter="input[@name='emailsDy']";  //订阅邮件勾选框                                                                                              ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_radio_invited="input[@id='yes_radio']";  //Radio朋友推荐：Yes                                                                                        ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_radio_notinvited="input[@id='no_radio']";  //Radio朋友推荐：No                                                                                         ;;;;;;;;;
	public static final String MilanooRegistPage_regpage_button_reg="span[text()='{-$lang.reg.regButton}']";  //注册按钮                                                                                                 ;;;;;;;;;
	
	/**
	 * wap登录页
	 */
	public static final String MilanooWapLoginPage_waplogin_email="input[@id='loginusername']";  //EMAIL输入框                                                                                             ;;;;;;;;;
	public static final String MilanooWapLoginPage_waplogin_password="input[@id='loginuserpass']";  //PASSWORD输入框                                                                                          ;;;;;;;;;
	public static final String MilanooWapLoginPage_waplogin_signin="button[text()='{-$lang.login.signIn}']";  //登录按钮                                                                                                 ;;;;;;;;;

	
	/**
	 * 
	 * @param key
	 * @return
	 * 
	 * 读取配置文件
	 */
	public static String getConfiguration(String key) {
		String value;
		Properties prop = new Properties();
		InputStream in = Global.class
				.getResourceAsStream("configure.properties");
		try {
			prop.load(in);
			value = prop.getProperty(key).trim();
			return value;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

	}
}
