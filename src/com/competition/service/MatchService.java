package com.competition.service;

import java.io.IOException;
import java.util.HashMap;

import com.competition.dao.MatchDAO;
import com.competition.dm.Match;
import com.competition.utility.UtilityClass;

//This class is a Singleton that will hold only 1 instance of the MatchDAO, which lets it handle a single running DB.

public class MatchService implements ICustomService<String, Match>
{
	private MatchDAO dao;
	
	public MatchService()
	{
		dao = new MatchDAO();
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