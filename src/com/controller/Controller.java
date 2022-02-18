package com.controller;

import java.util.HashMap;
import java.util.List;

import com.competition.dm.Contest;
import com.competition.dm.IDataModel;
import com.competition.dm.Match;
import com.competition.dm.Team;
import com.competition.service.ContestService;
import com.competition.service.MatchService;
import com.competition.service.TeamService;

public class Controller
{
	TeamService ts;
	MatchService ms;
	ContestService cs;
	public Controller()
	{
		ts = new TeamService();
		ms = new MatchService();
		cs = new ContestService();
	}
	
	public <T extends IDataModel> void Add(T[] objects)
	{
		if(objects == null) return;
		
		switch(objects[0].get_type())
		{
			case Contest:
				for(int i = 0; i < objects.length; i++)
				{
					cs.insert((Contest) objects[i]);
				}
				break;
			case Match:
				for(int i = 0; i < objects.length; i++)
				{
					ms.insert((Match) objects[i]);
				}
				break;
			case Team:
				for(int i = 0; i < objects.length; i++)
				{
					ts.insert((Team) objects[i]);
				}
				break;
			default:
				break;
		}
	}
	
	public <T extends IDataModel> void Delete(T[] objects)
	{
		if(objects == null) return;
		
		switch(objects[0].get_type())
		{
			case Contest:
				for(int i = 0; i < objects.length; i++)
				{
					cs.delete((Contest) objects[i]);
				}
				break;
			case Match:
				for(int i = 0; i < objects.length; i++)
				{
					ms.delete((Match) objects[i]);
				}
				break;
			case Team:
				for(int i = 0; i < objects.length; i++)
				{
					ts.delete((Team) objects[i]);
				}
				break;
			default:
				break;
		}
	}
	
	public List<IDataModel> get_db(String objType)
	{
		//return cs.get_objects();
		// ask what type we are -- construct a list from hashmap, return it.
		
		//assume we are Team
		//HashMap<String, IDataModel> db = null;
		List<IDataModel> send = null;
		if(objType.equals("Team"))
		{
			HashMap<String, Team> db = ts.get_objects();
			send.add(db.get("1"));
		}
		return send;
	}
}
