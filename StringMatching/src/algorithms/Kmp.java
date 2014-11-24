package algorithms;

import java.util.ArrayList;
import java.util.Arrays;

public class Kmp {

	ArrayList<String> text=null;
	ArrayList<String> pattern=null;
	
	public Kmp(ArrayList<String> text, ArrayList<String> pattern){
		this.text = text;
		this.pattern = pattern;
	}
	// Method to calculate prefix function of given pattern
	public ArrayList<Integer> compute_prefix(){
		int pattern_length = pattern.size()-1;
		ArrayList<Integer> prefix_array = new ArrayList<>(); 
		prefix_array.add(0,0);
		int k=0;
		int j=0;
		for(int q=1;q<=pattern_length;q++)
		{
			while( k>0 && pattern.get(k)!=pattern.get(q)){
				k = prefix_array.get(k);
			}
			k=j;
			if(pattern.get(k).equals(pattern.get(q))){
				k = k+1;
				j=k;
			}
			prefix_array.add(q, k);
		}
		return prefix_array;
	}

	// Method to calculate the index where match occurs 
	public void kmp_matcher()
	{
		int text_length = text.size()-1;
		int pattern_length = pattern.size()-1;
		ArrayList<Integer> prefix_array =compute_prefix();
		System.out.println(prefix_array);
		int q = -1;
		int j=0;
		for(int i=0;i<=text_length;i++){
			while(q>0 && q<=pattern_length && pattern.get(q)!=text.get(i)){
				q = prefix_array.get(q); 
			}
			q=j;
			if(q>=0 && q <=pattern_length&&pattern.get(q).equals(text.get(i))){

				q=q+1;
				j=q;
			}
			if(q==pattern_length){
				System.out.println("Pattern occurs with shift "+(i-pattern_length+1));
				q=prefix_array.get(q);
			}
			
		}
	}
	public static void main(String args[]){
		String pString= "vir";
		String tString= "guranantsinghvirdee";
		ArrayList<String> pList = new ArrayList<String>(Arrays.asList(pString.split("")));
		ArrayList<String> tList = new ArrayList<String>(Arrays.asList(tString.split("")));
		Kmp obj1= new Kmp(tList,pList);
		obj1.kmp_matcher();
	}
}
