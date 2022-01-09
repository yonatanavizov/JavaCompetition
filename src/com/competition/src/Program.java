package com.competition.src;

import java.io.IOException;
import com.competition.team.Team;
import com.competition.team.Team.Rank;
import com.competition.team.TeamDAO;


public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "our team", "basket ball",r,12,55,(float) 0.02, "A lot of summary 1976");
		try {
			TeamDAO.get_instance().save(toAdd);
			//TeamDAO.get_instance().print_service();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(TeamDAO.get_instance().get_db().get("0").to_string());
		System.out.println("end");
	}
}