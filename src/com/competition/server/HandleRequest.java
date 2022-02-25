package com.competition.server;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.Socket;
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
	
	private String readBytesRequest(DataInputStream in, ByteArrayOutputStream buffer) throws IOException
	{	
		/*
		int nRead;
		byte[] data = new byte[16384];
		while ((nRead = in.read(data, 0, data.length)) != -1)
		{
		  buffer.write(data, 0, nRead);
		}
		String contents = new String(buffer.toByteArray(), StandardCharsets.UTF_8);
		buffer.flush();
		return contents;*/
		return null;
	}
	
	private void returnAnswerToClient(DataOutputStream output, Controller controller, RequestData oldRequest) throws IOException
	{
		System.out.println(">> Going to send answer to client");
		IDataModel[] info = null;
		try {
			info = controller.get_db(oldRequest.get_objType());
		}
		catch (Exception e)
		{
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
		
		GsonBuilder gson = new GsonBuilder();
		gson.registerTypeAdapter(RequestData.class, serializer);
		
		Gson gsonRep = gson.create();  
		String answer = gsonRep.toJson(response);
		
		output.writeUTF(answer);
		output.flush();
		System.out.println("[SERVER] Sent answer --  \n"+answer);
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
	
	private void CloseConnection()
	{
		System.out.println("[SERVER] Closing Connection");
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean ParseToController(DataOutputStream output, RequestData requester)
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
			/*try {
				returnAnswerToClient(output, controller,requester);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
			ans = true;
		}
		
		return ans;
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
	
	@Override
	public void run()
	{		
		//DataInputStream in;
		//DataOutputStream output;
		try
		{
			//in = new DataInputStream(socket.getInputStream());
			//output = new DataOutputStream(socket.getOutputStream());
			//ByteArrayOutputStream buffer = new ByteArrayOutputStream();
			
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter printOutput = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));

			String str ="";
			
			while(!str.equals("stop"))
			{
				//RequestData re;
				System.out.println(">> Going to read request");
				//str = readBytesRequest(in, buffer);
				//str = in.readUTF();
				String message ="";
				/*while ((message = input.readLine()) != null) {
					str+=message;
				   }*/
				System.out.println(input.readLine());
				System.out.println("Got from Client\n"+str);
				
				printOutput.write("hello from server");
				printOutput.flush();
				//re = ParseRequest(str);
				//System.out.println(">> After parse");
				//boolean ans = ParseToController(output, re);
				//System.out.println(">> Need to return -- " + ans);
				//if(ans)
				//{
					// wait for confirm
					//System.out.println("[SERVER] sending back info");
					//output.writeUTF("Hello from server");
				//}
				
				
				
				//if(CheckConnection()) break;
				//output.flush();
			}
			input.close();
			printOutput.close();
			//in.close();
			//output.close();
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		} 
		

		CloseConnection();
	}
	
}