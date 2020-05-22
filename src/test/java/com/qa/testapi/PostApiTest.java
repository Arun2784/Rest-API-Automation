package com.qa.testapi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpEntity;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.client.RestPostClient;
import com.qa.testdata.Users;

public class PostApiTest extends TestBase {

	TestBase testbase;
	public String serviceurl;
	public String apiurl;
	public String url;
	RestPostClient restPostClient;

	CloseableHttpResponse closebaleHttpResponse;

	@BeforeMethod()

	public void setup() {
		testbase = new TestBase();

		serviceurl = prop.getProperty("serviceURL");

		apiurl = prop.getProperty("apiUrl");

		url = serviceurl + apiurl;
	}

	@Test

	public void postApiTest() throws JsonGenerationException, JsonMappingException, IOException {

		restPostClient = new RestPostClient();

//		HashMap<String, String> headerMap = new HashMap<String, String>();
//
//		headerMap.put("Cookie", "__cfduid=de08b0ebaf59aa58413da799ee785db511588100450");
//		headerMap.put("Postman-Token", "<calculated when request is sent>");
//		headerMap.put("Content-Type", "application/json");
//		headerMap.put("Content-Length", "<calculated when request is sent>");
//		headerMap.put("Host", "<calculated when request is sent>");
//		headerMap.put("User-Agent", "PostmanRuntime/7.24.1");
//		headerMap.put("Accept", "*/*");
//		headerMap.put("Accept-Encoding", "gzip, deflate, br");
//		headerMap.put("Connection", "keep-alive");

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-Type", "application/json");

		// jackson API:
		ObjectMapper mapper = new ObjectMapper();
		Users users = new Users("morpheus", "leader"); // expected users obejct

		// object to json file:
		mapper.writeValue(new File("D:\\Core Java\\restapi\\src\\main\\java\\com\\qa\\testdata\\users.json"), users);

		// java object to json in String:
		String usersJsonString = mapper.writeValueAsString(users);
		System.out.println(usersJsonString);

		closebaleHttpResponse = restPostClient.post(url, usersJsonString, headerMap); // call the API 

		// validate response from API:
		// 1. status code:
		int statusCode = closebaleHttpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_201);

		// 2. JsonString:
		String responseString = EntityUtils.toString(closebaleHttpResponse.getEntity(), "UTF-8");

		JSONObject responseJson = new JSONObject(responseString);
		System.out.println("The response from API is:" + responseJson);
		
		//Json to Java Object
		
		Users userResobj = mapper.readValue(responseString, Users.class); //Actual user Object
		System.out.println(userResobj);
		
		Assert.assertTrue(users.getName().equals(userResobj.getName()));
		
		Assert.assertTrue(users.getJob().equals(userResobj.getJob()));
		
		
		System.out.println(userResobj.getId());
		
		System.out.println(userResobj.getCreatedAt());

	}

}
