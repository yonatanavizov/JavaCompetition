package com.competition.team;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
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
			db = UtilityClass.DaoUtil.Init(UtilityClass.TeamsJsonPath, teamListType);
		}
		catch(Exception e)
		{
			System.out.println("Team DB JSON issue, error recieved: " + e.getMessage());
		}
	}
	
	@Override
	public void save(Team entity) throws IOException //Get a team and save into the JSON file
	{	
		UtilityClass.DaoUtil.save(db, new Team(entity), entity.get_id(), UtilityClass.TeamsJsonPath);
	}

	@Override
	public void delete(Team entity) throws IOException // get a team, delete from DB and from JSON file
	{
		UtilityClass.DaoUtil.delete(db, entity.get_id(), UtilityClass.TeamsJsonPath);
	}
	
	@Override
	public HashMap<String, Team> get_db()
	{
		return db;
	}
}