package com.competition.src;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

import org.junit.jupiter.api.Test;

import com.competition.team.Team;
import com.competition.team.TeamDAO;
import com.competition.team.TeamService;
import com.competition.team.Team.Rank;
import com.google.gson.Gson;

public class Program
{
	public static void main(String[] args)
	{
		System.out.println("start");
		//int tid, String name,String game_type,Rank rank,int members,int position,float win_loss, String summary
		Rank r = Team.Rank.beginner;
		Team toAdd = new Team(0, "our team", "basket ball",r,12,55,(float) 0.02, "A lot of summary 1976");
		
		try {
			TeamDAO.get_instance().save(toAdd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("middle");
		try {
			TeamDAO.get_instance().delete(toAdd);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("end");
	}
}