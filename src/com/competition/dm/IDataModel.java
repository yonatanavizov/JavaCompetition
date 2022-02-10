package com.competition.dm;

public interface IDataModel
{
	public enum ModelType{
		Team,Match,Contest
	}
	int get_id();
	void set_id(int id);
	String toString();
	ModelType get_type();
}
