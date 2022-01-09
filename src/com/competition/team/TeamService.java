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
	private static TeamService instance;
	private TeamDAO teamDao;
	
	
	private TeamService()
	{
		teamDao = new TeamDAO();
	}
	
	public static TeamService get_instance()
	{
        if (TeamService.instance == null)
        {
        	TeamService.instance = new TeamService();
        }
        return TeamService.instance;
	}
	
	@Override
	public void print_service()
	{
		teamDao.print_service();
	}

	@Override
	public HashMap<String, Team> get_objects()
	{
		return teamDao.get_db();
	}


	@Override
	public boolean insert(Team obj)
	{
		if(obj == null) return false;
		
		try
		{
			teamDao.save(obj);
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
				
		try
		{
			teamDao.delete(obj);
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
		try {
			Team t = teamDao.get_db().get(id);
			return t;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Team> find_by_summary(String phrase) // Activate the KMP
	{
		return null;
	}
}