package com.qa.Tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestClientsReqResCode;
import com.users.data.RegisterUserDetails;

public class PostRequestRegisterUser {

	String url = "https://reqres.in";
	String apiUrl;
	RestClientsReqResCode restClient;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/api/register";
	}

	@Test
	public void RegisterUserTest() throws ClientProtocolException, IOException {
		// 1.Create restclient object
		restClient = new RestClientsReqResCode();
		// 2.prepare header
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		headerMap.put("Authorization", "Bearer c05f2b475db324a2e4be720202859ef1");
		// 3.Prepare JSON Payload:jackson api,core jackson,Jackson dataBind
		// apis(Marshalling) First create POJO then convert pojo to json using
		// jackson,core,databind apis-ObjectMapper class
		ObjectMapper mapper = new ObjectMapper();
		Random random = new Random();
		RegisterUserDetails RegisterUsers = new RegisterUserDetails("sydney@fife" + random.nextInt(100),
				"pistol" + random.nextInt(100));
		String RegisterUserJson = mapper.writeValueAsString(RegisterUsers);
		System.out.println("payload is" + RegisterUserJson);
		// 4.execute post method
		CloseableHttpResponse closableHttpResponse = restClient.postCallMethod(apiUrl, RegisterUserJson, headerMap);
		// 5.1 validation Status code
		int statusCode = closableHttpResponse.getStatusLine().getStatusCode();
		System.out.println("Status code for request is" + statusCode);
		Assert.assertEquals(201, statusCode);
		// 5.2.Validate JSON Payload from response

		HttpEntity httpEntity = closableHttpResponse.getEntity();
		String responseStringCustomers = EntityUtils.toString(httpEntity);
		System.out.println("Response body is " + responseStringCustomers);
		System.out.println("Response body is " + responseStringCustomers);

		// 6.whatever response we get , its in pure STring format we need to
		// convert this string into json --JSONObject we need to create
		JSONObject responseJson = new JSONObject(responseStringCustomers);
		System.out.println("The Json response is: " + responseJson);
		// 5. Convert Json response to java object:: deserialization
		// unmarshalling--required objectMapper method readvalue
		// use mapper.readvalue
		RegisterUserDetails registerusers = mapper.readValue(responseStringCustomers, RegisterUserDetails.class);
		System.out.println(registerusers.getToken());

	}
}
