package com.competition.team;

import java.util.List;

import com.competition.ISearchAlgoFamily;
import com.competition.src.IService;

//Takes the DAO of the Team, collects the List from it, and starts from here the LOGIC 
// we may need (Like sorts, or finding matches from here).
//This is the class people will use for the logic of the application.
public class TeamService implements IService<Team>
{
	private List<Team> teamsDB;
	private TeamDAO translator;
	private ISearchAlgoFamily searcher;
	//get - reads jsons into list 
	//set - sets the list to this new list, and then updates the jsons
	@Override
	public List<Team> get_objects() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set_objects(List<Team> list_t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_service() {
		// TODO Auto-generated method stub
		
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
	
	//search with KMP and all that
}