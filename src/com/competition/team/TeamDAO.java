package com.competition.team;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO<String, Team>
{
	private HashMap<String, Team> db; // SHould be private HashMap<Long, Team> db2;
	private static Type teamListType = new TypeToken<HashMap<String, Team>>(){}.getType();
	
	protected TeamDAO() // Singleton based private Constructor
	{
		db = new HashMap<String, Team>();
		try
		{
			init();
		}
		catch(Exception e)
		{
			System.out.println("Team DB JSON issue, error recieved: " + e.getMessage());
		}
	}
	
	private void init() throws IOException
	{
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.TeamsJsonPath);
		Gson gson = new Gson();
		
		db = gson.fromJson(contents, teamListType);
	}
	@Override
	public void save(Team entity) throws IOException //Get a team and save into the JSON file
	{	
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.TeamsJsonPath);
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		
		if(db.containsKey(String.valueOf(entity.get_tid()))) //Update a value
		{
			jsonObj.remove(String.valueOf(entity.get_tid()));
			db.replace(String.valueOf(entity.get_tid()), new Team(entity));
		}
		else
		{
			db.put(String.valueOf(entity.get_tid()), entity);
		}
		
		jsonObj.add(String.valueOf(entity.get_tid()), new Gson().toJsonTree(entity));
		UtilityClass.WriteClass.StringToFile(UtilityClass.TeamsJsonPath, jsonObj.toString());
		
	}

	@Override
	public void delete(Team entity) throws IOException // get a team, delete from DB and from JSON file
	{
		if(!db.containsKey(String.valueOf(entity.get_tid())))
		{
			System.out.println("Team DB does not contain said Team to delete.");
			return;
		}
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.TeamsJsonPath);
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		jsonObj.remove(String.valueOf(entity.get_tid()));
		//System.out.println(jsonObj.toString());
		UtilityClass.WriteClass.StringToFile(UtilityClass.TeamsJsonPath, jsonObj.toString());
		db.remove(String.valueOf(entity.get_tid()));
	}
	
	public HashMap<String, Team> get_db()
	{
		return db;
	}
	
	public void print_service()
	{
		if(db.isEmpty())
		{
			System.out.println("The Team Database is empty.");
			return;
		}
		for(HashMap.Entry<String, Team> entry : db.entrySet())
		{
			System.out.println(entry.getValue().to_string());
		}
	}
}