package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestCalls {

	// CRUD operations Methods
	// GEt call

	public CloseableHttpResponse GetCall(String url) throws ClientProtocolException, IOException {
		// 1.Just like in restAssured (given()) method to create Httpclient we
		// need createDefault method
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 2.Prepare HTTP req we need httpGet class craete object and pass url
		HttpGet httpGet = new HttpGet(url);
		// 3.Execute request
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpGet);
		return closableHttpResponse;
	}

	// Post Call
	public CloseableHttpResponse PostCall(String url, String entityString, HashMap<String, String> HeaderMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// create
																		// HTTPClient
		HttpPost httpPost = new HttpPost(url);// http post request
		httpPost.setEntity(new StringEntity(entityString));// for adding payload
		// Add header : may be multiple headers will be there 3 to 4 :
		// Content-type,Auth
		for (Entry<String, String> entry : HeaderMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		// execute
		CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost);
		return closeableHttpResponse;
	}

	// Put Call

	public CloseableHttpResponse PutCall(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut();
		httpPut.setEntity(new StringEntity(entityString));
		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpPut.addHeader(entry.getKey(), entry.getValue());
		}
		// execute
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpPut);
		return closableHttpResponse;

	}

}
