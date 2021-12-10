package com.competition;

import java.util.ArrayList;
import java.util.List;

public class NaiveSearchAlgo implements ISearchAlgoFamily
{
	private List<Integer> results;
	private String big, small;
	
	public NaiveSearchAlgo()
	{
		results = new ArrayList<Integer>();
	}
	
	
	@Override
	public SearchResult Search(String a1, String a2)
	{
		if(a1.length() > a2.length())
		{
			big = a1;
			small = a2;
		}
		else
		{
			big = a2;
			small = a1;
		}
		
		int s = 0, b = 0, old = 0;
		boolean foundAMatch = true;
		//1223ASjhsgdASAFkjashdka
		//ASAF
		
		while(old < big.length())
		{
			b = old;
			s = 0;
			foundAMatch = true;
			while(s < small.length())
			{
				if(big.charAt(b) == small.charAt(s))
				{
					s++;
					b++;
				}
				else
				{
					foundAMatch = false;
					break;
				}
			}
			
			if(foundAMatch)
			{
				results.add(old);
			}
			old++;
		}
		
		return FindResult();
	}

	@Override
	public List<Integer> GetFoundResults()
	{
		return results;
	}
	
    private SearchResult FindResult()
    {
        if(results.size() == 0)
        {
            return SearchResult.NotFound;
        }
        else if(results.size() == 1 && results.get(0) == 0 && big.length() == small.length())
        {
            return SearchResult.ExactlyTheSame;
        }
        else if(results.size() > 1)
        {
            return SearchResult.FoundMany;
        }
        return SearchResult.Found;
    }
}