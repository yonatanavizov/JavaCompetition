package com.competition.team;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;

import com.competition.src.IDAO;
import com.google.gson.Gson;

//Reads the JSON file and writes to a JSON file, of all Teams, makes them into Team List.
//Should only interact with Team and TeamDAOService
public class TeamDAO implements IDAO,Serializable
{
	private static final long serialVersionUID = -8369577276878817253L;//? PROBLEM
	FileWriter filewrite;
	FileReader fileread;

	@Override
	public void save(Object entity) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String cust = gson.toJson(entity);
		//FileWriter fileWriter = new FileWriter("Customer.json");
		//fileWriter.write(cust);
		///fileWriter.close();
		
		//Gson gson = new Gson();
		//Customer cust = gson.fromJson(new FileReader("Customer.json"), Customer.class);
		//System.out.println(cust);
		
	}

	@Override
	public void delete(Object entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object find(Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}


}
