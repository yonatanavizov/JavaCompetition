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
	public void save(Match entity) throws IOException {
		String json = UtilityClass.ReadClass.FileToString(UtilityClass.MatchesJsonPath);
		Gson gson = new Gson();
		JsonObject jsonObj = gson.fromJson(json, JsonObject.class);
		
		if(db.containsKey(String.valueOf(entity.GetID()))) //Update a value
		{
			jsonObj.remove(String.valueOf(entity.GetID()));
			db.replace(String.valueOf(entity.GetID()), new Match(entity));
		}
		else
		{
			db.put(String.valueOf(entity.GetID()), entity);
		}
		
		jsonObj.add(String.valueOf(entity.GetID()), new Gson().toJsonTree(entity));
		UtilityClass.WriteClass.StringToFile(UtilityClass.MatchesJsonPath, jsonObj.toString());
		
	}

	@Override
	public void delete(Match entity) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public HashMap<String, Match> get_db() {
		// TODO Auto-generated method stub
		return null;
	}
}