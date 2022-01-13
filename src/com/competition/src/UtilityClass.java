package com.competition.src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import com.competition.team.Team;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
	
	public static String CompetitonsJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","competitons.json").toString();
	
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
	}
	
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

	public static class DaoUtil
	{
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
		
		public static <OBJ> void print_service(HashMap<String,OBJ> db)
		{
			if(db.isEmpty())
			{
				System.out.println("The Team Database is empty.");
				return;
			}
			for(HashMap.Entry<String, OBJ> entry : db.entrySet())
			{
				System.out.println(entry.getValue().toString());
			}
		}
		
		public static <OBJ> HashMap<String, OBJ> Init(String path, Type type)
		{
			String contents = UtilityClass.ReadClass.FileToString(path);
			Gson gson = new Gson();
			return gson.fromJson(contents, type);
		}
	}
}