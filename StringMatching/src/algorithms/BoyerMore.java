import java.io.*;
import java.util.*;

public class BoyerMore {

	final static int  NO_OF_CHARS=100000;
	static int testCounter = 0;
	static int patternCounter =0;
	
	public static void main(String args[])
	{
	    /*String t= "In computer science, the Boyer–Moore string search algorithm is an efficient string searching algorithm that is the standard benchmark for practical string search literature.[1] It was developed by Robert S. Boyer and J Strother Moore in 1977.[2] The algorithm preprocesses the string being searched for (the pattern), but not the string being searched in (the text). It is thus well-suited for applications in which the pattern is much shorter than the text or does persist across multiple searches. The Boyer-Moore algorithm uses information gathered during the preprocess step to skip sections of the text, resulting in a lower constant factor than many other string algorithms. In general, the algorithm runs faster as the pattern length increases. The key feature of the algorithm is to match on the tail of the pattern rather than the head, and to skip along the text in jumps of multiple characters rather than searching every single character in the text.";
	    t= t.replaceAll(" ","");
	    char txt[] = t.toCharArray();
	    String p= "It is thus well-suited for applications      in which the pattern is much shorter than the text or does persist across multiple searches.";
	    p= p.replaceAll(" ","");
	    char pat[] =p.toCharArray();
		*/
		String filepath="by.txt";
	    ArrayList<String> text = getList(filepath);
	    ArrayList<String> pattern = getList("ts.txt");

	    for(int i=0;i<pattern.size();i++){
	    	String t = pattern.get(i);
	    	t= t.replaceAll(" ","");
	    	char txt[] = t.toCharArray();
	    	patternCounter = i; 
	    	for(int j=0;j<text.size();j++){
	    		String p = text.get(j);
	    		p= p.replaceAll(" ","");
	    		char pat[] =p.toCharArray();
	    		testCounter = j;
	    		search(txt, pat);
	    	}
	    }
	    
	}
	

	static ArrayList<String> getList(String filepath){

		ArrayList<String> list=null;
		try{

			File file = new File (filepath);
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			list = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
   				// process the line.
				if(line.length()!=0)
				{
					list.add(line);
				}
			}
			 
		br.close();

		}catch(Exception e){

		}
		return list;
	}


	/* A pattern searching function that uses Bad Character Heuristic of
	   Boyer Moore Algorithm */
	static void search( char txt[],  char pat[])
	{
	    int m = pat.length;
	    int n = txt.length;
	 	//System.out.println(n +"---------------"+m);
	    int badchar[] = new int[NO_OF_CHARS];
	 
	    /* Fill the bad character array by calling the preprocessing
	       function badCharHeuristic() for given pattern */
	    badCharHeuristic(pat, m, badchar);
	 
	    int s = 0;  // s is shift of the pattern with respect to text
	    while(s <= (n - m))
	    {
	        int j = m-1;
	 
	        /* Keep reducing index j of pattern while characters of
	           pattern and text are matching at this shift s */
	        while(j >= 0 && pat[j] == txt[s+j])
	            j--;
	 
	        /* If the pattern is present at current shift, then index j
	           will become -1 after the above loop */
	        if (j < 0)
	        {
	        	System.out.println("Pattern found for para in pattern"+patternCounter+" for test"+testCounter);
	            //System.out.println("pattern occurs at shift"+ s);
	 
	            /* Shift the pattern so that the next character in text
	               aligns with the last occurrence of it in pattern.
	               The condition s+m < n is necessary for the case when
	               pattern occurs at the end of text */
	            s += (s+m < n)? m-badchar[txt[s+m]] : 1;
	 
	        }
	 
	        else
	            /* Shift the pattern so that the bad character in text
	               aligns with the last occurrence of it in pattern. The
	               max function is used to make sure that we get a positive
	               shift. We may get a negative shift if the last occurrence
	               of bad character in pattern is on the right side of the
	               current character. */
	            s += max(1, j - badchar[txt[s+j]]);
	    }
	}
	
	static void badCharHeuristic( char str[], int size, int badchar[])
	{
	    int i;
	 
	    // Initialize all occurrences as -1
	    for (i = 0; i < NO_OF_CHARS; i++)
	         badchar[i] = -1;
	 
	    // Fill the actual value of last occurrence of a character
	    for (i = 0; i < size; i++)
	         badchar[(int) str[i]] = i;
	}
	
	static int max (int a, int b) 
	{ 
		return (a > b)? a: b; 
	}
}
