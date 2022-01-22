package com.competition.service;

import java.io.IOException;
import java.util.HashMap;

import com.competition.dao.ContestDAO;
import com.competition.dm.Contest;
import com.competition.utility.UtilityClass;

//This class is a Singleton that will hold only 1 instance of the ContestDAO, which lets it handle a single running DB.
public class ContestService implements ICustomService<String, Contest>
{
	ContestDAO dao;
	
	public ContestService()
	{
		dao = new ContestDAO();
	}
	
	@Override
	public HashMap<String, Contest> get_objects()
	{
		return dao.get_db();
	}

	
	@Override
	public void print_service()
	{
		UtilityClass.DaoUtil.print_service(dao.get_db());
	}
	@Override
	public boolean insert(Contest obj)
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
	public boolean delete(Contest obj)
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
	public Contest find(String id)
	{
		return dao.get_db().get(id);
	}

	
}
