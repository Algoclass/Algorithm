package algorithms;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class LcssCompute {

	public static long runningTime = 0;
	public static double match = 0;

	public static void main(String... args) {

		// File documentCorpus = new File("res/Document_corpus/by.txt");
		File documentCorpus = new File(
				"/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus");
		File potentialPlagiarisedFile = new File(
				"/Users/Devcenter/Dropbox/padhai/sem3/Algo/1.txt");
		// File potentialPlagiarisedFile = new
		// File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus/1.txt");
		ArrayList<String> percent = compute(documentCorpus,
				potentialPlagiarisedFile);
		for (String s : percent) {
			System.out.println(s);
		}
	}

	public static ArrayList<String> compute(File documentCorpus, File testFile) {
		long startTime = System.currentTimeMillis();

		ArrayList<String> outputList = new ArrayList<String>();
		System.out.println("Test File: " + testFile.getName());
		double num = 0;
		double den = 0;

		HashMap patternTestMap = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
			String line;
			ArrayList<String> patt = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				patt.add(line);
				den = den + line.length();
				// sb.append(System.lineSeparator());line = br.readLine();
			}
			// System.out.println("FileContents"+fileContents);
			// String[] patternParasArr = fileContents.split("\\n");
			String[] patternParasArr = new String[patt.size()];
			patternParasArr = patt.toArray(patternParasArr);
			// System.out.println(patternParasArr[0]);

			int i = 0;

			for (String paras : patternParasArr) {
				patternTestMap.put(i, "");
				i = i + 1;
			}

			Set set = patternTestMap.entrySet(); // Get an iterator Iterator
			/*
			 * j = set.iterator(); // Display elements while (j.hasNext()) {
			 * Map.Entry me = (Map.Entry) j.next(); System.out.print(me.getKey()
			 * + ": "); System.out.println(me.getValue()); }
			 */

			// System.out.println("Test File Contents: "+fileContents);
			br.close();

			if (documentCorpus.isDirectory()) {
				Lcss lcssObj = new Lcss();
				File[] files = documentCorpus.listFiles();
				int var = 1;
				for (File file : files) {
					BufferedReader br_f = new BufferedReader(new FileReader(
							file));
					ArrayList<String> patt1 = new ArrayList<String>();
					while ((line = br_f.readLine()) != null) {
						patt1.add(line);
					}
					String[] testFileParasArr = new String[patt1.size()];
					testFileParasArr = patt1.toArray(testFileParasArr);
					String source = "";
					int l = 0;
					for (String patternPara : patternParasArr) {
						int par = 0;
						String max = patternTestMap.get(l).toString();

						// System.out.println("xxxxxxxxxxxxxxxxxx  max= "+var+" "+max);
						for (String testPara : testFileParasArr) {
							@SuppressWarnings("static-access")
							String lcss = lcssObj.search(patternPara, testPara);
							if (max.length() < lcss.length()) {
								max = lcss;
								// System.out.println("adding source" + par);
								source = "Pattern found in File "
										// + file.getName()
										+ var
										+ " at para"
										+ par
										+ "("
										+ testPara.substring(0, Math.min(
												testPara.length() - 1, 25))
										+ ")";
							}
						}
						patternTestMap.put(l, max);

						l = l + 1;
					}
					var = var + 1;
					outputList.add(source);
				}
				Iterator q = set.iterator();
				// Display elements

				while (q.hasNext()) {
					Map.Entry me = (Map.Entry) q.next();
					num = num + me.getValue().toString().length();

				}

			} // write code for else when it is just a file
			// ------------------------------------------------

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (den != 0) {
			double percent = num / den * 100;
			String pc = "Percentage matching= " + String.valueOf(percent) + "%";
			outputList.add(pc);
			match = percent;
		}
		long endTime = System.currentTimeMillis();
		runningTime = endTime - startTime;
		return outputList;
	}
}
