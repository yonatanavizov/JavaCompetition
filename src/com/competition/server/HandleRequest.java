package com.competition.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;

import com.competition.dm.Contest;
import com.competition.dm.IDataModel;
import com.competition.dm.Match;
import com.competition.dm.Team;
import com.controller.Controller;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

public class HandleRequest implements Runnable
{
	Socket socket;
	
	public HandleRequest(Socket given)
	{
		socket = given;
	}
	
	private RequestData ParseRequest(String request)
	{
		
		JsonDeserializer<RequestData> deserializer = new JsonDeserializer<RequestData>()
		{  
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
		        
		        if(amountOfObjects==0) amountOfObjects = 1;
		        
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
		        System.out.println("[SERVER] Client Request Parsed -- Action: " + re.get_action());
		        return re;
		    }
		};
		
		
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(RequestData.class, deserializer);
		Gson customGson = gsonBuilder.create();  
		RequestData requester = customGson.fromJson(request, RequestData.class);  
		System.out.println(">> Parsed request");
		return requester;
	}
	
	private boolean ParseToController(RequestData clientRequest)
	{
		Controller controller = new Controller();
		boolean ans = false;
		if(clientRequest.get_action().equals("add"))
		{
			controller.Add(clientRequest.get_data());
		}
		else if(clientRequest.get_action().equals("remove"))
		{
			controller.Delete(clientRequest.get_data());
		}
		else if(clientRequest.get_action().equals("get"))
		{
			ans = true;
		}
		return ans;
	}
	
	private String ReturnAnswerToClient(String type) throws IOException
	{
		Controller controller = new Controller();
		System.out.println(">> Going to send answer to client");
		IDataModel[] info = null;
		try {
			info = controller.get_db(type);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("Returning to Client size " + info.length);
		for(int i = 0; i < info.length; i++)
		{
			System.out.println(info[i]);
		}
		
		RequestData response = new RequestData("add", type, info.length);
		
		
		response.set_data(info, type);
		
		JsonSerializer<RequestData> serializer = new JsonSerializer<RequestData>() {  
		    @Override
		    public JsonElement serialize(RequestData src, Type typeOfSrc, JsonSerializationContext context) {
		        JsonObject req = new JsonObject();

		        req.addProperty("action", src.get_action());
		        req.addProperty("objType", src.get_objType());
		        req.addProperty("amountOfObjects", src.get_data().length);
		        JsonArray dataArr = new JsonArray();
		        
		        IDataModel[] data = src.get_data();
		        if (data != null)
		        {
		        	for(int i = 0; i < data.length; i++)
		        	{
		        		dataArr.add(context.serialize(data[i]));
		        	}
		        }
		        req.add("data", dataArr);

		        return req;
		    }
		};
		
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(RequestData.class, serializer);
		Gson gsonRep = gson.create();  
		String answer = gsonRep.toJson(response);
		
		return answer;
	}
	
	@Override
	public void run()
	{
		System.out.println("[SERVER]\nFakeHandleRequest -- Awake");
		String str = "";
		
		while(!str.equals("stop"))
		{
			try
			{
				System.out.println(">> entered while");
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter output = new PrintWriter(socket.getOutputStream());
				
				System.out.println(">> after some while");

				str = input.readLine();
				System.out.println("<< got from client\n"+str);
				RequestData clientRequest = ParseRequest(str);
				boolean shouldSendToClient = ParseToController(clientRequest);
				
				String toSendtoClient = "bye";
				
				if(shouldSendToClient)
				{
					toSendtoClient = ReturnAnswerToClient(clientRequest.get_objType());
				}
				
				System.out.println(">> Going to send to client");
				output.write(toSendtoClient);
				output.flush();
				
				System.out.println("FakeRequest is closing...");
				output.close();
				input.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			break;
		}
		
		
		try
		{
			socket.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}