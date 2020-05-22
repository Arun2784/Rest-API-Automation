package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class ValidateJsonResponse {

	// Get Method without headers

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url); // http get request

		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);//Hit

		return closeableHttpResponse;

	}

//Get Method with headers

	public CloseableHttpResponse get(String url, HashMap<String, String> headermap)
			throws ClientProtocolException, IOException {

		CloseableHttpClient httpclient = HttpClients.createDefault();

		HttpGet httpget = new HttpGet(url); // http get request
		
		
		for(Map.Entry<String, String> entry : headermap.entrySet()) {
			
			
			httpget.addHeader(entry.getKey(),entry.getValue());
		}
		
		

		CloseableHttpResponse closeableHttpResponse = httpclient.execute(httpget);

		return closeableHttpResponse;

	}

}
