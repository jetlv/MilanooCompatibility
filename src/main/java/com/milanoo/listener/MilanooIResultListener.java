package com.milanoo.listener;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import com.milanoo.tests.TestBase;

/**
 * 
 * @author Administrator 测试报告截图配置
 */
public class MilanooIResultListener extends TestListenerAdapter {
	
		
	/**
	 * 失败后截图
	 */
	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Failure detected");
		takeScreenShot(result);
	}

	/**
	 * 自动截图，保存图片到本地以及html结果文件中
	 * 
	 * @param tr
	 */
	private void takeScreenShot(ITestResult tr) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String mDateTime = formatter.format(new Date());
		String fileName = mDateTime + "_" + tr.getName();
		String filePath = "pics/" + fileName + ".png"; // 项目根目录/pics下生成图片
		File f = ((TakesScreenshot) ((TestBase) tr.getInstance()).getDriver())
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(f, new File(filePath));
			Reporter.setCurrentTestResult(tr);
			Reporter.log(filePath);
			// 这里实现把图片链接直接输出到结果文件中，通过邮件发送结果则可以直接显示图片
			Reporter.log("<img src=\"../../" + filePath + "\"/>");
		} catch (IOException e) {
			// do nothing
		}
	}
}
