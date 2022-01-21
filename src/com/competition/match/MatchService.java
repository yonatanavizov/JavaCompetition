package com.competition.match;

import java.io.IOException;
import java.util.HashMap;

import com.competition.utility.ICustomService;
import com.competition.utility.UtilityClass;

//This class is a Singleton that will hold only 1 instance of the MatchDAO, which lets it handle a single running DB.

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
		UtilityClass.DaoUtil.print_service(dao.get_db());
	}

	@Override
	public boolean insert(Match obj)
	{
		try {
			dao.save(obj);
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Match obj)
	{
		try {
			dao.delete(obj);
			return true;
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