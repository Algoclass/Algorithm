package algorithms;

import java.io.*;

public class IterateDir 
{
	String pattern="";
	public static void main(String... args) {
		//File pat=new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/1.txt");
		//String path="/Users/Devcenter/Dropbox/padhai/sem3/Algo";
		File documentCorpus = new File("res/Document_corpus");
		File potentialPlagiarisedFile = new File("res/Potential_plagiarised_files");
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
			System.out.println("Test File Contents: "+fileContents);
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
						readCorpusFile(documentCorpus, fileContents, true, false, false, false, false);
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
						readCorpusFile(documentCorpus, fileContents, false, true, false, false, false);
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
						readCorpusFile(documentCorpus, fileContents, false, false, true, false, false);
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
						readCorpusFile(documentCorpus, fileContents, false, false, false, true, false);
					}
				}
				break;
			case 5:
				//Rabin Karp
				// TODO
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
			String fileContents = sb.toString();
			System.out.println("Corpus File Contents: "+fileContents);
			if(lcss == true) {
				Lcss lcssObj = new Lcss();
				lcssObj.mainLCSS(fileContents, testPattern);
			}
			if(naive == true) {
				NaiveString naiveStringObj = new NaiveString();
				naiveStringObj.search(fileContents.toCharArray(), testPattern.toCharArray());
			}
			if(kmp == true) {
				//Kmp kmpObj = new Kmp();
				// TODO
			}
			if(boyerMoore == true) {
				BoyerMoore boyerMooreObj = new BoyerMoore();
				// TODO
			}
			if(rabinKarp == true) {
				RabinKarp rabinKarpObj = new RabinKarp();
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

	public static void readFile(File fileToRead) {
		System.out.println("parent: " + fileToRead.getName());
		try(BufferedReader br = new BufferedReader(new FileReader(fileToRead))) {
			StringBuilder sb = new StringBuilder();
			String line ;
			while ((line= br.readLine()) != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
			}
			br.close();
			String fileContents = sb.toString();
			System.out.println(fileContents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void calculate(File test) {
	}
}
