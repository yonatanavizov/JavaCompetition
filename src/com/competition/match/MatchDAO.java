package com.competition.match;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.competition.utility.IDAO;
import com.competition.utility.UtilityClass;
import com.google.gson.reflect.TypeToken;

//Handles the JSON and Database connection to the MatchService.
public class MatchDAO implements IDAO<String, Match>
{
	private static Type matchDbType = new TypeToken<HashMap<String, Match>>(){}.getType();
	private HashMap<String, Match> db;
	
	protected MatchDAO() // Only created by the Service.
	{
		db = new HashMap<String, Match>();
		try
		{
			db = UtilityClass.DaoUtil.Init(UtilityClass.MatchesJsonPath, matchDbType);
		}
		catch(Exception e)
		{
			System.out.println("Team DB JSON issue, error recieved: " + e.getMessage());
		}
		
	}

	@Override
	public void save(Match entity) throws IOException
	{
		UtilityClass.DaoUtil.save(db, new Match(entity), entity.get_id(), UtilityClass.MatchesJsonPath);
	}

	@Override
	public void delete(Match entity) throws IOException
	{
		UtilityClass.DaoUtil.delete(db, entity.get_id(), UtilityClass.MatchesJsonPath);
	}
	
	@Override
	public HashMap<String, Match> get_db()
	{
		return db;
	}
}