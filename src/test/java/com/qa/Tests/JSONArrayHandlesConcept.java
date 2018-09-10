package com.qa.Tests;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class JSONArrayHandlesConcept {

	String url;
	RestClient restClient;

	@BeforeMethod
	public void setUP() {
		url = "https://restcountries.eu/rest/v2/capital/Washington";
	}

	@Test
	public void getAPITest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		CloseableHttpResponse Response = restClient.get(url);

		// 1.Status code
		int statuscode = Response.getStatusLine().getStatusCode();
		System.out.println("The status Code is :" + statuscode);
		Assert.assertEquals(200, statuscode);

		// 2.Json String :to get json response
		HttpEntity httpEntity = Response.getEntity();
		String responseString = EntityUtils.toString(httpEntity);
		System.out.println(responseString);// with []
		responseString = responseString.substring(1, responseString.length());
		responseString = responseString.substring(0, responseString.length() - 1);
		System.out.println("Actual Json response String is " + responseString);

		// String to Json Conversion
		JSONObject jsonResponseObj = new JSONObject(responseString);
		System.out.println("the actual Json respones is" + jsonResponseObj);
		// get values from json obj
		String CountryName = jsonResponseObj.getString("name");
		System.out.println("the country name is :" + CountryName);
		Assert.assertEquals("United States of America", CountryName);
		JSONArray spellingarray=jsonResponseObj.getJSONArray("altSpellings");
		System.out.println(spellingarray);
	}

}
