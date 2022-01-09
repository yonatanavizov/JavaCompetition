package com.competition.match;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.HashMap;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.competition.team.Team;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class MatchDAO implements IDAO<Long, Match>
{
	private static Type matchDbType = new TypeToken<HashMap<String, Team>>(){}.getType();
	private HashMap<String, Match> db;
	
	protected MatchDAO()
	{
		db = new HashMap<String, Match>();
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.MatchesJsonPath);
		Gson gson = new Gson();
		
		db = gson.fromJson(contents, matchDbType);
	}
	@Override
	public void save(Match entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Match entity) {
		// TODO Auto-generated method stub
		
	}
}