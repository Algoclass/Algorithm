package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RabinKarp {

	final static int d = 1000;
	static double match_count = 0;
	static ArrayList<String> rabin_karp_list = new ArrayList<String>();
	static Map<String, Integer> sentense_map = new HashMap<String, Integer>();
	static public long runnigTime = 0;
	static int q = 101;
	public static double match = 0;

	public static void main(String args[]) {
		String t = "AABAACAADAABAAABAA";
		char txt[] = t.toCharArray();
		String p = "AABA";
		char pat[] = p.toCharArray();
		int q = 101; // A prime number
		// search( txt,pat, q);
	}

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
					search(txt, pat, q, fi, corpus.get(0));
				}
			}
		}

		double count_total_sentence = pattern_list.size();
		double percentage = match_count / count_total_sentence;
		System.out.println(percentage);
		match = percentage * 100;
		long end = System.currentTimeMillis();
		rabin_karp_list.add("Total percentage match :" + (percentage) * 100);
		rabin_karp_list.add("Total time to run the algorithm" + (end - start)
				+ " miliseconds");
		runnigTime = end - start;
		return rabin_karp_list;

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
					for (String sentence : temp) {
						sentence_list.add(sentence);
						if (!sentense_map.containsKey(sentence))
							sentense_map.put(sentence, 0);
					}
				}
			}

			br.close();

		} catch (Exception e) {

		}
		list.add(para_list);
		list.add(sentence_list);
		return list;
	}

	static void search(char txt[], char pat[], int q, int file_number,
			ArrayList<String> corpus) {
		String patt = String.copyValueOf(pat);
		String tt = String.copyValueOf(txt);

		if (patt.equals(tt))
			System.out.println("equals -------");

		int M = pat.length;
		int N = txt.length;
		int i, j;
		int p = 0; // hash value for pattern
		int t = 0; // hash value for txt
		int h = 1;

		if (M <= N) {
			for (i = 0; i < M - 1; i++)
				h = (h * d) % q;

			// Calculate the hash value of pattern and first window of text
			for (i = 0; i < M; i++) {
				p = (d * p + pat[i]) % q;
				t = (d * t + txt[i]) % q;

			}

			// Slide the pattern over text one by one
			for (i = 0; i <= N - M; i++) {

				// Check the hash values of current window of text and pattern
				// If the hash values match then only check for characters on by
				// one
				if (p == t) {
					boolean match = true;
					for (j = 0; j < M; j++) {
						if (txt[i + j] != pat[j]) {
							match = false;
							break;
						}
					}
					System.out.println(match);
					if (match) // if p == t and pat[0...M-1] = txt[i, i+1,
					// ...i+M-1]
					{
						for (int k = 0; k < corpus.size(); k++) {
							if (corpus.get(k).contains(patt)) {
								String sub = "";
								if (corpus.get(k).length() > 30)
									sub = corpus.get(k).substring(0, 30);
								else
									sub = corpus.get(k);

								String patt_sub = "";
								if (patt.length() > 20)
									patt_sub = patt.substring(0, 20);
								else
									patt_sub = patt;

								rabin_karp_list.add("Pattern found in File "
										+ file_number + " at para" + k + "("
										+ sub + ")" + "for sententence ("
										+ patt + ")");
							}
						}
						if (sentense_map.get(patt) == 0) {
							match_count++;
							int val = sentense_map.get(patt);
							sentense_map.put(patt, val + 1);
						}
						System.out.println("Pattern found at index" + i);
					}
				}

				// Calculate hash value for next window of text: Remove leading
				// digit,
				// add trailing digit
				if (i < N - M) {
					t = (d * (t - txt[i] * h) + txt[i + M]) % q;

					// We might get negative value of t, converting it to
					// positive
					if (t < 0)
						t = (t + q) % q;
				}
			}

		}

	}

	/*
	 * static void search( char txt[],char pat[], int q) { String patt =
	 * String.copyValueOf(pat);
	 * 
	 * int M =pat.length; int N = txt.length; int i, j; int p = 0; // hash value
	 * for pattern int t = 0; // hash value for txt int h = 1;
	 * 
	 * 
	 * if(M<N){ for (i = 0; i < M-1; i++) h = (h*d)%q;
	 * 
	 * // Calculate the hash value of pattern and first window of text for (i =
	 * 0; i < M; i++) { p = (d*p + pat[i])%q; t = (d*t + txt[i])%q;
	 * 
	 * }
	 * 
	 * // Slide the pattern over text one by one for (i = 0; i <= N - M; i++) {
	 * 
	 * // Check the hash values of current window of text and pattern // If the
	 * hash values match then only check for characters on by one if ( p == t )
	 * { Check for characters one by one for (j = 0; j < M; j++) { if (txt[i+j]
	 * != pat[j]) break; } if (j == M) // if p == t and pat[0...M-1] = txt[i,
	 * i+1, ...i+M-1] { for(int k=0;k<corpus.size();k++) {
	 * if(corpus.get(k).contains(patt)) { String sub =
	 * corpus.get(k).substring(0,30);
	 * rabin_karp_list.add("Pattern found in File "+file_number +" at para"+k
	 * +"("+sub+")" + "for sententence (" +patt.substring(0,20)+")"); } } if
	 * (sentense_map.get(patt) == 0) { match_count++; int val =
	 * sentense_map.get(patt); sentense_map.put(patt, val + 1); }
	 * System.out.println("Pattern found at index"+ i); } }
	 * 
	 * // Calculate hash value for next window of text: Remove leading digit, //
	 * add trailing digit if ( i < N-M ) { t = (d*(t - txt[i]*h) + txt[i+M])%q;
	 * 
	 * // We might get negative value of t, converting it to positive if(t < 0)
	 * t = (t + q)%q; } }
	 * 
	 * }
	 * 
	 * }
	 */

}
