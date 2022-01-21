package com.competition.utility;

import java.util.HashMap;

// Interface for things that require a Service.
public interface ICustomService<ID, OBJ>
{
	HashMap<ID, OBJ> get_objects(); // Whoever needs gets the FIXED DB.
	void print_service(); // Print DB
	boolean insert(OBJ obj); // Add to DB, Call insert from IDAO
	boolean delete(OBJ obj); // Remove from DB, Call remove from IDAO
	OBJ find(ID id); // Find Object from DB
}