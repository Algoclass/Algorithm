package algorithms;

public class NaiveString {

	
	static void search(char pat[], char txt[])
	{
	    int M = pat.length;
	    int N = txt.length;
	  
	    /* A loop to slide pat[] one by one */
	    for (int i = 0; i <= N - M; i++)
	    {
	        int j;
	  
	        /* For current index i, check for pattern match */
	        for (j = 0; j < M; j++)
	        {
	            if (txt[i+j] != pat[j])
	                break;
	        }
	        if (j == M)  // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
	        {
	           System.out.println("Pattern found at index "+ i);
	        }
	    }
	}
	  
	/* Driver program to test above function */
	public static void main(String args[])
	{
	   String t = "AABAACAADAABAAABAA";
	   char txt[]=t.toCharArray();
	   String p= "AABA";
	   char pat[]=p.toCharArray();
	   search(pat, txt);
	}
}
