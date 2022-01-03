package com.competition.src;

import java.util.List;

public interface IService<T>
{
	List<T> get_objects();
	void set_objects(List<T> list_t);
	void print_service();
	void insert(T obj);
	boolean delete(T obj);
}