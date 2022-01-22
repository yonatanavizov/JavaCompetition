package com.competition.dm;

import com.google.gson.annotations.SerializedName;

/*
 * Team Data Object:
 * This object holds the team that would be in a competition.
 */
public class Team 
{
	public enum Rank // The rank is delegating the teams to different tiers.
	{
		@SerializedName("BEGINNER") BEGINNER, 
		@SerializedName("ADVANCED") ADVANCED, 
		@SerializedName("PROFESSIONAL") PROFESSIONAL; 
	}
	private int tid;
	private int members;
	private int postion;
	private Rank rank;
	private float win_loss_ratio;
	private String game_type;
	private String name;
	private String summary; // Free text for the description for the team.
	
	public Team()
	{
		this.tid = 0;
		this.name="n/a";
		this.game_type="football";
		this.rank = Rank.BEGINNER;
		this.members=-1;
		this.postion=-1;
		this.win_loss_ratio=-1;
		this.summary = "n/a";
	}
	
	public Team(int tid, String name,String game_type,Rank rank,int members,int position,float win_loss, String summary)
	{
		this.tid = tid;
		this.name=name;
		this.game_type=game_type;
		this.rank=rank;
		this.members=members;
		this.postion=position;
		this.win_loss_ratio=win_loss;
		this.summary = summary;
	}
	
	public Team(Team other)
	{
		this.tid = other.get_id();
		this.name =other.get_name();
		this.game_type=other.get_game_type();
		this.rank=other.get_rank();
		this.members=other.get_members();
		this.postion=other.get_position();
		this.win_loss_ratio=other.get_win_loss();
		this.summary = other.get_summary();
	}
	
	public String get_name()
	{
		return this.name;
	}
	public String get_game_type()
	{
		return this.game_type;
	}
	public int get_members()
	{
		return this.members;
	}
	public int get_position()
	{
		return this.postion;
	}
	public Rank get_rank()
	{
		return this.rank;
	}
	public float get_win_loss()
	{
		return this.win_loss_ratio;
	}
	public String get_summary()
	{
		return this.summary;
	}
	public int get_id()
	{
		return this.tid;
	}
	public String toString()
	{
		String str = "id: " + tid + " [name: " + name + ", game_type: " + game_type + 
				", rank: " + rank + ", members: " + members + ", postion: " + postion + 
				", win_loss_ratio: " + win_loss_ratio + ", summary: " + summary + "]";
		return str;
	}
	//=============================================
	public void set_name(String name)
	{
		 this.name=name;
	}
	public void set_game_type(String game_type)
	{
		 this.game_type=game_type;
	}
	public void set_members(int members)
	{
		 this.members=members;
	}
	public void set_position(int position)
	{
		 this.postion=position;
	}
	public void set_rank(Rank rank)
	{
		 this.rank=rank;
	}
	public void set_win_loss(float win_loss)
	{
		 this.win_loss_ratio=win_loss;
	}
	public void set_summary(String summary)
	{
		this.summary = summary;
	}
	public void set_id(int tid)
	{
		this.tid = tid;
	}
}