package com.competition.contest;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.google.gson.reflect.TypeToken;


//Handles the JSON and Database Interactions with the ContestService.
public class ContestDAO implements IDAO<String, Contest>
{
	private static Type contestDbType = new TypeToken<HashMap<String, Contest>>(){}.getType();
	private HashMap<String, Contest> db;
	
	protected ContestDAO() // Created only by the Service
	{
		db = UtilityClass.DaoUtil.Init(UtilityClass.ContestsJsonPath, contestDbType);
	}
	
	@Override
	public void save(Contest entity) throws IOException
	{
		UtilityClass.DaoUtil.save(db, new Contest(entity), entity.get_id(), UtilityClass.ContestsJsonPath);
	}

	@Override
	public void delete(Contest entity) throws IOException
	{
		UtilityClass.DaoUtil.delete(db, entity.get_id(), UtilityClass.ContestsJsonPath);
	}
	
	@Override
	public HashMap<String, Contest> get_db()
	{
		return db;
	}
}