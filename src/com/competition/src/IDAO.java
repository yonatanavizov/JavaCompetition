package com.competition.src;

public interface IDAO
{
	//takes object turns to json
	void SetJson(Object object);
	//Takes json, turns to object
	Object SetObject(String json);
}