package com.qa.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.client.RestClientsReqResCode;

public class GetCityInfo {
	String url = "https://restcountries.eu/rest/v2/capital";
	String apiUrl;
	RestClientsReqResCode restClient;

	@BeforeTest
	public void setUP() {
		apiUrl = url + "/Washington";

	}

	@Test
	public void GetCallDemoTest() throws ClientProtocolException, IOException {
		restClient = new RestClientsReqResCode();
		CloseableHttpResponse closableHttpResponse = restClient.getCallMethod(apiUrl);
		// validation
		// 1.StatusCode --statusLine.statusCode
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code is" + statusCode);
		Assert.assertEquals(statusCode, 200);
		// 2 need to convert resp in json
		HttpEntity httpEntity = closableHttpResponse.getEntity();
		String responseBody = EntityUtils.toString(httpEntity);
		System.out.println(responseBody);
		// 3.headers validation
		Header[] headers = closableHttpResponse.getAllHeaders();
		// HashMap required as headers
		HashMap<String, String> hm = new HashMap<String, String>();
		for (Header header : headers) {
			hm.put(header.getName(), header.getValue());
		}

		System.out.println("headers are coming in response" + hm);

	}

}
