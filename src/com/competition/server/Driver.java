package com.competition.server;

import java.io.IOException;

/*
 * Made by Asaf Dangoor (ID 313307340) - asafdangoor@gmail.com
 * Made by Yonatan Avizov (ID 318432101) - yonatanavizov1997@gmail.com
 */

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