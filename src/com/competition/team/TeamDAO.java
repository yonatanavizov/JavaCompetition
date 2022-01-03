package com.competition.team;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;

import com.competition.src.IDAO;
import com.google.gson.Gson;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO<Long, Team>
{
	FileWriter filewrite;
	FileReader fileread;

	@Override
	public void save(Team entity) throws IOException
	{
		Gson gson = new Gson();
		String team = gson.toJson(entity);
		filewrite = new FileWriter("teams.json");
		filewrite.write(team);
		filewrite.close();	
	}

	@Override
	public void delete(Team entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Team find(Long id) {
		//Gson gson = new Gson();
		//Customer cust = gson.fromJson(new FileReader("Customer.json"), Customer.class);
		//System.out.println(cust);
		return null;
	}
}