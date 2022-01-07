package com.competition.team;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO<String, Team>
{
	private HashMap<String, Team> db; // SHould be private HashMap<Long, Team> db2;
	private static Type teamListType = new TypeToken<HashMap<String, Team>>(){}.getType();
	private static TeamDAO instance;
	
	private TeamDAO() // Singleton based private Constructor
	{
		db = new HashMap<String, Team>();
		try
		{
			init();
		}
		catch(Exception e)
		{
			System.out.println("Team DB JSON empty, error recieved: " + e.getMessage());
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
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.ReadClass.TeamsJsonPath);
		Gson gson = new Gson();
		
		db = gson.fromJson(contents, teamListType);
	}
	@Override
	public void save(Team entity) throws IOException //Get a team and save into the JSON file
	{
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.ReadClass.TeamsJsonPath);
		
		Gson gson = new Gson();
		db = gson.fromJson(contents, teamListType);
	}

	@Override
	public void delete(Team entity) throws IOException // get a team, delete from DB and from JSON file
	{
		
	}
	
	public HashMap<String, Team> get_db()
	{
		return db;
	}
}