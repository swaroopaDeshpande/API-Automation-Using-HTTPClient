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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClientsReqResCode;

public class GetcallDemo {

	String url = "http://restapi.demoqa.com/utilities/weather/city";
	String apiUrl;
	RestClientsReqResCode restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/Pune";
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClientsReqResCode();
		CloseableHttpResponse closableHttpResponse = restClient.getCallMethod(apiUrl);
		// validation
		// 1.StatusCode --statusLine.statusCode
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("status code is" + statusCode);
		Assert.assertEquals(statusCode, 200);
		// 2.Response body-Json String--HttpEntity required for conversion of
		// response in JSON
		HttpEntity httpEntity = closableHttpResponse.getEntity();
		String ResponseString = EntityUtils.toString(httpEntity);
		System.out.println("Response body is " + ResponseString);
		//3.Headers
		Header headersArray[]=closableHttpResponse.getAllHeaders();
		HashMap<String,String > allHeaders=new HashMap<String,String>();
		for(Header header:headersArray)
		{
			allHeaders.put(header.getName(), header.getValue());
		}
		System.out.println("headers are coming in response"+allHeaders);
		
	}

}
