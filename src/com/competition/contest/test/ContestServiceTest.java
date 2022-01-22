package com.competition.contest.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import com.competition.dm.Contest;
import com.competition.dm.Match;
import com.competition.dm.Team;
import com.competition.dm.Match.OutCome;
import com.competition.dm.Team.Rank;
import com.competition.service.ContestService;

public class ContestServiceTest
{
	private HashMap<String, Contest> testDb;
	private HashMap<String, Contest> requestedDb;
	
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
		ContestService cs = new ContestService();
		testDb = new HashMap<String, Contest>();
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "first team", "basket ball",r,1,11,(float) 0.11, "A lot of summaryfor ther year 1976 and all that .");
		Team toAdd2 = new Team(1, "second team", "basket ball",r,2,22,(float) 0.22, "A different amount of lot of summary 1976 and all that.");
		Match m1 = new Match(toAdd, toAdd2, 0);
		m1.set_outcome(OutCome.Team_A_Won);
		Contest c1 = new Contest();
		HashMap<String, ArrayList<Match>> toAddToCon = new HashMap<String, ArrayList<Match>>();
		toAddToCon.put("Start", new ArrayList<Match>());
		
		testDb.put("0", c1);
		requestedDb = cs.get_objects();
	}
	
	@Test
	public void TestContest()
	{
		Init();
		//Assert - Our DB vs the JSON db.
		assertFalse(testDb.equals(requestedDb));
	}
}