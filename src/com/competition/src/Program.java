package com.competition.src;

import java.util.ArrayList;
import java.util.HashMap;

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
		TeamService.get_instance().insert(toAdd);
		TeamService.get_instance().insert(toAdd2);
		//TeamService.get_instance().print_service();
		ArrayList<Team> teamsFound = TeamService.get_instance().find_by_summary("1976");
		TeamService.get_instance().delete(toAdd);
		TeamService.get_instance().delete(toAdd2);
		//TeamService.get_instance().print_service();
		
		System.out.println("printing findings per the needed KMP:");
		for(int i = 0; i < teamsFound.size(); i++)
		{
			System.out.println(teamsFound.get(i).to_string());
		}
		
		System.out.println("end");
		/*
		 * TODO:
		 * Re add the JAR of the KMP search. Make it static classes.
		 * Now that TEAMs are ok, copy paste the idea to Matches and Contests.
		 */
	}
}