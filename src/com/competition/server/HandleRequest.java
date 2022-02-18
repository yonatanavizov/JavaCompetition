package com.competition.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;

import com.competition.dm.Contest;
import com.competition.dm.IDataModel;
import com.competition.dm.Match;
import com.competition.dm.Team;
import com.controller.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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
			
			JsonDeserializer<RequestData> deserializer = new JsonDeserializer<RequestData>() {  
			    @Override
			    public RequestData deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
			    {
			        JsonObject jsonObject = json.getAsJsonObject();
			        
			        String action = jsonObject.get("action").getAsString();
			        String objType = jsonObject.get("objType").getAsString();
			        int amountOfObjects = jsonObject.get("amountOfObjects").getAsInt();
			        
			        RequestData re = new RequestData(action, objType, amountOfObjects);
			        Type type;
			        Gson gson = new Gson();
			        
			        switch(objType)
			        {
				        case "Team":
				        	Team[] teams = new Team[amountOfObjects];
				        	type = new TypeToken<Team[]>(){}.getType();
				        	teams = gson.fromJson(jsonObject.get("data"), type);
				        	
				        	re.set_data(teams);
				        	break;
				        case "Match":
				        	Match[] matches = new Match[amountOfObjects];
				        	type = new TypeToken<Match[]>(){}.getType();
				        	matches = gson.fromJson(jsonObject.get("data"), type);
				        	
				        	re.set_data(matches);
				        	break;
				        case "Contest":
				        	Contest[] contests = new Contest[amountOfObjects];
				        	type = new TypeToken<Contest[]>(){}.getType();
				        	contests = gson.fromJson(jsonObject.get("data"), type);
				        	
				        	re.set_data(contests);
				        	break;
			        	default:
			        		throw new JsonParseException("Wrong type of object");
			        }			        
			        
			        return re;
			    }
			};
			
			
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(RequestData.class, deserializer);
			Gson customGson = gsonBuilder.create();  
			RequestData requester = customGson.fromJson(request, RequestData.class);  
						
			//System.out.println(requester.toString());
			Controller controller = new Controller();
			
			if(requester.get_action().equals("add"))
			{
				controller.Add(requester.get_data());
			}
			else if(requester.get_action().equals("remove"))
			{
				controller.Delete(requester.get_data());
			}
			else if(requester.get_action().equals("get"))
			{
				//need to send back info.
				List<IDataModel> info = controller.get_db(requester.get_objType());
				
				if(info.get(0).get_type() == new Team().get_type())
				{
					System.out.println("getting");
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
}
