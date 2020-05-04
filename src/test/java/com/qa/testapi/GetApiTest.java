package com.qa.testapi;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.RestClient;

public class GetApiTest extends TestBase {

	TestBase testbase;
	public String serviceurl;

	public String apiurl;

	public String url;
	RestClient restClient;

	@BeforeMethod()

	public void setup() {
		testbase = new TestBase();

		serviceurl = prop.getProperty("serviceURL");

		apiurl = prop.getProperty("apiUrl");

		url = serviceurl + apiurl;
	}

	@Test

	public void getAPITest() throws ClientProtocolException, IOException {

		restClient = new RestClient();

		restClient.get(url);

	}

}
