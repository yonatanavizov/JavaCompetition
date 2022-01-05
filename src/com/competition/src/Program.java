package com.competition.src;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import com.competition.team.Team;
import com.competition.team.TeamDAO;
import com.competition.team.TeamService;

public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
	    TeamDAO ts = new TeamDAO();
	    Team[] teams = ts.getTeams().get("teams");
	    if(teams.length <= 0) System.out.println("empty");
	    String s1 = teams[0].to_string();
	    String s2 = teams[1].to_string();
	    System.out.println(s1);
	    System.out.println(s2);
		//ts.initialLoad();
		//ts.print_service();
		System.out.println("end");
	}
}