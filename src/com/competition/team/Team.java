package com.competition.team;


	
//The teams that will be represented as a object in a JSON file.
public class Team 
{
	public enum Rank
	{
		pro,adv,beginner;
	}
	private int members;
	private int postion;
	private Rank rank;
	private float win_loss_ratio;
	private String game_type;
	private String name;
	
	public Team()
	{
		this.name="n/a";
		this.game_type="football";
		this.rank=Rank.beginner;
		this.members=-1;
		this.postion=-1;
		this.win_loss_ratio=-1;
	}
	
	public Team(String name,String game_type,Rank rank,int members,int position,float win_loss)
	{
		this.name=name;
		this.game_type=game_type;
		this.rank=rank;
		this.members=members;
		this.postion=position;
		this.win_loss_ratio=win_loss;
	}
	public Team(Team other)
	{
		this.name=other.get_name();
		this.game_type=other.get_game_type();
		this.rank=other.get_rank();
		this.members=other.get_members();
		this.postion=other.get_position();
		this.win_loss_ratio=other.get_win_loss();
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
	
}
