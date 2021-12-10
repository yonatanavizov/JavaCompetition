package com.competition;
import java.util.List;

public interface ISearchAlgoFamily
{
	    enum SearchResult
	    {
	        Found,
	        ExactlyTheSame,
	        FoundMany,
	        NotFound
	    }
	    SearchResult Search(String a1,String a2);
	    List<Integer> GetFoundResults();
}