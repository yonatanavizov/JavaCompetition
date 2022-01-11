package com.competition.src;

import java.io.IOException;
import java.util.HashMap;

public interface IDAO <ID extends java.io.Serializable,OBJ>
{
	void save(OBJ entity) throws IOException;
	void delete(OBJ entity) throws IOException;
	HashMap<ID, OBJ> get_db();
}