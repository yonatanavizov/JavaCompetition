package com.competition.contest;

import java.util.List;
import com.competition.src.*;

public class ContestService implements ICustomService<Contest>
{
	
	List<Contest> contestsDB;
	ContestDAO translator;
	
	@Override
	public List<Contest> get_objects() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void set_objects(List<Contest> list_t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_service() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void insert(Contest obj) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean delete(Contest obj) {
		// TODO Auto-generated method stub
		return false;
	}
}
