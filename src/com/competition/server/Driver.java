package com.competition.server;

import java.io.IOException;

public class Driver
{
	
	public static void main(String[] args)
	{
		Server server;
		try
		{
			server = new Server(34567);
			new Thread(server).start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}