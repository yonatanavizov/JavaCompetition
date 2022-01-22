package com.competition.server;

import java.io.BufferedReader;
import java.io.IOException;

import com.competition.utility.UtilityClass;

public class HandleRequest {
	
	String request;
	
	public HandleRequest(BufferedReader request) throws IOException
	{
		//this.request = UtilityClass.ReadClass.ReadAllLines(request);
		this.request = request.readLine();
	}
	
	public String get_request()
	{
		return request;
	}
}
