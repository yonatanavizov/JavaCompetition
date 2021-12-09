package compotition;
import java.util.Random;


public  class GenricSearchAlgo implements ISearchStringAlgo
{
	
	@Override
	public boolean Search(String a1, String a2)
	{
		int i=0,j=0;
		if (a1.length()!= a2.length())
		{
			return false;
		}
		while (i<a1.length())
		{
			for(j=0;j<a2.length();j++)
			{
				if (a1.charAt(i)==a2.charAt(j))
				{
					i++;
				}
				else
				{
					return false;
				}
			}
		}
		
		return true;
	}

	@Override
	public  boolean ContainStr(String a1, String a2)
	{
		
		if (a1.length()>a2.length())
		{
			return Contains(a1,a2); 
		}
		else if (a1.length()<a2.length())
		{
			return Contains(a2,a1);
		}
		
		return Search(a1,a2);
	}
	
	private boolean Contains(String big, String small) 
	{
		int i=0,j=0,k=0;
		int counter=0; 
		//System.out.print(big.length());
		//System.out.print(small.length());
		while (j<big.length()-small.length())
		{
			j=k;
			while(i<small.length())
			{
				if(small.charAt(i)==big.charAt(j))
				{
					i++;
					j++;
					counter++;
					//"asaf";
					//"hfaerasafaaaa";
					
				}
				else 
				{
					//System.out.print(big.charAt(k));
					i=0;
					k++;
					counter=1;
					break;
					
				}
			}
			System.out.println(counter);
			if(counter-1==small.length())
			{
				return true;
			}
			
		}
		
		return false ;
	}
	
	public boolean  LinareSearch(String arr[],String ElementToFind)
	{
		int i=0;
		for (i=0;i<arr.length;i++)
		{
			if (arr[i]==ElementToFind)
			{
				return true;
			}
		}
		return false;
	}
	public  Compretedor MatchByLev(Compretedor[] arr,char Lev)
	{
		int i=0;
		Compretedor compretedor;
		Random rand = new Random(); 
		Compretedor[] temp= new Compretedor[arr.length];
		for (i=0;i<arr.length;i++)
		{
			if (arr[i].rank==Lev)
			{
				temp[i]=arr[i];
			}
		}
		//compretedor=rand.;//random from the list of temp and then delete it from temp
		
		return compretedor;
	}

}
