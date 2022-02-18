package com.competition.server;

import java.io.IOException;
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
			Socket someClient;
			try
			{
				someClient = serverSocket.accept();
				System.out.println("request["+requestId+"] incoming.");
				new Thread(new HandleRequest(someClient)).start();
				requestId++;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("Closing Server.");
	}
}
