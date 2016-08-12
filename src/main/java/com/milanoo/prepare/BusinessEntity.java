package com.milanoo.prepare;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Administrator
 *	
 * 构造test Suite的实体
 */
public class BusinessEntity {
	private String projectName;
	private String lang; //eg : en de es etc...
	private int threadCount;
	private Map<String, String> parameters;
	private List<BrowserCap> browserCaps;
	private String testClass;
	
	public BusinessEntity(String projectName, String lang, int threadCount, Map<String, String> parameters, String testClass) {
		this.projectName = projectName;
		this.lang = lang;
		this.threadCount = threadCount;
		this.parameters = parameters;
		this.testClass = testClass;
	}
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public int getThreadCount() {
		return threadCount;
	}
	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	public List<BrowserCap> getBrowserCaps() {
		return browserCaps;
	}
	public void setBrowserCaps(List<BrowserCap> browserCaps) {
		this.browserCaps = browserCaps;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}
	
	
}
