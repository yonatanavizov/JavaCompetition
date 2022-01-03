package com.competition.team;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.competition.src.IDAO;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO<Long, Team>
{
	FileWriter filewrite;
	FileReader fileread;
	String path ="teams.json";
	
	@Override
	public void save(Team entity) throws IOException
	{
		Gson gson = new Gson();
		String team = gson.toJson(entity);
		
		filewrite = new FileWriter(path);
		filewrite.write(team);
		filewrite.close();	
	}

	@Override
	public void delete(Team entity) throws IOException {
		// TODO Auto-generated method stub
		//getAsJsonObject
		fileread = new FileReader("teams.json");
		String jsonFile = new String(Files.readAllBytes(Paths.get(path)));
		
		// TODO: Make the jsonFile String convert to JSONObject, delete by .remove, then WRITE the object back into the teams.json file.
		JsonObject jo = new JsonObject();
		jo.remove(entity.get_name());
	}

	@Override
	public Team find(Long id) {
		//Gson gson = new Gson();
		//Customer cust = gson.fromJson(new FileReader("Customer.json"), Customer.class);
		//System.out.println(cust);
		return null;
	}
}