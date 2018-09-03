package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class RestClient {

	// 1.GET method
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {
		// ClosableHttpclient is abstract class. Here Postman is ready to send
		// req
		CloseableHttpClient httpClient = HttpClients.createDefault();// creating
																		// HTTP
																		// client
		// Prepare request
		HttpGet httpGet = new HttpGet(url);// creating http Get request
		CloseableHttpResponse closableHttpResponse = httpClient.execute(httpGet);
		return closableHttpResponse;

	}

	// 2.Post method
	public CloseableHttpResponse post(String url, String entityString,  HashMap<String, String> headerMap)
			throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();// creating
																		// HTTP
																		// client
		HttpPost httppost = new HttpPost(url);
		// need to add header and pass Json body
		httppost.setEntity(new StringEntity(entityString));// for adding
															// payload(entity)
		// add header

		for (Entry<String, String> entry : headerMap.entrySet()) {
			httppost.addHeader(entry.getKey(), entry.getValue());
		}
		CloseableHttpResponse closablehttpresponse = httpClient.execute(httppost);
		return closablehttpresponse;

	}
	
	// 3.PUT call
		public CloseableHttpResponse put(String url, String entityString,  HashMap<String, String> headerMap)
				throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();// creating
																			// HTTP
																			// client
			HttpPut httpput = new HttpPut(url);
			// need to add header and pass Json body
			httpput.setEntity(new StringEntity(entityString));// for adding
																// payload(entity)
			// add header

			for (Entry<String, String> entry : headerMap.entrySet()) {
				httpput.addHeader(entry.getKey(), entry.getValue());
			}
			CloseableHttpResponse closablehttpresponse = httpClient.execute(httpput);
			return closablehttpresponse;
		}
		//4.Delete call
		
		public CloseableHttpResponse delete(String url)
				throws ClientProtocolException, IOException {
			CloseableHttpClient httpClient = HttpClients.createDefault();// creating HTTP Client																
			HttpDelete httpdelete = new HttpDelete(url);
			CloseableHttpResponse closablehttpresponse = httpClient.execute(httpdelete);
			return closablehttpresponse;
		}
		

}
