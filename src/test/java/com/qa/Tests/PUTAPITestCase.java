package com.qa.Tests;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClient;
import com.users.data.Users;
import com.users.data.UsersUpdate;

public class PUTAPITestCase {

	String url = "https://reqres.in";
	String apiUrl;
	RestClient restClient;

	@BeforeTest
	public void setUP() {
		apiUrl = url + "/api/users/2";
	}

	@Test
	public void UpdateUserTest() throws ClientProtocolException, IOException {
		restClient = new RestClient();
		// 1.prepare header
		HashMap<String, String> headermap = new HashMap<String, String>();
		headermap.put("Content-Type", "application/json");

		// 2.Prepare json payload -jackson api
		ObjectMapper mapper = new ObjectMapper();
		UsersUpdate usersupdate = new UsersUpdate("JerrY", "CEO");

		// 3.convert java object to json string serialization--marshalling
		String userUpdatesJsonString = mapper.writeValueAsString(usersupdate);
		System.out.println("Request payload is :" + userUpdatesJsonString);

		// 4.call Put call method
		CloseableHttpResponse closablehttpresponse = restClient.put(apiUrl, userUpdatesJsonString, headermap);

		// 5.get status code
		int statuscode = closablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("the status code is:" + statuscode);

		Assert.assertEquals(statuscode, 200);

		// //6.get Json payload response
		// HttpEntity httpEntity = closablehttpresponse.getEntity();
		// String responseString = EntityUtils.toString(httpEntity);
		// System.out.println(responseString);

		// 7.validate response
		String responseStringUsers = EntityUtils.toString(closablehttpresponse.getEntity());
		JSONObject responseJson = new JSONObject(responseStringUsers);
		System.out.println("the response json is " + responseJson);

		// 8.convert json String to java object: Deserialization/unmarshalling
		UsersUpdate usersObj = mapper.readValue(responseStringUsers, UsersUpdate.class);
		System.out.println(usersObj.getName());
		System.out.println(usersObj.getJob());
		Assert.assertEquals(usersObj.getName(), usersupdate.getName());
		
		System.out.println(usersObj.getUpdatedAt());
		Assert.assertNotNull(usersObj.getUpdatedAt());
	}

}
