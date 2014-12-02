package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class NaiveString {

	static double match_count = 0;
	static ArrayList<String> naive_list = new ArrayList<String>();
	static int runnigTime = 0;

	public static ArrayList<String> computeAlgo(File documentCorpus,
			File testFile) {

		long start = System.currentTimeMillis();
		ArrayList<String> pattern_list = null;
		File files[] = documentCorpus.listFiles();

		for (int fi = 0; fi < files.length; fi++) {
			ArrayList<ArrayList<String>> pattern = getList(testFile);
			ArrayList<ArrayList<String>> corpus = getList(files[fi]);

			ArrayList<String> corpus_list = corpus.get(1);
			pattern_list = pattern.get(1);

			for (int i = 0; i < pattern_list.size(); i++) {
				String p = pattern_list.get(i);
				// t= t.replaceAll(" ","");
				char pat[] = p.toCharArray();
				for (int j = 0; j < corpus_list.size(); j++) {
					String t = corpus_list.get(j);
					// p= p.replaceAll(" ","");
					char txt[] = t.toCharArray();
					search(txt, pat, fi, corpus.get(0));
				}
			}

		}

		double count_total_sentence = pattern_list.size();
		double percentage = match_count / count_total_sentence;
		System.out.println(percentage);

		long end = System.currentTimeMillis();
		naive_list.add("Total percentage match :" + (percentage) * 100);
		naive_list.add("Total time to run the algorithm" + (end - start)
				+ " miliseconds");
		return naive_list;
	}

	static ArrayList<ArrayList<String>> getList(File file) {

		ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
		ArrayList<String> para_list = null;
		ArrayList<String> sentence_list = null;
		try {

			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			para_list = new ArrayList<String>();
			sentence_list = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				// process the line.
				if (line.length() != 0) {
					para_list.add(line);
					String temp[] = line.split("\\. ");
					for (String sentence : temp)
						sentence_list.add(sentence);
				}
			}

			br.close();

		} catch (Exception e) {

		}
		list.add(para_list);
		list.add(sentence_list);
		return list;
	}

	static void search(char text[], char pattern[], int file_number,
			ArrayList<String> corpus) {
		int M = pattern.length;
		int N = text.length;
		String patt = String.copyValueOf(pattern);
		/* A loop to slide pattern[] one by one */
		for (int i = 0; i <= N - M; i++) {
			int j;
			/* For current index i, check for pattern match */
			for (j = 0; j < M; j++) {
				if (text[i + j] != pattern[j])
					break;
			}
			if (j == M) { // if pattern[0...M-1] = txt[i, i+1, ...i+M-1]

				for (int k = 0; k < corpus.size(); k++) {
					if (corpus.get(k).contains(patt)) {
						String sub = corpus.get(k).substring(0,
								Math.min(30, (corpus.get(k).length() - 1)));
						naive_list.add("Pattern found in File "
								+ file_number
								+ " at para"
								+ k
								+ "("
								+ sub
								+ ")"
								+ "for sententence ("
								+ patt.substring(0,
										Math.min(20, patt.length() - 1)) + ")");
					}
				}

				System.out.println("Pattern found at index " + i);
				String patternString = new String(pattern);
				System.out.println("Matched Pattern is : " + patternString);
				match_count++;
			}
		}
	}

	/* Driver program to test above function */
	public static void main(String args[]) {
		String t = "AABAACAADAABAAABAA";
		// String t = "Abhinav Bansal";
		char txt[] = t.toCharArray();
		String p = "Abhinav BA";
		char pat[] = p.toCharArray();
		// search(pat, txt);
	}
}
