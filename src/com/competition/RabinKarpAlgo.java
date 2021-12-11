package com.competition;

import java.util.ArrayList;
import java.util.List;

public class RabinKarpAlgo implements ISearchAlgoFamily
{
	public static int NumberOfCharacters = 256;
	public static int PrimeNumberForAlgo = 13;
	
	private List<Integer> results;
	private StringForAlgo big, small;
	
	public RabinKarpAlgo()
	{
		results = new ArrayList<Integer>();
	}
	
	@Override
	public SearchResult Search(String a1, String a2)
	{
		if(a1.length() > a2.length())
		{
			big = new StringForAlgo(a1);
			small = new StringForAlgo(a2);
		}
		else
		{
			big = new StringForAlgo(a2);
			small = new StringForAlgo(a1);
		}

        int i, j;
        int h = 1;
        
        // calculate hash modifier
        for (i = 0; i < small.length-1; i++)
            h = (h*NumberOfCharacters)%PrimeNumberForAlgo;
      
        //calculate starting hash
        for (i = 0; i < small.length; i++)
        {
            small.hash = (NumberOfCharacters*small.hash + small.text.charAt(i))%PrimeNumberForAlgo;
            big.hash = (NumberOfCharacters*big.hash + big.text.charAt(i))%PrimeNumberForAlgo;
        }
      
        for (i = 0; i <= big.length - small.length; i++)
        {
            if ( small.hash == big.hash )
            {
                for (j = 0; j < small.length; j++)
                {
                    if (big.text.charAt(i+j) != small.text.charAt(j))
                        break;
                }
      
                if (j == small.length) //found match
                	results.add(i);
            }
      
            //rehash
            if ( i < big.length-small.length )
            {
                big.hash = (NumberOfCharacters*(big.hash - big.text.charAt(i)*h) + big.text.charAt(i+small.length))%PrimeNumberForAlgo;

                if (big.hash < 0)
                	big.hash = (big.hash + PrimeNumberForAlgo);
            }
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
        else if(results.size() == 1 && results.get(0) == 0 && big.length == small.length)
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