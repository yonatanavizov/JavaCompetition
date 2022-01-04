package com.competition.src;

import java.net.URL;

import org.junit.jupiter.api.Test;

import com.competition.team.TeamService;

public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");	    
	    
	    TeamService ts = new TeamService();
		ts.initialLoad();
		ts.print_service();
		System.out.println("end");
	}
}