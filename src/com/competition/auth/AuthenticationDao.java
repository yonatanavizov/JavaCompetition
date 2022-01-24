package com.competition.auth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.competition.ISearchAlgoFamily;

public class AuthenticationDao
{
	String fileName;
	ISearchAlgoFamily searcher;
	String sep = " : ";
	
	public AuthenticationDao(String fileName)
	{
		this.fileName = fileName;
		try
		{
			Init();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//make a file
	private void Init() throws IOException
	{
		File db = new File(fileName);
		db.createNewFile();
	}
	
	//write hashmap to text file
	public void addUserFromDb(HashMap<String,String> db) throws IOException
	{
		editDb(db);
	}
	
	//writes to txt from hashmap
	public void removeUserFromDb(HashMap<String,String> db) throws IOException
	{
		editDb(db);
	}
	
	
	private void editDb(HashMap<String, String> db) throws IOException
	{
		BufferedWriter bf = new BufferedWriter(new FileWriter(fileName));
        
        for (Map.Entry<String, String> entry : db.entrySet())
        {
            bf.write(entry.getKey() + sep + entry.getValue());
            bf.newLine();
        }

        bf.flush();
        bf.close();
	}
	
	//write text file to hasmap
	public HashMap<String, String> initDb() throws FileNotFoundException, IOException
	{
        File file = new File(fileName);
        HashMap<String, String> map = new HashMap<String, String>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String line = null;

        // read file line by line
        while ((line = br.readLine()) != null)
        {
            String[] parts = line.split(sep);

            String name = parts[0].trim();
            String pass = parts[1].trim();

            if (!name.equals("") && !pass.equals(""))
            {
            	map.put(name, pass);
            }
                
        }
        br.close();
        return map;
	}
}
