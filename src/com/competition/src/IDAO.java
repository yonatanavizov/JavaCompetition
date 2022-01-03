package com.competition.src;

import java.io.IOException;

public interface IDAO <ID extends java.io.Serializable,T>
{
	void save(T entity) throws IOException;
	void delete(T entity) throws IOException;
	T find(ID id) throws IOException;
}
	