package com.competition.auth;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class AuthenticationService
{
	private HashMap<String, String> users;
	private AuthenticationDao dao;
	
	public AuthenticationService(String fileLocatioAndName)
	{
		users = new HashMap<String,String>();
		dao = new AuthenticationDao(fileLocatioAndName);
		try {
			users = dao.initDb();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(String username, String password)
	{
		if(users.containsKey(username)) return;
		users.put(username, password);
		
		
		try {
			dao.addUserFromDb(users);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void removeUser(String username)
	{
		if(users.containsKey(username))
		{
			users.remove(username);
			try
			{
				dao.removeUserFromDb(users);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public boolean auth(String username, String password)
	{
		if(users.containsKey(username))
		{
			if(password.equals(users.get(username))) return true;
		}
		return false;
	}
	
	public void print()
	{
		System.out.println("printing DB");
		if(users.isEmpty())
		{
			System.out.println("The Database is empty.");
			return;
		}
		for(HashMap.Entry<String, String> entry : users.entrySet())
		{
			System.out.println(entry.getKey() + " : " +entry.getValue());
		}
	}
}
