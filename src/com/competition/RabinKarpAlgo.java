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

        int M = a1.length();
        int N = a2.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for txt
        int h = 1;
      
        int d = NumberOfCharacters;
        int q = PrimeNumberForAlgo;
        
        String txt = a2, pat = a1;
        
        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M-1; i++)
            h = (h*d)%q;
      
        // Calculate the hash value of pattern and first
        // window of text
        for (i = 0; i < M; i++)
        {
            p = (d*p + pat.charAt(i))%q;
            t = (d*t + txt.charAt(i))%q;
        }
      
        // Slide the pattern over text one by one
        for (i = 0; i <= N - M; i++)
        {
      
            // Check the hash values of current window of text
            // and pattern. If the hash values match then only
            // check for characters on by one
            if ( p == t )
            {
                /* Check for characters one by one */
                for (j = 0; j < M; j++)
                {
                    if (txt.charAt(i+j) != pat.charAt(j))
                        break;
                }
      
                // if p == t and pat[0...M-1] = txt[i, i+1, ...i+M-1]
                if (j == M)
                	results.add(i);
                    //System.out.println("Pattern found at index " + i);
            }
      
            // Calculate hash value for next window of text: Remove
            // leading digit, add trailing digit
            if ( i < N-M )
            {
                t = (d*(t - txt.charAt(i)*h) + txt.charAt(i+M))%q;
      
                // We might get negative value of t, converting it
                // to positive
                if (t < 0)
                t = (t + q);
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

/*
		int s=0,b=0;
		int h = 1;
		
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

		//set hash index
		for(int k = 0; k < small.length; k++)
		{
			h = (h*NumberOfCharacters)%PrimeNumberForAlgo;
		}
		
		//set first hash of small and big
		for(int k = 0; k < small.length; k++)
		{
			small.hash = (small.hash*NumberOfCharacters + small.text.charAt(k))%PrimeNumberForAlgo;
			big.hash = (big.hash*NumberOfCharacters + big.text.charAt(k))%PrimeNumberForAlgo;
		}
		
		//look for matches
		for(b = 0; b <= big.length - small.length; b++)
		{
			if(big.hash == small.hash)
			{
				for(s = 0; s < small.length; s++)
				{
					if(small.text.charAt(s) != big.text.charAt(b)) break;
				}
				
				if(s == small.length)
				{
					//found a match
					results.add(b);
				}
			}
			
			//reHash
			if(b < big.length - small.length)
			{
				big.hash = (NumberOfCharacters*
						(big.hash-big.text.charAt(b)*h) + 
						big.text.charAt(b+small.length)
						)%PrimeNumberForAlgo;
				
				if(big.hash < 0)
					big.hash += PrimeNumberForAlgo;
			}
		}
*/