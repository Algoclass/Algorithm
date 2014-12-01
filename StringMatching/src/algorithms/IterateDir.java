 package algorithms;

import java.io.*;

public class IterateDir 
{
	public static void main(String... args) {

		File documentCorpus = new File("res/Document_corpus/by.txt");
		//File documentCorpus = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus");
		File potentialPlagiarisedFile = new File("res/Potential_plagiarised_files/ts.txt");
		//File potentialPlagiarisedFile = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/corpus/1.txt");
		if (potentialPlagiarisedFile.isDirectory()) {
			File[] filesPotentiallyPlagiarised = potentialPlagiarisedFile.listFiles();
			showTestDirectory(documentCorpus, filesPotentiallyPlagiarised);
		} else {
			showTestFile(documentCorpus, potentialPlagiarisedFile);
		}
	}

	//This method is called if potential plagiarised input is a file
	public static void showTestFile(File documentCorpus, File testFile) {
		File fileToTest = testFile;
		System.out.println("Test File: " + fileToTest.getName());
		try(BufferedReader br = new BufferedReader(new FileReader(fileToTest))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line);
				sb.append(" ");
				//sb.append(System.lineSeparator());line = br.readLine();
			}
			String fileContents = sb.toString().replaceAll("\\s+", " ");
			//System.out.println("Test File Contents: "+fileContents);
			br.close();
			System.out.println("Enter number for the respective algorithm to run on above test file.");
			System.out.println("1: LCSS");
			System.out.println("2: Naive");
			System.out.println("3: KMP");
			System.out.println("4: Boyer Moore");
			System.out.println("5: Rabin-karp");
			BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in));
			int option  = Integer.parseInt(bufferReader.readLine());
			String [] fileData;
			switch(option) {
			case 1:
				//LCSS
				fileData = fileContents.split("\\.\\n?\\.\\r");
				for(int i=0; i<fileData.length;i++) {
					fileData[i] = fileData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Paragraph wise Test File Data: "+fileData[i]);
					if(documentCorpus.isDirectory()) {
						readCorpusDirectory(documentCorpus.listFiles(), fileData[i], true, false, false, false, false);
					} else {
						readCorpusFile(documentCorpus, fileData[i], true, false, false, false, false);
					}
				}
				break;
			case 2:
				//Naive
				fileData = fileContents.split("\\. ");
				for(int i=0; i<fileData.length;i++) {
					fileData[i] = fileData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Test File Data: "+fileData[i]);
					if(documentCorpus.isDirectory()) {
						readCorpusDirectory(documentCorpus.listFiles(), fileData[i], false, true, false, false, false);
					} else {
						readCorpusFile(documentCorpus, fileData[i], false, true, false, false, false);
					}
				}
				break;
			case 3:
				//KMP
				fileData = fileContents.split("\\. ");
				for(int i=0; i<fileData.length;i++) {
					fileData[i] = fileData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Test File Data: "+fileData[i]);
					if(documentCorpus.isDirectory()) {
						readCorpusDirectory(documentCorpus.listFiles(), fileData[i], false, false, true, false, false);
					} else {
						readCorpusFile(documentCorpus, fileData[i], false, false, true, false, false);
					}
				}
				break;
			case 4:
				//Boyer Moore
				fileData = fileContents.split("\\. ");
				for(int i=0; i<fileData.length;i++) {
					fileData[i] = fileData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Test File Data: "+fileData[i]);
					if(documentCorpus.isDirectory()) {
						readCorpusDirectory(documentCorpus.listFiles(), fileData[i], false, false, false, true, false);
					} else {
						readCorpusFile(documentCorpus, fileData[i], false, false, false, true, false);
					}
				}
				break;
			case 5:
				//Rabin Karp
				fileData = fileContents.split("\\. ");
				for(int i=0; i<fileData.length;i++) {
					fileData[i] = fileData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Test File Data: "+fileData[i]);
					if(documentCorpus.isDirectory()) {
						readCorpusDirectory(documentCorpus.listFiles(), fileData[i], false, false, false, false, true);
					} else {
						readCorpusFile(documentCorpus, fileData[i], false, false, false, false, true);
					}
				}
				break;
			default:
				System.out.println("Wrong Entry");
				break;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//This method is called if corpus input is a file
	public static void readCorpusFile(File fileInCorpus, String testPattern,
			Boolean lcss, Boolean naive, Boolean kmp, Boolean boyerMoore, Boolean rabinKarp) {
		System.out.println("Corpus File: " + fileInCorpus.getName());
		try(BufferedReader br = new BufferedReader(new FileReader(fileInCorpus))) {
			StringBuilder sb = new StringBuilder();
			String line ;
			while ((line= br.readLine()) != null) {
				sb.append(line);
				sb.append(" ");
				//sb.append(System.lineSeparator());
			}
			br.close();
			String fileInCorpusContents = sb.toString();
			//System.out.println("Corpus File Contents: "+fileContents);
			String [] fileInCorpusData;
			if(lcss == true) {
				fileInCorpusData = fileInCorpusContents.split("\\.\\n?\\.\\r");
				double lcssLength = 0;
				for(int i=0; i<fileInCorpusData.length;i++) {
					fileInCorpusData[i] = fileInCorpusData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Paragraph wise Test File Data: "+fileInCorpusData[i]);
					Lcss lcssObj = new Lcss();
					lcssLength = lcssLength + lcssObj.search(fileInCorpusData[i], testPattern).length();
				}
				double percent = (lcssLength*100)/testPattern.length();
				System.out.println("Percentage match: "+percent);
				// TODO
			}
			if(naive == true) {
				fileInCorpusData = fileInCorpusContents.split("\\. ");
				for(int i=0; i<fileInCorpusData.length;i++) {
					fileInCorpusData[i] = fileInCorpusData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Train File Data: "+fileInCorpusData[i]);
					NaiveString naiveStringObj = new NaiveString();
					naiveStringObj.search(fileInCorpusData[i].toCharArray(), testPattern.toCharArray());
				}
				//TODO
			}
			if(kmp == true) {
				fileInCorpusData = fileInCorpusContents.split("\\. ");
				for(int i=0; i<fileInCorpusData.length;i++) {
					fileInCorpusData[i] = fileInCorpusData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Test File Data: "+fileInCorpusData[i]);
					//Kmp kmpObj = new Kmp();
					//kmpObj.search(fileInCorpusData[i].toCharArray(), testPattern.toCharArray());
				}
				// TODO
			}
			if(boyerMoore == true) {
				fileInCorpusData = fileInCorpusContents.split("\\. ");
				for(int i=0; i<fileInCorpusData.length;i++) {
					fileInCorpusData[i] = fileInCorpusData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Train File Data: "+fileInCorpusData[i]);
					BoyerMoore boyerMooreObj = new BoyerMoore();
					boyerMooreObj.search(fileInCorpusData[i].toCharArray(), testPattern.toCharArray());
				}
				//TODO
			}
			if(rabinKarp == true) {
				fileInCorpusData = fileInCorpusContents.split("\\. ");
				for(int i=0; i<fileInCorpusData.length;i++) {
					fileInCorpusData[i] = fileInCorpusData[i].trim().replaceAll("\\s+", " ");
					System.out.println("Sentence wise Train File Data: "+fileInCorpusData[i]);
					RabinKarp rabinKarpObj = new RabinKarp();
					//rabinKarpObj.search(fileInCorpusData[i].toCharArray(), testPattern.toCharArray());
				}
				// TODO
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//This method is called if potential plagiarised input is a directory
	public static void showTestDirectory(File documentCorpus, File [] testDirectory) {
		for (File fileToTest : testDirectory) {
			if (fileToTest.isDirectory()) {
				System.out.println("Test Directory: " + fileToTest.getName());
				showTestDirectory(documentCorpus, fileToTest.listFiles());
			} else {
				showTestFile(documentCorpus, fileToTest);
			}
		}
	}

	//This method is called if corpus input is a directory
	public static void readCorpusDirectory(File [] fileInCorpus, String testPattern, 
			Boolean lcss, Boolean naive, Boolean kmp, Boolean boyerMoore, Boolean rabinKarp) {
		for (File fileToRead : fileInCorpus) {
			if (fileToRead.isDirectory()) {
				System.out.println("Corpus Directory: " + fileToRead.getName());
				readCorpusDirectory(fileToRead.listFiles(), testPattern, lcss, naive, kmp, boyerMoore, rabinKarp);
			} else {
				readCorpusFile(fileToRead, testPattern, lcss, naive, kmp, boyerMoore, rabinKarp);
			}
		}
	}
}
