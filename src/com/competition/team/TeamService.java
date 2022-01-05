package com.competition.team;

import java.util.ArrayList;
import java.util.List;

import com.competition.ISearchAlgoFamily;
import com.competition.KMPSearchAlgo;
import com.competition.src.ICustomService;

//Takes the DAO of the Team, collects the List from it, and starts from here the LOGIC 
// we may need (Like sorts, or finding matches from here).
//This is the class people will use for the logic of the application.
public class TeamService implements ICustomService<Team>
{
	private ArrayList<Team> teamsDB;
	private TeamDAO translator;
	private ISearchAlgoFamily searcher;
	//get - reads jsons into list 
	//set - sets the list to this new list, and then updates the jsons
	
	public TeamService()
	{
		translator = new TeamDAO();
		teamsDB = new ArrayList<Team>();
	}
	@Override
	public List<Team> get_objects() {
		teamsDB = translator.getTeams();
		return null;
	}
	@Override
	public void set_objects(List<Team> list_t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_service()
	{
		for(int i = 0; i < teamsDB.size(); i++)
		{
			System.out.println(teamsDB.get(i).to_string());
		}
	}
	@Override
	public void insert(Team obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean delete(Team obj) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void initialLoad()
	{
		teamsDB = translator.getTeams();
	}
	
	//search with KMP and all that
}