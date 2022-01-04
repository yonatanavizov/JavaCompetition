package com.competition.src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class UtilityClass
{
	public static class ReadClass
	{
		public static String TeamsJsonPath = "/JavaCompetition/src/resources/teams.json";
		
		public ReadClass()
		{
			
		}
		public static String FileToString(String filePath) 
	    {
	        String content = "";
	 
	        try
	        {
	            content = new String (Files.readAllBytes(Paths.get(filePath)));
	        } 
	        catch (IOException e) 
	        {
	            e.printStackTrace();
	        }
	 
	        return content;
	    }
	}
	
}
