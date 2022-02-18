package com.competition.service;

import java.util.HashMap;

import com.competition.dm.IDataModel;

// Interface for things that require a Service.
public interface ICustomService<OBJ extends IDataModel>
{
	HashMap<String, OBJ> get_objects(); // Whoever needs gets the FIXED DB.
	void print_service(); // Print DB
	boolean insert(OBJ obj); // Add to DB, Call insert from IDAO
	boolean delete(OBJ obj); // Remove from DB, Call remove from IDAO
	OBJ find(String id); // Find Object from DB
}