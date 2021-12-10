package com.competition;

import java.util.ArrayList;
import java.util.List;

public class KMPSearchAlgo implements ISearchAlgoFamily
{

	private List<Integer> results;
    private int[] lps;
    private String pat, txt;

    public KMPSearchAlgo()
    {
        results = new ArrayList<>();
    }
    @Override
    public SearchResult Search(String a1, String a2)
    {
        SetPatternAndText(a1,a2);
        lps = new int[pat.length()];
        int pat_index = 0, txt_index = 0;

        CalculateLPSArray(pat);

        while(txt_index < txt.length() - pat.length())
        {
            if(pat_index == pat.length()) // Found a Result
            {
                results.add(txt_index-pat_index); // ?~
                pat_index = lps[pat_index-1];
            }
            if(pat.charAt(pat_index) == txt.charAt(txt_index))
            {
                pat_index++;
                txt_index++;
            }
            else if(pat_index > 0)
            {
                pat_index = lps[pat_index-1];
            }
            else if(pat_index == 0)
            {
                txt_index++;
            }
        }


        return FindResult();
    }

    private void CalculateLPSArray(String pattern)
    {
        int length = 0;
        int pat_length = pattern.length();
        int index = 1;
        lps[0] = 0;

        while(index < pat_length)
        {
            if(pattern.charAt(length) == pattern.charAt(index)) //AAABAA // 012001
            {
                lps[index] = lps[index-1];
                index++;
                length++;
            }
            else if(length > 0)
            {
                length = lps[length-1];
            }
            else
            {
                lps[index] = length;
                index++;
            }
        }
    }
    private SearchResult FindResult()
    {
        if(results.size() == 0)
        {
            return SearchResult.NotFound;
        }
        else if(results.size() == 1 && results.get(0) == 0 && pat.length() == txt.length())
        {
            return SearchResult.ExactlyTheSame;
        }
        else if(results.size() > 1)
        {
            return SearchResult.FoundMany;
        }
        return SearchResult.Found;
    }
    private void SetPatternAndText(String a1, String a2)
    {
        if(a1.length() > a2.length())
        {
            pat = a2;
            txt = a1;
        }
        else
        {
            pat = a1;
            txt = a2;
        }
    }
    @Override
    public List<Integer> GetFoundResults()
    {
        return results;
    }

}
