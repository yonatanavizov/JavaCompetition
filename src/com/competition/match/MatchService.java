package com.competition.match;

import java.io.IOException;
import java.util.HashMap;

import com.competition.src.ICustomService;

// Something here too. 
public class MatchService implements ICustomService<String, Match>
{
	private static MatchService instace;
	private MatchDAO dao;
	
	private MatchService()
	{
		dao = new MatchDAO();
	}
	
	public static MatchService get_instance()
	{
		if(instace == null)
		{
			instace = new MatchService();
		}
		return instace;
	}
	
	@Override
	public HashMap<String, Match> get_objects()
	{
		return dao.get_db();
	}


	@Override
	public void print_service()
	{
		dao.print_dao();
	}

	@Override
	public boolean insert(Match obj)
	{
		try {
			dao.save(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean delete(Match obj)
	{
		try {
			dao.delete(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Match find(String id)
	{
		return dao.get_db().get(id);
	}

}