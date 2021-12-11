package com.competition.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.competition.ISearchAlgoFamily;
import com.competition.KMPSearchAlgo;
import com.competition.NaiveSearchAlgo;
import com.competition.RabinKarpAlgo;
import com.competition.ISearchAlgoFamily.SearchResult;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


class SearchAlgoFamilyTest {
	String s = "ASAF";
	String b = "somethingASAF12378ASAF1827hellothere";
	
	ISearchAlgoFamily Searcher;

	
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		System.out.println("Started Test");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
		System.out.println("Ended Test");
	}

	@Test
	void KMP()
	{
		
		Searcher= new KMPSearchAlgo();	
	    assertEquals(SearchResult.FoundMany, Searcher.Search(s, b));
	}
	@Test
	void NavieTest()
	{
		Searcher= new NaiveSearchAlgo ();
	    assertEquals(SearchResult.FoundMany, Searcher.Search(s, b));
	}
	@Test
	void RabinKarpAlgo()
	{
		Searcher= new RabinKarpAlgo ();
	    assertEquals(SearchResult.FoundMany, Searcher.Search(s, b));
	}

}
