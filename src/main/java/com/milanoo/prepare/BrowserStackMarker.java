package com.milanoo.prepare;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;

import com.milanoo.configure.Global;
/**
 * 标识该测试成功或者失败
 * @author Administrator
 *
 */
public class BrowserStackMarker {
	
	private String sessionId;
	
	public BrowserStackMarker(String sessionId) {
		this.sessionId = sessionId;
	}
	
	public void mark(String status, String reason) throws URISyntaxException, UnsupportedEncodingException, IOException {
	  URI uri = new URI(Global.MARK + "/" + sessionId + ".json");
	  HttpPut putRequest = new HttpPut(uri);

	  ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
	  nameValuePairs.add((new BasicNameValuePair("status", status)));
	  nameValuePairs.add((new BasicNameValuePair("reason", reason)));
	  putRequest.setEntity(new UrlEncodedFormEntity(nameValuePairs));

	  HttpClientBuilder.create().build().execute(putRequest);
	}

	public void markError() throws UnsupportedEncodingException, URISyntaxException, IOException {
		mark("error", "");
	}
	
	
	public void markValidationError() throws UnsupportedEncodingException, URISyntaxException, IOException {
		mark("error", "Validation failed");
	}
	
	public void markErrorWithMessage(String msg) throws UnsupportedEncodingException, URISyntaxException, IOException {
		mark("error", msg);
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
}
