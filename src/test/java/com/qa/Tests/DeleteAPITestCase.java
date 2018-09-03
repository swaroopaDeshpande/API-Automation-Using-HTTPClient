package com.qa.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class DeleteAPITestCase {

	String url = "https://reqres.in/api/users";
	String apiUrl;
	RestClient restClient;

	@BeforeTest
	public void setUP() {
		apiUrl = url + "/2";
	}

	@Test
	public void deleteAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse closableHttpResponse = restClient.delete(apiUrl);

		// 1.Status code
		int statuscode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("The status Code is :" + statuscode);
		Assert.assertEquals(statuscode, 204);

		// 2.Json String :to get json response
		HttpEntity httpEntity = closableHttpResponse.getEntity();
		Assert.assertNull(httpEntity);

		// 3.To Get Headers
		Header headerarray[] = closableHttpResponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();

		for (Header header : headerarray) {
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("Headers are coming in Response :" + allHeaders);
	}

}
