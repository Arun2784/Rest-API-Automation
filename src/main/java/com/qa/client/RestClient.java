package com.qa.client;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	public void get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpGet httpget = new HttpGet(url); // http Get request
		CloseableHttpResponse httpresponse = httpclient.execute(httpget); // hit the Get Url

		//1. Status code
		
		int statuscode = httpresponse.getStatusLine().getStatusCode();

		System.out.println("status code = " + statuscode);
		
		//2.Response code or Json String

		String responseString = EntityUtils.toString(httpresponse.getEntity(), "UTF-8");

		JSONObject responsejson = new JSONObject(responseString);

		System.out.println("Response JSON from API --" + responsejson);
		
		
		//3.All Headers

		Header[] headersArray=  httpresponse.getAllHeaders();
		
		
		HashMap<String, String> allheadrs = new HashMap<String, String>();
		
		for (Header header : headersArray) {
			
			allheadrs.put(header.getName(), header.getValue());
			
		}
		
		System.out.println("Headers Array--->"+allheadrs);
		
	}

}
