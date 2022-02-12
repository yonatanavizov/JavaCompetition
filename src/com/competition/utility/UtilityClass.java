package com.competition.utility;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.competition.dm.IDataModel;
import com.competition.dm.Team;
import com.competition.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

//Holds a lot of Static Classes that will come in handy for the project itself.
public class UtilityClass
{
	public static String TeamsJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","teams.json").toString();
	
	public static String MatchesJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","matches.json").toString();
	
	public static String ContestsJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","contests.json").toString();
	
	//A class dedicated to reading a file and returning a string of what it read. 
	public static class ReadClass
	{
		public static String FileToString(String filePath) 
	    {
	        String content = "";
	 
	        try
	        {
	            content = new String (Files.readAllBytes(Paths.get(filePath)));
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	 
	        return content;
	    }
		
		public static String ReadAllLines(BufferedReader reader) throws IOException {
		    StringBuilder content = new StringBuilder();
		    String line;
		    
		    while ((line = reader.readLine()) != null) {
		        content.append(line);
		        content.append(System.lineSeparator());
		    }

		    return content.toString();
		}
	}
	
	//A class dedicated to writing to a file, getting the string and path to where to write it.
	public static class WriteClass
	{
		public static void StringToFile(String filePath, String content) throws IOException
		{
			String str = content;
		    FileOutputStream outputStream;
			try
			{
				outputStream = new FileOutputStream(filePath);
				byte[] strToBytes = str.getBytes();
			    outputStream.write(strToBytes);
			    outputStream.close();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}   
		}
	}

	//A class to handle all matters of JSON saving and deleteing, only used by IDAO classes.
	public static class DaoUtil
	{
		// Gets a HashMap Database, an item to save, the id of the item, and path to save to.
		// Saves the item inside of the given DB, while checking to see if the item already exists in it.
		// If the item exists, it will update its value.
		public static <OBJ> void save(HashMap<String,OBJ> db, OBJ entity, int id, String path) throws IOException //Get a team and save into the JSON file
		{	
			String json = UtilityClass.ReadClass.FileToString(path);
			Gson gson = new Gson();
			JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
			
			if(db.containsKey(String.valueOf(id))) //Update a value
			{
				jsonObj.remove(String.valueOf(id));
				db.replace(String.valueOf(id), entity);
			}
			else
			{
				db.put(String.valueOf(id), entity);
			}
			
			jsonObj.add(String.valueOf(id), new Gson().toJsonTree(entity));
			UtilityClass.WriteClass.StringToFile(path, jsonObj.toString());
		}
		
		// Gets a HashMap Database, the id of the item to delete, and path to write back to.
		// Checks to see if the item exists inside of the DB
		// Then would proceed to delete the item from both JSON file and DB HashMap.
		public static <OBJ> void delete(HashMap<String,OBJ> db, int id, String path) throws IOException
		{
			if(!db.containsKey(String.valueOf(id)))
			{
				System.out.println("Team DB does not contain said Team to delete.");
				return;
			}
			String json = UtilityClass.ReadClass.FileToString(path);
			Gson gson = new Gson();
			
			JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
			jsonObj.remove(String.valueOf(id));
			UtilityClass.WriteClass.StringToFile(path, jsonObj.toString());
			db.remove(String.valueOf(id));
		}
		
		// Prints the given DB by their toString functionality.
		public static <OBJ> void print_service(HashMap<String,OBJ> db)
		{
			if(db.isEmpty())
			{
				System.out.println("The Database is empty.");
				return;
			}
			for(HashMap.Entry<String, OBJ> entry : db.entrySet())
			{
				System.out.println(entry.getValue().toString());
			}
		}
		
		// Initializes the Database HashMap from the given Json paths and files.
		public static <OBJ> HashMap<String, OBJ> Init(String path, Type type)
		{
			//TODO: Add a check if the file actually exists, if not, create it.
			String contents = UtilityClass.ReadClass.FileToString(path);
			Gson gson = new Gson();
			return gson.fromJson(contents, type);
		}
	}
	
	public static class ServerUtil
	{
		public static <T extends IDataModel> void Add(T[] objects)
		{
			if(objects == null) return;
			System.out.println("I am inside ADD for type == " + objects[0].get_type());
			switch(objects[0].get_type())
			{
				case Contest:
					break;
				case Match:
					break;
				case Team:
					TeamService ts = new TeamService();
					for(int i = 0; i < objects.length; i++)
					{
						ts.insert((Team) objects[i]);
					}
					break;
				default:
					break;
			}
		}
		
		public static <T extends IDataModel> void Delete(T[] objects)
		{
			if(objects == null) return;
			
			switch(objects[0].get_type())
			{
				case Contest:
					break;
				case Match:
					break;
				case Team:
					TeamService ts = new TeamService();
					for(int i = 0; i < objects.length; i++)
					{
						ts.delete((Team) objects[i]);
					}
					break;
				default:
					break;
			}
		}
	}
}