package com.competition.contest;

import java.util.HashMap;
import java.util.List;

import com.competition.match.Match;

public class Contest
{
	String name;
	int id;
	HashMap<Integer, List<Match>> matches;
	
	public Contest()
	{
		
	}
	
	public Contest(Contest other)
	{
		this.matches = new HashMap<Integer, List<Match>>(other.matches);
		this.name=other.name;
		this.id=other.id;
	}
	public Contest(String name,int id)
	{
		this.name=name;
		this.id=id;
		this.matches = new HashMap<Integer, List<Match>>();
	}
	public int GetID()
	{
		return id;
	}
	public String GetName()
	{
		return name;
	}
	public void SetName(String name)
	{
		this.name=name;
	}
	public void SetID(int id)
	{
		this.id=id;
	}
	public HashMap<Integer, List<Match>> GetMatches()
	{
		return matches;
	}
	public void SetMatches(HashMap<Integer, List<Match>> other)
	{
		this.matches = new HashMap<Integer, List<Match>>(other);
	}
	
	
	
	
}