package com.competition.src;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilityClass
{
	
	public static class ReadClass
	{
		public static String TeamsJsonPath = Path.of(
				Paths.get(".").normalize().toAbsolutePath().toString(), 
				"_EXTRA",
				"Jsons","teams.json").toString();
		
		public static String MatchesJsonPath = Path.of(
				Paths.get(".").normalize().toAbsolutePath().toString(), 
				"_EXTRA",
				"Jsons","matches.json").toString();
		
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