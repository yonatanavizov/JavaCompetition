package com.competition.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


public class HandleRequest implements Runnable
{
	
	String request;
	Socket socket;
	public HandleRequest(BufferedReader request) throws IOException
	{
		
	}

	
	private void readBytesRequest() throws IOException
	{
		System.out.println("Request port: " + socket.getPort());
		DataInputStream  in = new DataInputStream (socket.getInputStream());
		DataOutputStream  out = new DataOutputStream (socket.getOutputStream());
		//int len = in.
		byte[] bytes = new byte[1024];
		int s = in.read(bytes);
		String sr = new String(bytes, StandardCharsets.UTF_8);
		System.out.println(sr);
		
		//out.writeUTF("hello from server " + s);
		
		in.close();
		out.close();
		socket.close();
	}
	
	

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
