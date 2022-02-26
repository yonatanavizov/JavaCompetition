package com.competition.dm;

import com.google.gson.annotations.SerializedName;


/*
 * Match Data Object:
 * This will hold a match that went between two teams, the outcome of the match as well.
 */
public class Match implements IDataModel
{
	public enum OutCome // The outcome of the match to adjust the team's win lose ratio.
	{
		@SerializedName("Team_A_Won") Team_A_Won,
		@SerializedName("Team_B_Won") Team_B_Won,
		@SerializedName("Tie") Tie,
		@SerializedName("Undecided") Undecided
	}
	
	private int id;
	private Team a, b;
	private OutCome outcome;
	
	public Match()
	{
		a = new Team();
		b = new Team();
		id = -1;
		this.outcome = OutCome.Undecided;
	}
	
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
	@Override
	public void set_id(int id)
	{
		this.id = id;
	}
	@Override
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
	@Override
	public String toString()
	{ 
		return "Match [ ID: "+id+" | outcome: "+ outcome+ " | Team A: "+ a.get_name() + " .VS. "+" Team B: "+b.get_name()+"]"; 
	}

	@Override
	public ModelType get_type()
	{
		return ModelType.Match;
	}	
}