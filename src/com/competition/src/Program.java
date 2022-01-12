package com.competition.src;

import java.util.ArrayList;
import java.util.HashMap;

import com.competition.match.Match;
import com.competition.match.Match.OutCome;
import com.competition.match.MatchService;
import com.competition.team.Team;
import com.competition.team.Team.Rank;
import com.competition.team.TeamService;


public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "first team", "basket ball",r,1,11,(float) 0.11, "A lot of summaryfor ther year 1976 and all that .");
		Team toAdd2 = new Team(1, "second team", "basket ball",r,2,22,(float) 0.22, "A different amount of lot of summary 1976 and all that.");
		//TeamService.get_instance().insert(toAdd);
		//TeamService.get_instance().insert(toAdd2);
		//TeamService.get_instance().print_service();
		//ArrayList<Team> teamsFound = TeamService.get_instance().find_by_summary("1976");
		//TeamService.get_instance().delete(toAdd);
		//TeamService.get_instance().delete(toAdd2);
		//TeamService.get_instance().print_service();
		Match m1 = new Match(toAdd, toAdd2, 0);
		m1.set_outcome(OutCome.TeamAWon);
		
		MatchService.get_instance().insert(m1);
		MatchService.get_instance().print_service();
		System.out.println("end");
	}
}