package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Kmp {

	ArrayList<String> corpus_sentence = null;
	ArrayList<String> pattern_sentence = null;

	public static double match = 0;
	static double match_count = 0;
	static long runningTime = 0;
	static Map<String, Integer> sentense_map = new HashMap<String, Integer>();
	static ArrayList<String> kmp_list = new ArrayList<String>();

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
				ArrayList<String> pList = new ArrayList<String>(Arrays.asList(p
						.split("")));
				for (int j = 0; j < corpus_list.size(); j++) {
					String t = corpus_list.get(j);
					ArrayList<String> tList = new ArrayList<String>(
							Arrays.asList(t.split("")));
					Kmp obj1 = new Kmp(tList, pList);
					obj1.kmp_matcher(fi, corpus.get(0));
				}
			}

			System.out.println(files[fi]);
		}
		double count_total_sentence = pattern_list.size();
		double percentage = match_count / count_total_sentence;
		System.out.println(percentage);
		match = percentage * 100;
		long end = System.currentTimeMillis();
		runningTime = end - start;
		kmp_list.add("Total percentage match :" + (percentage) * 100);
		kmp_list.add("Total time to run the algorithm" + (end - start)
				+ " miliseconds");
		kmp_list.add("End time" + end + " ,Start time" + start);

		return kmp_list;
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

	public Kmp(ArrayList<String> text, ArrayList<String> pattern) {
		this.corpus_sentence = text;
		this.pattern_sentence = pattern;
	}

	// Method to calculate prefix function of given pattern
	public ArrayList<Integer> compute_prefix() {
		int pattern_length = pattern_sentence.size() - 1;
		ArrayList<Integer> prefix_array = new ArrayList<>();
		prefix_array.add(0, 0);
		int k = 0;
		int j = 0;
		for (int q = 1; q <= pattern_length; q++) {
			while (k > 0 && pattern_sentence.get(k) != pattern_sentence.get(q)) {
				k = prefix_array.get(k);
			}
			k = j;
			if (pattern_sentence.get(k).equals(pattern_sentence.get(q))) {
				k = k + 1;
				j = k;
			}
			prefix_array.add(q, k);
		}
		return prefix_array;
	}

	// Method to calculate the index where match occurs
	public void kmp_matcher(int file_number, ArrayList<String> corpus) {
		int text_length = corpus_sentence.size() - 1;
		int pattern_length = pattern_sentence.size() - 1;

		StringBuilder sb = new StringBuilder();
		for (String pa : pattern_sentence)
			sb.append(pa);
		String patt = sb.toString();

		ArrayList<Integer> prefix_array = compute_prefix();
		// System.out.println(prefix_array);
		int q = -1;
		int j = 0;
		for (int i = 0; i <= text_length; i++) {
			while (q > 0 && q <= pattern_length
					&& pattern_sentence.get(q) != corpus_sentence.get(i)) {
				q = prefix_array.get(q);
			}
			q = j;
			if (q >= 0 && q <= pattern_length
					&& pattern_sentence.get(q).equals(corpus_sentence.get(i))) {

				q = q + 1;
				j = q;
			}
			if (q == pattern_length) {
				String pattern_match = "";
				for (int k = 0; k <= pattern_length; k++) {
					pattern_match = pattern_match + pattern_sentence.get(k);
				}
				System.out.println("Pattern matched is: " + pattern_match);
				System.out.println("Pattern occurs with shift "
						+ (i - pattern_length + 1));
				// q=prefix_array.get(q);

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

						kmp_list.add("Pattern found in File " + file_number
								+ " at para" + k + "(" + sub + ")"
								+ "for sententence (" + patt_sub + ")");
					}
				}

				if (sentense_map.get(patt) == 0) {
					match_count++;
					int val = sentense_map.get(patt);
					sentense_map.put(patt, val + 1);
				}

			}

		}
	}

	public static void main(String args[]) {
		String pString = "vir";
		String tString = "guranantsinghvirdee";
		ArrayList<String> pList = new ArrayList<String>(Arrays.asList(pString
				.split("")));
		ArrayList<String> tList = new ArrayList<String>(Arrays.asList(tString
				.split("")));
		Kmp obj1 = new Kmp(tList, pList);
		// obj1.kmp_matcher();
	}
}
