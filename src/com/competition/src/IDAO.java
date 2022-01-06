package com.competition.src;

import java.io.IOException;

public interface IDAO <ID extends java.io.Serializable,OBJ>
{
	void save(OBJ entity) throws IOException;
	void delete(OBJ entity) throws IOException;
}