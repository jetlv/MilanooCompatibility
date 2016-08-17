package com.milanoo.prepare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.junit.Ignore;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 
 * @author 准备用例
 */
public class Preparation {

	/** 生成的单个xml的路径 */
	List<String> paths;

	@BeforeTest
	public void setUp() {
		paths = new ArrayList<String>();
	}

	public static void main(String[] args) throws IOException {
		Preparation p = new Preparation();
		p.setUp();
		p.PrepareHalloween();
		p.tearDown();
	}

	@Test
	/**
	 * 万圣节准备
	 */
	public void PrepareHalloween() throws IOException {
		String[] langs = { "en", "de", "es", "fr", "it", "pt", "jp" };
//		String[] langs = { "en"};
		for (int i = 0; i < langs.length; i++) {
			String lang = langs[i];
			String project = "halloween";
			int threadCount = 4;
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("site", lang);
			parameters.put("pageUrl", "http://www.milanoo.com/" + lang
					+ "/search?type=search&keyword=");
			parameters.put("project", project);

			String testClass = "com.milanoo.tests.TestHalloween";

			BusinessEntity be = new BusinessEntity(project, lang, threadCount,
					parameters, testClass);
			
//			BrowserCap MAC_FIREFOX_47 = new BrowserCap("firefox", "47.0", "MAC");
			BrowserCap WIN7_CHROME_52 = new BrowserCap("chrome", "52.0", "WINDOWS");
			BrowserCap WIN7_FIREFOR_47 =new BrowserCap("firefox", "47.0", "WINDOWS"); 
			
			List<BrowserCap> caps = new ArrayList<BrowserCap>();
			
			caps.add(WIN7_FIREFOR_47);
			caps.add(WIN7_CHROME_52);
			
			be.setBrowserCaps(caps);
			
			writeSingleXML(be);

		}

	}

	@Test
	@Ignore
	/**
	 * 基本流程的xml准备
	 * @throws IOException
	 */
	public void prepareBaseProgress() throws IOException {
		String[] langs = { "en", "de", "es", "fr", "it", "pt", "ru", "jp" };
		for (int i = 0; i < langs.length; i++) {
			String lang = langs[i];
			String project = "baseprogress";
			int threadCount = 4;
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("site", lang);
			parameters.put("pageUrl", "http://www.milanoo.com/" + lang);
			parameters.put("project", project);
			parameters.put("productUrl", "http://www.milanoo.com/" + lang
					+ "/produkt/schoene-pvc-1-teilig-nail-sticker-p71814.html");
			String testClass = "com.milanoo.tests.BaseProgress";

			BusinessEntity be = new BusinessEntity(project, lang, threadCount,
					parameters, testClass);
			be.setBrowserCaps(commonBrowsers());

			
			writeSingleXML(be);

		}

	}

	@AfterTest
	public void tearDown() throws IOException {
		/** 写出testng */
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("suite");
		root.addAttribute("name", "All");
		Element lists = root.addElement("suite-files");

		for (int i = 0; i < paths.size(); i++) {
			String path = paths.get(i);

			Element sf = lists.addElement("suite-file");
			sf.addAttribute("path", path);
		}
		FileOutputStream fos = new FileOutputStream("testng.xml");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		OutputFormat of = new OutputFormat();
		of.setEncoding("UTF-8");
		of.setIndent(true);
		of.setIndent("    ");
		of.setNewlines(true);
		XMLWriter writer = new XMLWriter(osw, of);
		writer.write(document);
		writer.close();

	}

	/**
	 * 写单个xml
	 * 
	 * @param be
	 * @throws IOException
	 */
	private void writeSingleXML(BusinessEntity be) throws IOException {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("suite");
		root.addAttribute("name", be.getLang() + "_" + be.getProjectName());
		root.addAttribute("thread-count", String.valueOf(be.getThreadCount()));
		root.addAttribute("parallel", "tests");
		
//		/** 监听器注入 */
//		Element listeners = root.addElement("listeners");
//		Element resultListener = listeners.addElement("listener");
//		resultListener.addAttribute("class-name", "com.milanoo.listener.MilanooIResultListener");

		/** 参数注入 */
		Map<String, String> parameters = be.getParameters();

		Set<String> set = parameters.keySet();

		for (String s : set) {
			Element parameter = root.addElement("parameter");
			parameter.addAttribute("name", s);
			parameter.addAttribute("value", parameters.get(s));
		}

		/** 根据浏览器写test */
		List<BrowserCap> caps = be.getBrowserCaps();

		for (int i = 0; i < caps.size(); i++) {
			BrowserCap capability = caps.get(i);
			Element test = root.addElement("test");
			test.addAttribute("name",
					capability.getBrowserName() + "_" + be.getProjectName());
			Element browser = test.addElement("parameter");
			browser.addAttribute("name", "browser");
			browser.addAttribute("value", capability.getBrowserName());

			Element version = test.addElement("parameter");
			version.addAttribute("name", "version");
			version.addAttribute("value", capability.getBrowserVersion());

			Element platform = test.addElement("parameter");
			platform.addAttribute("name", "platform");
			platform.addAttribute("value", capability.getPlatform());

			Element tClasses = test.addElement("classes");
			Element tClass = tClasses.addElement("class");
			tClass.addAttribute("name", be.getTestClass());
		}
		String folder = "scenario/s_" + be.getProjectName();
		if (!new File(folder).exists()) {
			System.out.println(new File(folder).mkdirs());
		}
		// System.out.println(new File(folder).getAbsolutePath());

		FileOutputStream fos = new FileOutputStream(folder + "/"
				+ be.getProjectName() + "_" + be.getLang() + ".xml");
		OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8");
		OutputFormat of = new OutputFormat();
		of.setEncoding("UTF-8");
		of.setIndent(true);
		of.setIndent("    ");
		of.setNewlines(true);
		XMLWriter writer = new XMLWriter(osw, of);
		writer.write(document);
		writer.close();
		/** 写好一个后，把路径放到paths中 */
		paths.add(folder + "/" + be.getProjectName() + "_" + be.getLang()
				+ ".xml");
	}

	/**
	 * 
	 * @return
	 * 
	 *         常规的浏览器配置
	 */
	private List<BrowserCap> commonBrowsers() {
		BrowserCap MAC_FIREFOX_47 = new BrowserCap("firefox", "47.0", "MAC");
		BrowserCap WIN7_CHROME_52 = new BrowserCap("chrome", "52.0", "WINDOWS");
//		BrowserCap WIN10_EDGE_13 = new BrowserCap("edge", "13.0", "WINDOWS");
		BrowserCap WIN7_IE_11 = new BrowserCap("ie", "11.0", "WINDOWS");
		BrowserCap MAC_SAFARI_8 = new BrowserCap("safari", "8.0", "MAC");

		List<BrowserCap> bcs = new ArrayList<BrowserCap>();

		bcs.add(WIN7_IE_11);
		bcs.add(MAC_SAFARI_8);
		bcs.add(WIN7_CHROME_52);
		bcs.add(MAC_FIREFOX_47);

		return bcs;
	}
}
