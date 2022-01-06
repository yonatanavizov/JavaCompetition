package com.competition.team;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.competition.ISearchAlgoFamily;
import com.competition.src.ICustomService;

//Takes the DAO of the Team, collects the List from it, and starts from here the LOGIC 
// we may need (Like sorts, or finding matches from here).
//This is the class people will use for the logic of the application.
public class TeamService implements ICustomService<Long, Team>
{
	private HashMap<Long, Team> teamsDB;
	private TeamDAO translator;
	//private ISearchAlgoFamily searcher;//TODO: Fix it to Static Classes

	public TeamService()
	{
		translator = TeamDAO.get_instance();
		//searcher
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
	public HashMap<Long, Team> get_objects()
	{
		return teamsDB;
	}

	@Override
	public void update_objects()
	{
		Team[] db = translator.getTeams();
		teamsDB = new HashMap<Long, Team>();
		for(int i = 0; i < db.length; i++)
		{
			teamsDB.put((long)db[i].get_tid(), new Team(db[i]));
		}
	}

	@Override
	public boolean insert(Team obj)
	{
		if(obj == null) return false;
		
		teamsDB.put((long)obj.get_tid(), new Team(obj));
		
		try
		{
			translator.save(obj);
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
		
		teamsDB.remove((long)obj.get_tid());
		
		try
		{
			translator.delete(obj);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public Team find(Long id)
	{
		return teamsDB.get(id);
	}
	
	public ArrayList<Team> find_by_summary(String phrase) // Activate the KMP
	{
		return null;
	}
}