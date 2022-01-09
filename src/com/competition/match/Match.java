package com.competition.match;

import com.competition.team.Team;

public class Match
{
	public enum OutCome
	{
		TeamAWon, TeamBWon, Tie, Undecided
	}
	private int id;
	private Team a, b;
	private OutCome outcome;
	public Match ()
	{
		
	}
	public Match(Team a, Team b, int id)
	{
		this.a = new Team(a);
		this.b = new Team(b);
		this.id = id;
	}
	public Match(Match other)
	{
		this.id=other.id;
		this.a=new Team(other.a);
		this.b=new Team(other.b);
		this.outcome=other.outcome;//?? problem
		
	}
	
	public void SetOutCome(OutCome outcome)
	{
		this.outcome = outcome;
	}
	
	public OutCome GetoutCome()
	{
		return outcome;
	}
	
	public void SetID(int id)
	{
		this.id = id;
	}
	
	public int GetID()
	{
		return id;
	}
	
	public Team GetTeamA()
	{
		return a;
	}
	
	public void SetTeamA(Team a)
	{
		this.a= new Team(a);
	}
	
	public Team GetTeamB()
	{
		return b;
	}
	
	public void SetTeamB(Team b)
	{
		this.b= new Team(b);
	}
	
	public String toString() { 
	      return "Match [ ID: "+id+", outcome: "+ outcome+ " Team A "+
	a.get_name()+" VS "+" Team B "+b.get_name()+"]"; 
	   }  
	
	
	
	
	
	
	
	
	
	
	
}