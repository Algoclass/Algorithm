package algorithms;

public class NaiveString {

	
	static void search(char pattern[], char text[]) {
	    int M = pattern.length;
	    int N = text.length;
	  
	    /* A loop to slide pattern[] one by one */
	    for (int i = 0; i <= N - M; i++) {
	        int j;
	        /* For current index i, check for pattern match */
	        for (j = 0; j < M; j++) {
	            if (text[i+j] != pattern[j])
	                break;
	        }
	        if (j == M) { // if pattern[0...M-1] = txt[i, i+1, ...i+M-1]
	           System.out.println("Pattern found at index "+ i);
	           String patternString = new String(pattern);
	           System.out.println("Matched Pattern is : "+patternString);
	        }
	    }
	}

	/* Driver program to test above function */
	public static void main(String args[]) {
		String t = "AABAACAADAABAAABAA";
		//String t = "Abhinav Bansal";
		char txt[]=t.toCharArray();
		String p= "Abhinav BA";
		char pat[]=p.toCharArray();
		search(pat, txt);
	}
}
