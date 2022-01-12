package com.competition.match;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.competition.team.Team;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

public class MatchDAO implements IDAO<String, Match>
{
	private static Type matchDbType = new TypeToken<HashMap<String, Match>>(){}.getType();
	private HashMap<String, Match> db;
	
	protected MatchDAO()
	{
		db = new HashMap<String, Match>();
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.MatchesJsonPath);
		Gson gson = new Gson();
		
		db = gson.fromJson(contents, matchDbType);
	}
	
	@Override
	public void save(Match entity) throws IOException
	{
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.MatchesJsonPath);
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		
		if(db.containsKey(String.valueOf(entity.get_id()))) //Update a value
		{
			jsonObj.remove(String.valueOf(entity.get_id()));
			db.replace(String.valueOf(entity.get_id()), new Match(entity));
		}
		else
		{
			db.put(String.valueOf(entity.get_id()), entity);
		}
		
		jsonObj.add(String.valueOf(entity.get_id()), new Gson().toJsonTree(entity));
		UtilityClass.WriteClass.StringToFile(UtilityClass.MatchesJsonPath, jsonObj.toString());
		
	}

	@Override
	public void delete(Match entity) throws IOException
	{
		if(!db.containsKey(String.valueOf(entity.get_id())))
		{
			System.out.println("Team DB does not contain said Team to delete.");
			return;
		}
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.MatchesJsonPath);
		Gson gson = new Gson();
		
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		jsonObj.remove(String.valueOf(entity.get_id()));
		UtilityClass.WriteClass.StringToFile(UtilityClass.MatchesJsonPath, jsonObj.toString());
		db.remove(String.valueOf(entity.get_id()));
	}
	
	@Override
	public HashMap<String, Match> get_db()
	{
		return db;
	}
	
	protected void print_dao()
	{
		if(db.isEmpty())
		{
			System.out.println("The Match Database is empty.");
			return;
		}
		for(HashMap.Entry<String, Match> entry : db.entrySet())
		{
			System.out.println(entry.getValue().to_string());
		}
	}
}