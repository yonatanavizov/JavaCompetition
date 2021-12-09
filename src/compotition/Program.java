package compotition;

public class Program {
	

	public static void main(String[] args)
	{
		String arr []= {"asaf","yoni","roi"};
		boolean ans;
		String a="asaf";
		String b="hfaerasafaaaa";
		GenricSearchAlgo te = new GenricSearchAlgo ();
		ans=te.LinareSearch(arr, a);
		System.out.print(ans);
		

	}

}
