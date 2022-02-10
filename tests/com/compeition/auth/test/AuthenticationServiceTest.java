package com.compeition.auth.test;
import org.junit.jupiter.api.Test;

import com.competition.auth.AuthenticationService;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;


public class AuthenticationServiceTest {
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

	@Test
	public void TestContest()
	{
		String file = "c:\\\\\\\\TEMP\\\\\\\\users.txt";
		AuthenticationService auth = new AuthenticationService(file);
		auth.addUser("Asa12f", "gggff");
		auth.addUser("Reee", "asda");
		
		auth.removeUser("Reee");
		auth.print();
		
		
		//Didint complete
		
		System.out.println(auth.auth("Asa12f", "ddd"));
	}
}
