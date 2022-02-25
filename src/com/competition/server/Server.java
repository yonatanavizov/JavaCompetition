package com.competition.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable
{
	ServerSocket serverSocket;
	int port;
	int requestId;
	boolean serverup = true;
	
	public Server(int port) throws IOException
	{
		this.port = port;
		serverSocket = new ServerSocket(port);
		requestId = 0;
	}

	@Override
	public void run()
	{
		System.out.println("Server up, port [" + port + "]");
		while(serverup)
		{
			Socket socket;
			try
			{
				socket = serverSocket.accept();
				System.out.println("request["+requestId+"] incoming.");
				//new Thread(new HandleRequest(someClient)).start();
				new Thread(new FakeHandleRequest(socket)).start();
				requestId++;
			}
			catch (IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
		System.out.println("Closing Server.");
	}
}
