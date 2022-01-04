package com.competition.team;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.competition.src.IDAO;
import com.competition.src.UtilityClass;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO<Long, Team>
{
	ArrayList<Team> teamArray;
	public static Type teamListType = new TypeToken<ArrayList<Team>>(){}.getType();
	
	public TeamDAO()
	{
		teamArray = new ArrayList<Team>();
	}
	
	@Override
	public void save(Team entity) throws IOException
	{
		String contents = UtilityClass.ReadClass.FileToString(UtilityClass.ReadClass.TeamsJsonPath);
		Gson gson = new Gson();
		teamArray = gson.fromJson(contents, teamListType);
	}

	@Override
	public void delete(Team entity) throws IOException
	{
		
	}

	@Override
	public Team find(Long id)
	{
		return null;
	}
	public ArrayList<Team> getTeams()
	{
		return teamArray;
	}
}