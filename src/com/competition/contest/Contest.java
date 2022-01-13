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
		this.matches = new HashMap<Integer, List<Match>>();
		this.name="None";
		this.id=0;
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
	public int get_id()
	{
		return id;
	}
	public String get_name()
	{
		return name;
	}
	public void set_name(String name)
	{
		this.name=name;
	}
	public void set_id(int id)
	{
		this.id=id;
	}
	public HashMap<Integer, List<Match>> get_matches()
	{
		return matches;
	}
	public void set_matches(HashMap<Integer, List<Match>> other)
	{
		this.matches = new HashMap<Integer, List<Match>>(other);
	}
	
	public String toString() // Needs to print the whole matches.
	{
		String s = "[ Contest id: " + this.id + " name: " + this.name + " ]";
		return s;
	}
}