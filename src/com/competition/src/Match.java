package com.competition.src;

import com.competition.src.Team.Rank;

public class Match
{
	public enum OutCome
	{
		TeamAWon, TeamBWon, Tie, Undecided
	}
	private int id;
	private Team a, b;
	private OutCome outcome;
	
	public Match(Team a, Team b, int id)
	{
		this.a = new Team(a);
		this.b = new Team(b);
		this.id = id;
	}
	
	public void SetOutCome(OutCome outcome)
	{
		this.outcome = outcome;
	}
}