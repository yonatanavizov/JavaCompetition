package com.competition.team;

import java.util.List;

//Takes the DAO of the Team, collects the List from it, and starts from here the LOGIC 
// we may need (Like sorts, or finding matches from here).
//This is the class people will use for the logic of the application.
public class TeamService
{
	private List<Team> teamsDB;
	private TeamDAO translator;
	//get - reads jsons into list 
	//set - sets the list to this new list, and then updates the jsons
}
