package com.competition.server;

import com.competition.dm.Contest;
import com.competition.dm.IDataModel;
import com.competition.dm.Match;
import com.competition.dm.Team;

public class RequestData
{
	private String action;
	private String objType;
	private int amountOfObjects;
	private IDataModel[] data;
	
	public RequestData(String action, String objType, int amountOfObjects)
	{
		this.action = action;
		this.objType = objType;
		this.amountOfObjects = amountOfObjects;
	}
	
	public String get_action()
	{
		return action;
	}
	
	public String get_objType()
	{
		return objType;
	}
	
	public IDataModel[] get_data()
	{
		return data;
	}
	
	public void set_action(String action)
	{
		this.action = action;
	}
	
	public void set_objType(String objType)
	{
		this.objType = objType;
	}
	
	public void set_data(Team[] data)
	{
		this.data = new Team[amountOfObjects];
		System.out.println("(made == got) " + this.data.length + " == " + data.length);
		for(int i = 0; i < amountOfObjects; i++)
		{
			this.data[i] = new Team(data[i]);
		}
	}
	
	public void set_data(Match[] data)
	{
		this.data = new Match[amountOfObjects];
		for(int i = 0; i < this.data.length; i++)
		{
			this.data[i] = new Match(data[i]);
		}
	}
	
	public void set_data(Contest[] data)
	{
		this.data = new Contest[amountOfObjects];
		for(int i = 0; i < this.data.length; i++)
		{
			this.data[i] = new Contest(data[i]);
		}
	}
	
	public void set_data(IDataModel[] datar)
	{
		if(datar == null) {
			System.out.println(">> No data given in set_data, exiting...");
			return;
		}
		switch(datar[0].get_type())
		{
		case Contest:
			Contest[] c = (Contest[]) datar;
			set_data(c);
			break;
		case Match:
			Match[] m = (Match[]) datar;
			set_data(m);
			break;
		case Team:
			Team[] t = (Team[]) datar;
			set_data(t);
			break;
			default:
				break;
		}
	}
	
	public String toString()
	{
		return "{ action: "+action+",\ntype: "+objType+"\ndata: " + data.length + " data[0]: " + data[0].toString();//String.join(" ; ", data) +" }";
	}
}
