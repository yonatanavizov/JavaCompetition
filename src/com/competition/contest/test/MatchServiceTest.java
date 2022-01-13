package com.competition.contest.test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.competition.match.Match;
import com.competition.match.MatchService;
import com.competition.match.Match.OutCome;
import com.competition.team.Team;
import com.competition.team.Team.Rank;

public class MatchServiceTest
{
	HashMap<String, Match> testDb;
	HashMap<String, Match> requestedDb;
	
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
		testDb = new HashMap<String, Match>();
		Rank r = Rank.ADVANCED;
		Team toAdd = new Team(0, "first team", "basket ball",r,1,11,(float) 0.11, "A lot of summaryfor ther year 1976 and all that .");
		Team toAdd2 = new Team(1, "second team", "basket ball",r,2,22,(float) 0.22, "A different amount of lot of summary 1976 and all that.");
		Match m1 = new Match(toAdd, toAdd2, 0);
		m1.set_outcome(OutCome.Team_A_Won);
		
		requestedDb = MatchService.get_instance().get_objects();
	}
	
	@Test
	public void tester()
	{
		Init();
		assertFalse(testDb.equals(requestedDb));
	}
	
}