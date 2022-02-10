package com.competition.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

import com.competition.dm.IDataModel;
import com.competition.dm.Team;
import com.competition.service.ContestService;
import com.competition.service.ICustomService;
import com.competition.service.MatchService;
import com.competition.service.TeamService;
import com.competition.utility.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class HandleRequest implements Runnable
{
	Socket socket;
	public HandleRequest(Socket socket)
	{
		this.socket = socket;
	}

	
	private String readBytesRequest() throws IOException
	{		
		InputStream in = socket.getInputStream(); // stream of bytes to read
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = in.read(data, 0, data.length)) != -1)
		{
		  buffer.write(data, 0, nRead);
		}
		String contents = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
		System.out.println(contents);
		
		in.close();
		
		return contents;
	}
	
	@Override
	public void run()
	{
		try
		{
			//Read the Request
			System.out.println(">> Request port: " + socket.getPort());
			String request = readBytesRequest();
			Type type = new TypeToken<RequestData>(){}.getType();
			Gson gson = new Gson();
			RequestData re = gson.fromJson(request, type);
			
			if(re.get_action() == "get")
			{
				//send data to the client
			}
			else
			{
				//Parse the Data Model
				IDataModel model = data_conversion(re.get_objType(), re.get_data());
				
				//Do the given action
				//do_action(re.get_action(), model);
				switch(re.get_objType())
				{
					case "Team":
					{
						do_action(re.get_action(), model, re.get_objType());
						break;
					}
				}
			}
			
			
			
			socket.close();
			System.out.println("<< Request ended");
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	
	private static <T extends IDataModel> void do_action(String action, T model, String type)
	{
		ICustomService<String, T> ser = null;
		/*
		 * Basically have this whole function ability in the Utility Class.
		 * Have 3 functions there, each gets the RequestData and Parses to the Data, does the action, and returns
		 * true or false if it managed something. 
		 * The functions will look exactly the same because fuck Generics. 
		 */
		if(action == "remove")//delete
		{
			ser.delete(model);
		}
		else if(action == "add")//add
		{
			ser.insert(model);
		}
	}
	
	private static IDataModel data_conversion(String type, String[] data)
	{
		if (data == null) return null;
		switch(type)
		{
			case "Team":
				return UtilityClass.ServerUtil.convertToTeam(data);
			case "Match":
				break;
			case "Contest":
				break;
		}
		return null;
	}
}
