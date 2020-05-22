package com.qa.testapi;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;
import com.qa.client.ValidateJsonResponse;
import com.qa.util.TestUtil;

public class ValidateJsonResponseTest extends TestBase {

	TestBase testbase;
	public String serviceurl;

	public String apiurl;

	public String url;
	ValidateJsonResponse validaterestjsonresponse;
	CloseableHttpResponse closeableHttpResponse;

	@BeforeMethod()

	public void setup() {
		testbase = new TestBase();

		serviceurl = prop.getProperty("serviceURL");

		apiurl = prop.getProperty("apiUrl");

		url = serviceurl + apiurl;
	}

	@Test(priority = 1)

	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException {

		validaterestjsonresponse = new ValidateJsonResponse();

		CloseableHttpResponse closeableHttpResponse = validaterestjsonresponse.get(url);

		// Status code

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code-->  " + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// JSon string

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responsejson = new JSONObject(responseString);

		System.out.println("Response JSON from API is--->   " + responsejson);

		// Perpage Value

		String perPageValue = TestUtil.getValueByJPath(responsejson, "/per_page");

		System.out.println("Value of per Page is ---->  " + perPageValue);

		Assert.assertEquals(Integer.parseInt(perPageValue), 6, " Value is not 6 ");

		// Total Value:

		String totalValue = TestUtil.getValueByJPath(responsejson, "/total");

		System.out.println("Value of Total is ---->  " + totalValue);

		Assert.assertEquals(Integer.parseInt(totalValue), 12, " Value is not 12  ");

		// Get The Value from JSON Array

		String last_name = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");

		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);

		String last_name1 = TestUtil.getValueByJPath(responsejson, "/data[1]/last_name");
		String id1 = TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar1 = TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String first_name1 = TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");

		System.out.println(last_name1);
		System.out.println(id1);
		System.out.println(avatar1);
		System.out.println(first_name1);

		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheadrs = new HashMap<String, String>();

		for (Header header : headersArray) {

			allheadrs.put(header.getName(), header.getValue());

		}

		System.out.println("Headers Array--->   " + allheadrs);

	}

	@Test(priority = 2)

	public void getAPITestWithHeaders() throws ClientProtocolException, IOException {

		validaterestjsonresponse = new ValidateJsonResponse();

		HashMap<String, String> headerMap = new HashMap<String, String>();

		headerMap.put("Cookie", "__cfduid=de08b0ebaf59aa58413da799ee785db511588100450");
		headerMap.put("Postman-Token", "<calculated when request is sent>");
		headerMap.put("Host", "<calculated when request is sent>");
		headerMap.put("User-Agent", "PostmanRuntime/7.24.1");
		headerMap.put("Accept", "*/*");
		headerMap.put("Accept-Encoding", "gzip, deflate, br");
		headerMap.put("Connection", "keep-alive");

		closeableHttpResponse = validaterestjsonresponse.get(url, headerMap);

		// Status code

		int statuscode = closeableHttpResponse.getStatusLine().getStatusCode();

		System.out.println("Status code-->  " + statuscode);

		Assert.assertEquals(statuscode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// JSon string

		String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

		JSONObject responsejson = new JSONObject(responseString);

		System.out.println("Response JSON from API is--->   " + responsejson);

		// Perpage Value

		String perPageValue = TestUtil.getValueByJPath(responsejson, "/per_page");

		System.out.println("Value of per Page is ---->  " + perPageValue);

		Assert.assertEquals(Integer.parseInt(perPageValue), 6, " Value is not 6 ");

		// Total Value:

		String totalValue = TestUtil.getValueByJPath(responsejson, "/total");

		System.out.println("Value of Total is ---->  " + totalValue);

		Assert.assertEquals(Integer.parseInt(totalValue), 12, " Value is not 12  ");

		// Get The Value from JSON Array

		String last_name = TestUtil.getValueByJPath(responsejson, "/data[0]/last_name");
		String id = TestUtil.getValueByJPath(responsejson, "/data[0]/id");
		String avatar = TestUtil.getValueByJPath(responsejson, "/data[0]/avatar");
		String first_name = TestUtil.getValueByJPath(responsejson, "/data[0]/first_name");

		System.out.println(last_name);
		System.out.println(id);
		System.out.println(avatar);
		System.out.println(first_name);

		Header[] headersArray = closeableHttpResponse.getAllHeaders();

		HashMap<String, String> allheadrs = new HashMap<String, String>();

		for (Header header : headersArray) {

			allheadrs.put(header.getName(), header.getValue());

		}

		System.out.println("Headers Array--->   " + allheadrs);

	}

}
