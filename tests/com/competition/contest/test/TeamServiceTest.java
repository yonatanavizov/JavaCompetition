package com.competition.contest.test;


import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.competition.dm.Team;
import com.competition.dm.Team.Rank;
import com.competition.service.TeamService;

public class TeamServiceTest {
	HashMap<String, Team> testDb;
	HashMap<String, Team> requestedDb;
	
	@BeforeAll
	public static void beforeAll()
	{
		System.out.println("Starting test...");

	}
	
	@AfterAll
	public static void afterAll()
	{
		System.out.println("Ending test...");

	}
	
	public void Init()
	{
		TeamService ts = new TeamService();
		testDb = new HashMap<String, Team>();
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "first team", "basket ball",r,1,11,(float) 0.11, "A lot of summaryfor ther year 1976 and all that .");
		Team toAdd2 = new Team(1, "second team", "basket ball",r,2,22,(float) 0.22, "A different amount of lot of summary 1976 and all that.");
		testDb.put(String.valueOf(toAdd.get_id()), toAdd);
		testDb.put(String.valueOf(toAdd2.get_id()), toAdd2);
		requestedDb = ts.get_objects();
	}
	
	@Test
	public void tester()
	{
		Init();
		assertFalse(testDb.equals(requestedDb));
	}
	
}
