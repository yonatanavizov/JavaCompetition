package com.controller;

import java.util.HashMap;

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
	
	public IDataModel[] get_db(String objType) throws Exception
	{
		IDataModel[] send = null;
		
		switch(objType)
		{
			case "Team":
				HashMap<String, Team> tdb = ts.get_objects();
				send = new Team[tdb.size()];
				for(int i= 0; i < send.length; i++)
				{
					send[i] = tdb.get(String.valueOf(i));
				}
				break;
			case "Match":
				HashMap<String, Match> mdb = ms.get_objects();
				send = new Match[mdb.size()];
				for(int i= 0; i < send.length; i++)
				{
					send[i] = mdb.get(String.valueOf(i));
				}
				break;
			case "Contest":
				HashMap<String, Contest> cdb = cs.get_objects();
				System.out.println("MY cdb IS SIZE " + cdb.size());
				send = new Contest[cdb.size()];
				for(int i= 0; i < send.length; i++)
				{
					System.out.println(cdb.get(String.valueOf(i)));
					send[i] = cdb.get(String.valueOf(i));
				}
				break;
			default:
				throw new Exception("Wrong object type given for Controller");
		}
		
		return send;
	}
}
