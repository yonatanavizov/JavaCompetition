package com.competition.src;

import com.competition.team.Team;
import com.competition.team.Team.Rank;
import com.competition.team.TeamService;


public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "our team", "basket ball",r,12,55,(float) 0.02, "A lot of summary 1976");
		
		TeamService.get_instance().insert(toAdd);
		TeamService.get_instance().print_service();
		TeamService.get_instance().delete(toAdd);
		TeamService.get_instance().print_service();
		System.out.println("end");
	}
}