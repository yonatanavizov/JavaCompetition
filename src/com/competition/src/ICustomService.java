package com.competition.src;

import java.util.List;

// Interface for things that require a Service.
public interface ICustomService<T>
{
	List<T> get_objects();
	void set_objects(List<T> list_t);
	void print_service();
	void insert(T obj);
	boolean delete(T obj);
}