 package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LcssCompute 
{
	public static void main(String... args) {

		//File documentCorpus = new File("res/Document_corpus/by.txt");
		File documentCorpus = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus");
		File potentialPlagiarisedFile = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/1.txt");
		//File potentialPlagiarisedFile = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus/1.txt");
		double percent= compute(documentCorpus, potentialPlagiarisedFile);
		System.out.println(percent*100+"%");
		
	}
	
	public static double compute(File documentCorpus, File testFile){
		System.out.println("Test File: " + testFile.getName());
		double num=0;
		double den=1;
		
		HashMap patternTestMap = new HashMap<>();
		try(BufferedReader br = new BufferedReader(new FileReader(testFile))) {
			String line;
			ArrayList<String> patt = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				patt.add(line);
				den=den+line.length();
				//sb.append(System.lineSeparator());line = br.readLine();
			}
			//System.out.println("FileContents"+fileContents);
			//String[] patternParasArr = fileContents.split("\\n");
			String[] patternParasArr = new String[patt.size()]; 
			patternParasArr=patt.toArray(patternParasArr);
			//System.out.println(patternParasArr[0]);
			
			for(String part:patternParasArr){
				System.out.println(part);
			}
			
			
			int i=0;
			
			for(String paras: patternParasArr){
				patternTestMap.put(i, "");
				i=i+1;
			}
			
			
			Set set = patternTestMap.entrySet();
		      // Get an iterator
		      Iterator j = set.iterator();
		      // Display elements
		      while(j.hasNext()) {
		         Map.Entry me = (Map.Entry)j.next();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		      }
		      
			
			//System.out.println("Test File Contents: "+fileContents);
			br.close();
			
			if (documentCorpus.isDirectory()){
				Lcss lcssObj = new Lcss();
			File [] files = documentCorpus.listFiles();
			int var=1;
			for(File file: files){
				BufferedReader br_f = new BufferedReader(new FileReader(file));
				ArrayList<String> patt1 = new ArrayList<String>();
				while ((line = br_f.readLine()) != null) {
					patt1.add(line);
				}
				String[] testFileParasArr = new String[patt1.size()]; 
				testFileParasArr=patt1.toArray(testFileParasArr);
				
				int l=0;
				for(String patternPara:patternParasArr){
					String max=patternTestMap.get(l).toString();
					System.out.println("xxxxxxxxxxxxxxxxxx  max= "+var+" "+max);
					for(String testPara: testFileParasArr){
						String lcss = lcssObj.search(patternPara,testPara);
						if(max.length()<lcss.length()){
							max=lcss;
						}
					}
					patternTestMap.put(l, max);
				l=l+1;	
				}
				var=var+1;
				
			}
			Iterator q = set.iterator();
		      // Display elements
			 
		      while(q.hasNext()) {
		         Map.Entry me = (Map.Entry)q.next();
		         System.out.print(me.getKey() + ": ");
		         System.out.println(me.getValue());
		         num=num+me.getValue().toString().length();
		         
		      }
			
			} // write code for else when it is just a file ------------------------------------------------
	
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(den!=0){
		return num/den;
		}
		else{
			return 0;
		}
}
}
