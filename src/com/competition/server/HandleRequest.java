package com.competition.server;

import java.io.BufferedReader;
import java.io.IOException;


public class HandleRequest {
	
	String request;
	
	public HandleRequest(BufferedReader request) throws IOException
	{
		this.request = request.readLine();
	}
	
	public String get_request()
	{
		return request;
	}
}
