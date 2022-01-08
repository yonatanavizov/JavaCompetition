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
	public static Type teamListType = new TypeToken<HashMap<String, Team>>(){}.getType();
	private static TeamDAO instance;
	
	private TeamDAO() // Singleton based private Constructor
	{
		System.out.println("inside TeamDAO CTOR");
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
	
	public static TeamDAO get_instance()
	{
        if (TeamDAO.instance == null)
        {
        	TeamDAO.instance = new TeamDAO();
        }
        return TeamDAO.instance;
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
		if(db.containsKey(String.valueOf(entity.get_tid()))) // Update Functionality.
		{
			System.out.println("Need to update the ENTITY - " + entity.get_name());
			return;
		}
		
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.TeamsJsonPath);
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		jsonObj.add(String.valueOf(entity.get_tid()), new Gson().toJsonTree(entity));
		System.out.println(jsonObj.toString());
		UtilityClass.WriteClass.StringToFile(UtilityClass.TeamsJsonPath, jsonObj.toString());
	}

	@Override
	public void delete(Team entity) throws IOException // get a team, delete from DB and from JSON file
	{
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.TeamsJsonPath);
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		jsonObj.remove(String.valueOf(entity.get_tid()));
		System.out.println(jsonObj.toString());
		UtilityClass.WriteClass.StringToFile(UtilityClass.TeamsJsonPath, jsonObj.toString());
	}
	
	public HashMap<String, Team> get_db()
	{
		return db;
	}
	
	public void print_service()
	{
		for(int i = 0; i < db.size(); i++)
		{
			System.out.println(db.get(String.valueOf(i)).to_string());
		}
	}
}