package com.competition.server;

public class RequestData
{
	private String action;
	private String objType;
	private String[] data;
	
	public RequestData(String action, String objType, String[] data)
	{
		this.action = action;
		this.objType = objType;
		this.data = data;
	}
	
	public String get_action()
	{
		return action;
	}
	
	public String get_objType()
	{
		return objType;
	}
	
	public String[] get_data()
	{
		return data;
	}
	
	public void set_action(String action)
	{
		this.action = action;
	}
	
	public void set_objType(String objType)
	{
		this.objType = objType;
	}
	
	public void set_data(String[] data)
	{
		this.data = new String[data.length];
		for(int i = 0; i < data.length; i++)
		{
			this.data[i] = data[i];
		}
	}
	
	public String toString()
	{
		return "{ action: "+action+",\ntype: "+objType+"\ndata: " + String.join(" ; ",data)+" }";
	}
}
