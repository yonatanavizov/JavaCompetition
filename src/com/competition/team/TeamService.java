package com.competition.team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.competition.ISearchAlgoFamily;
import com.competition.src.ICustomService;

//Takes the DAO of the Team, collects the List from it, and starts from here the LOGIC 
// we may need (Like sorts, or finding matches from here).
//This is the class people will use for the logic of the application.
public class TeamService implements ICustomService<String, Team>
{
	private HashMap<String, Team> teamsDB;
	//private ISearchAlgoFamily searcher;//TODO: Fix it to Static Classes

	public TeamService()
	{
		update_objects();
	}
	
	@Override
	public void print_service()
	{
		for(int i = 0; i < teamsDB.size(); i++)
		{
			System.out.println(teamsDB.get(i).to_string());
		}
	}

	@Override
	public HashMap<String, Team> get_objects()
	{
		return teamsDB;
	}

	@Override
	public void update_objects()
	{
		teamsDB = TeamDAO.get_instance().get_db();
	}

	@Override
	public boolean insert(Team obj)
	{
		if(obj == null) return false;
		
		teamsDB.put(String.valueOf(obj.get_tid()), new Team(obj));
		
		try
		{
			TeamDAO.get_instance().save(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean delete(Team obj)
	{
		if(obj == null) return false;
		
		teamsDB.remove(String.valueOf(obj.get_tid()));
		
		try
		{
			TeamDAO.get_instance().delete(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Team find(String id)
	{
		return teamsDB.get(id);
	}
	
	public ArrayList<Team> find_by_summary(String phrase) // Activate the KMP
	{
		return null;
	}
}