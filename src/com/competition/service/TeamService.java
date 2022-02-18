package com.competition.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import com.competition.ISearchAlgoFamily;
import com.competition.ISearchAlgoFamily.SearchResult;
import com.competition.dao.TeamDAO;
import com.competition.dm.Team;
import com.competition.utility.UtilityClass;
import com.competition.KMPSearchAlgo;

// This class is a Singleton that will hold only 1 instance of the TeamDAO, which lets it handle a single running DB.
// This class also has StringSearch need, so it has a special Searcher.
public class TeamService implements ICustomService<Team>
{
	ISearchAlgoFamily searcher;
	private TeamDAO teamDao;
	
	
	public TeamService()
	{
		searcher =  new KMPSearchAlgo();
		teamDao = new TeamDAO();
	}
	
	
	@Override
	public void print_service()
	{
		//teamDao.print_service();
		UtilityClass.DaoUtil.print_service(teamDao.get_db());
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
		ArrayList<Team> foundResults = new ArrayList<Team>();
		for(HashMap.Entry<String, Team> entry : teamDao.get_db().entrySet())
		{
			SearchResult res = searcher.Search(phrase, entry.getValue().get_summary());
			if(res == SearchResult.NotFound)
			{
				continue;
			}
			foundResults.add(entry.getValue());
		}
		if(foundResults.isEmpty())
		{
			System.out.println("Did not find results in Team DB for -- " + phrase);
			return null;
		}
		return foundResults;
	}
}