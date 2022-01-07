package com.competition.contest;

import java.util.HashMap;
import java.util.List;
import com.competition.src.*;

public class ContestService implements ICustomService<Integer, Contest>
{
	
	List<Contest> contestsDB;
	ContestDAO translator;
	@Override
	public HashMap<Integer, Contest> get_objects() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update_objects() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void print_service() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean insert(Contest obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean delete(Contest obj) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public Contest find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
