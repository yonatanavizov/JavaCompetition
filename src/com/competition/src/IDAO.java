package com.competition.src;

public interface IDAO <ID extends java.io.Serializable,T>
{
	void save(T entity);
	void delete(T entity);
	T find(ID id);
}
	