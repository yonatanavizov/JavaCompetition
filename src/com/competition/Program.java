package com.competition;

public class Program
{
	public static void main(String[] args)
	{
		System.out.println("ASJHGDJKASDBA");
		
		ISearchAlgoFamily searcher = new NaiveSearchAlgo();
		
		String s = "ASAF";
		String b = "somethingASAF12378ASAF1827hellothere";
		
		System.out.println(searcher.Search(s, b));
		System.out.println(searcher.GetFoundResults());
	}
}