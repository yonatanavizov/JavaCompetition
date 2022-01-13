package com.competition.match;

import com.competition.team.Team;
import com.google.gson.annotations.SerializedName;

public class Match
{
	public enum OutCome
	{
		@SerializedName("1") Team_A_Won,
		@SerializedName("2") Team_B_Won,
		@SerializedName("3") Tie,
		@SerializedName("4") Undecided
	}
	
	private int id;
	private Team a, b;
	private OutCome outcome;

	public Match(Team a, Team b, int id)
	{
		this.a = new Team(a);
		this.b = new Team(b);
		this.id = id;
		this.outcome = OutCome.Undecided;
	}
	
	public Match(Match other)
	{
		this.id = other.id;
		this.a = new Team(other.a);
		this.b = new Team(other.b);
		this.outcome = other.outcome;//?? problem
	}
	
	public void set_outcome(OutCome outcome)
	{
		this.outcome = outcome;
	}
	
	public OutCome get_outcome()
	{
		return outcome;
	}
	
	public void set_id(int id)
	{
		this.id = id;
	}
	
	public int get_id()
	{
		return id;
	}
	
	public Team get_team_a()
	{
		return a;
	}
	
	public void set_team_a(Team a)
	{
		this.a = new Team(a);
	}
	
	public Team get_team_b()
	{
		return b;
	}
	
	public void set_team_b(Team b)
	{
		this.b = new Team(b);
	}
	
	public String toString()
	{ 
		return "Match [ ID: "+id+" | outcome: "+ outcome+ " | Team A: "+ a.get_name() + " .VS. "+" Team B: "+b.get_name()+"]"; 
	}	
}