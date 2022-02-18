package com.controller;

import com.competition.dm.Contest;
import com.competition.dm.IDataModel;
import com.competition.dm.Match;
import com.competition.dm.Team;
import com.competition.service.ContestService;
import com.competition.service.ICustomService;
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
	
	public <T extends ICustomService<IDataModel>> T get_service(String objType)
	{
		T returner = null;
		
		switch(objType)
		{
			case "Team":
				//returner = (TeamService) returner;
				//return returner;
				break;
		}
		return returner;
	}
}
