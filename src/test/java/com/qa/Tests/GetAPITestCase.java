package com.qa.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qa.client.RestClient;

public class GetAPITestCase {
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
		//
		// // 3.To Get Headers
		// Header headerarray[] = closableHttpResponse.getAllHeaders();
		// HashMap<String, String> allHeaders = new HashMap<String, String>();
		//
		// for (Header header : headerarray) {
		// allHeaders.put(header.getName(), header.getValue());
		// }
		// System.out.println("Headers are coming in Response :" + allHeaders);
		// String contentType = allHeaders.get("Content-Type");
		// System.out.println(allHeaders.get(contentType));
		// Assert.assertEquals("Content-Type", "Content-Type");

		// String to Json Conversion
		JSONObject jsonResponseObj = new JSONObject(responseString);
		System.out.println("the actual Json respones is" + jsonResponseObj);
		// get values from json obj
		String CountryName = jsonResponseObj.getString("name");
		System.out.println("the country name is :" + CountryName);
		Assert.assertEquals("United States of America", CountryName);

		// get values from JSON Array having only values
		JSONArray spellingArray = jsonResponseObj.getJSONArray("altSpellings");
		System.out.println("values from altspelling array is" + spellingArray);

		// get values from jsonarray key and value
		JSONArray currenciesArray = jsonResponseObj.getJSONArray("currencies");
		System.out.println(currenciesArray.getJSONObject(0));
		System.out.println(currenciesArray.getJSONObject(0).get("code").toString());
		System.out.println(currenciesArray.getJSONObject(0).get("symbol").toString());

		JSONArray regionalBlocs = jsonResponseObj.getJSONArray("regionalBlocs");
		System.out.println(regionalBlocs.getJSONObject(0));
		System.out.println(regionalBlocs.getJSONObject(0).get("acronym").toString());
		
		System.out.println(regionalBlocs.getJSONObject(0).get("otherNames"));
		String othernames= regionalBlocs.getJSONObject(0).get("otherNames").toString();
		//"Tratado de Libre Comercio de América del Norte","Accord de Libre-échange Nord-Américain"
		String othernamesstrArray[]=othernames.split(",");
		for(int i=0;i<othernamesstrArray.length;i++)
		{
			System.out.println(othernamesstrArray[i]);
		}
	}

}
