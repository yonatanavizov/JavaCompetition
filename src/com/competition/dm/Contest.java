package com.competition.dm;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * Contest Data Object:
 * Holds a Minor Database of the Contest itself, having a STRING for the level of the contest (Semi Final, Finals, Prelim..)
 * 
 */
public class Contest implements IDataModel
{
	String name;
	int id;
	HashMap<String, ArrayList<Match>> matches;
	
	public Contest()
	{
		this.matches = new HashMap<String, ArrayList<Match>>();
		this.name="None";
		this.id=0;
	}
	
	public Contest(Contest other)
	{
		this.matches = new HashMap<String, ArrayList<Match>>(other.matches);
		this.name=other.name;
		this.id=other.id;
	}
	public Contest(String name,int id)
	{
		this.name=name;
		this.id=id;
		this.matches = new HashMap<String, ArrayList<Match>>();
	}
	@Override
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
	@Override
	public void set_id(int id)
	{
		this.id=id;
	}
	public HashMap<String, ArrayList<Match>> get_matches()
	{
		return matches;
	}
	public void set_matches(HashMap<String, ArrayList<Match>> other)
	{
		this.matches = new HashMap<String, ArrayList<Match>>(other);
	}
	
	public void add_match(Match mat, String level)
	{
		this.matches.get(level).add(mat);
	}
	public void remove_match(Match mat, String level)
	{
		this.matches.get(level).remove(mat);
	}
	@Override
	public String toString() // Needs to print the whole matches.
	{
		String s = "[ Contest id: " + this.id + " name: " + this.name + " Amount: " + matches.size() + " ]";
		return s;
	}

	@Override
	public ModelType get_type()
	{
		return ModelType.Contest;
	}
}