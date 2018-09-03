package com.qa.Tests;

import java.io.IOException;
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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.client.RestCalls;
import com.users.data.Customers;

public class PostCallDemo {

	String url = "https://reqres.in";
	String apiUrl;
	RestCalls restCalls;

	@BeforeMethod
	public void setUp() {
		apiUrl = url + "/api/users";
	}

	@Test
	public void PostCall_CreateCustomerTest() throws ClientProtocolException, IOException {
		// 1.create object of Restcalls java class where we have written method
		// for POST
		restCalls = new RestCalls();
		// 2. Prepare header
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");
		// 3.Prepare JSON Payload:jackson api,core jackson,Jackson dataBind apis
		ObjectMapper mapper = new ObjectMapper();
		Random random = new Random();
		Customers customer = new Customers("Swaroopa" + random.nextInt(100), "Leader" + random.nextInt(100));
		String CustomersJsonString = mapper.writeValueAsString(customer);
		System.out.println("payload is" + CustomersJsonString);
		// execute post method
		CloseableHttpResponse closablehttpResponse = restCalls.PostCall(apiUrl, CustomersJsonString, headerMap);
		// validation: 1 Status code
		int statusCode = closablehttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, 201);
		// 2.Validate JSON Payload from response
		HttpEntity httpEntity = closablehttpResponse.getEntity();
		String responseStringCustomers = EntityUtils.toString(httpEntity);
		System.out.println("Response body is " + responseStringCustomers);
		// String responseStringCustomers =
		// EntityUtils.toString(closablehttpResponse.getEntity());
		// whatever response we get , its in pure STring format we need to
		// convert this string into json --JSONObject we need to create
		JSONObject responseJson = new JSONObject(responseStringCustomers);
		System.out.println("The Json response is: " + responseJson);

		// 5. Convert Json response to java object:: deserialization
		// unmarshalling--required objectMapper method readvalue
		Customers customerObj = mapper.readValue(responseStringCustomers, Customers.class);
		System.out.println(customerObj.getName());
		System.out.println(customerObj.getJob());
		System.out.println(customerObj.getId());
		System.out.println(customerObj.getCreatedAt());
	}

}
