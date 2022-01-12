package com.competition.src;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class UtilityClass
{
	public static String TeamsJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","teams.json").toString();
	
	public static String MatchesJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","matches.json").toString();
	
	public static String CompetitonsJsonPath = Path.of(
			Paths.get(".").normalize().toAbsolutePath().toString(), 
			"_EXTRA",
			"Jsons","competitons.json").toString();
	
	public static class ReadClass
	{
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
	
	public static class WriteClass
	{
		public static void StringToFile(String filePath, String content) throws IOException
		{
			String str = content;
		    FileOutputStream outputStream;
			try
			{
				outputStream = new FileOutputStream(filePath);
				byte[] strToBytes = str.getBytes();
			    outputStream.write(strToBytes);
			    outputStream.close();
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}   
		}
	}
}