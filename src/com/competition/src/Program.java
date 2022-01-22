/* 
 * Project Summary:
 * The project is a Competition handle for various sports.
 * It contains the functionality of added teams, matches, and contests.
 * At the end, you can also display the contests.
 * You can also find specific teams by their Summary Description via a word of your choice. 
 * 
 * Project Created by: 
 * Yonatan Avizov ID: 318432101
 * Asaf Dangoor ID: 313307340
 */

package com.competition.src;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import com.competition.contest.Contest;
import com.competition.contest.ContestService;
import com.competition.match.Match;
import com.competition.match.Match.OutCome;
import com.competition.match.MatchService;
import com.competition.server.Server;
import com.competition.team.Team;
import com.competition.team.Team.Rank;
import com.competition.team.TeamService;

// The starting point of the application
// Currently used for Tests of the DB and Json Files.
public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
		/*
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "first team", "basket ball",r,1,11,(float) 0.11, "A lot of summaryfor ther year 1976 and all that .");
		Team toAdd2 = new Team(1, "second team", "basket ball",r,2,22,(float) 0.22, "A different amount of lot of summary 1976 and all that.");
		TeamService ts = new TeamService();
		
		ts.insert(toAdd);
		ts.insert(toAdd2);
		// TeamService.get_instance().print_service();
		// ArrayList<Team> teamsFound = TeamService.get_instance().find_by_summary("1976");
		// TeamService.get_instance().delete(toAdd);
		// TeamService.get_instance().delete(toAdd2);
		// TeamService.get_instance().print_service();
		
		
		Match m1 = new Match(toAdd, toAdd2, 0);
		m1.set_outcome(OutCome.Team_A_Won);
		MatchService ms = new MatchService();
		//System.out.println(m1.to_string());
		ms.insert(m1);
		ms.print_service();
		
		
		Contest c1 = new Contest();
		HashMap<String, ArrayList<Match>> toAddToCon = new HashMap<String, ArrayList<Match>>();
		toAddToCon.put("Start", new ArrayList<Match>());
		
		c1.set_matches(toAddToCon);
		c1.add_match(m1, "Start");
		ContestService cs = new ContestService();
		cs.insert(c1);
		cs.print_service();
		*/
		
		try {
			Server serv = new Server(9119);
			Thread t1 = new Thread(serv);
			
			t1.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("end");
	}
}