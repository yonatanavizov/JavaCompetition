package com.competition.server;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.Socket;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

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
		buffer.flush();
		in.close();
		
		return contents;
	}
	
	private void returnAnswerToClient(Controller controller, RequestData oldRequest) throws IOException
	{
		//need to send back info.
		if(CheckConnection()) return;
		IDataModel[] info = null;
		try {
			info = controller.get_db(oldRequest.get_objType());
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RequestData response = new RequestData(oldRequest.get_action(), oldRequest.get_objType(), info.length);
		response.set_data(info);
		
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
		
		GsonBuilder gsonRes = new GsonBuilder();

		
		GsonBuilder gsonBuilder = null;
		gsonBuilder.registerTypeAdapter(RequestData.class, serializer);

		Gson gsonRep = gsonRes.create();  
		String customJSON = gsonRep.toJson(response);
		
		if(CheckConnection()) return; // true == closed socket.
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		output.writeUTF(customJSON);
		output.flush();
		output.close();
	}
	@SuppressWarnings("finally")
	private boolean CheckConnection() throws IOException
	{
		boolean closed = false;
		try {
			if(socket.getInputStream().read() == -1)//ACK send
			{
				System.out.println("Client disconnected before recieved answer");
				closed = true;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			CloseConnection();
			closed = true;
		}
		finally
		{
			return closed;
		}
	}
	private void CloseConnection() throws IOException
	{
		System.out.println("[SERVER] Closing Connection");
		socket.close();
	}
	@Override
	public void run()
	{		
		String str ="";
		while(!str.equals("stop"))
		{
			RequestData re;
			try {
				str = readBytesRequest();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			re = ParseRequest(str);
			boolean ans = ParseToController(re);
			
			if(ans)
			{
				// wait for confirm
			}
			
			try {
				if(CheckConnection())
				{
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;
			}
			
		}
		
		try {
			CloseConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean ParseToController(RequestData requester)
	{
		Controller controller = new Controller();
		boolean ans = false;
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
			try {
				returnAnswerToClient(controller,requester);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ans = true;
		}
		
		return ans;
	}
	
	private RequestData ParseRequest(String request)
	{
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
		
		return requester;
	}
}
