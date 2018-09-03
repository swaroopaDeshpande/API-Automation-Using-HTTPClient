package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClientsReqResCode {

	// 1.GET Call
	public CloseableHttpResponse getCallMethod(String url) throws ClientProtocolException, IOException {
		// 1.1 Create HTTP Client
		CloseableHttpClient httpClient = HttpClients.createDefault();
		// 1.2 Prepare request
		HttpGet httpGet = new HttpGet(url);
		// 1.3 execute req
		CloseableHttpResponse ClosablehttpResponse = httpClient.execute(httpGet);
		return ClosablehttpResponse;
	}

	// 2.Post Call Creation

	public CloseableHttpResponse postCallMethod(String url, String entityString, HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		// 1.create client
		CloseableHttpClient closableHttpClient = HttpClients.createDefault();
		// 2.Create PostReq
		HttpPost httpPost = new HttpPost(url);
		// 3.Json Payload
		httpPost.setEntity(new StringEntity(entityString));
		// 4.Create Headers
		for (Entry<String, String> entry : headerMap.entrySet()) {
			httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closeableHttpResponse = closableHttpClient.execute(httpPost);
		return closeableHttpResponse;

	}

}
