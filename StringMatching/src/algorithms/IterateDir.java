package algorithms;

import java.io.*;

public class IterateDir 
{
	public static void main(String... args) {
		File[] files = new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo").listFiles();
		showFiles(files);
	}

	public static void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				showFiles(file.listFiles()); // Algo class wali recursion Baby!!
			} else {
				System.out.println("File: " + file.getName());
				 try(BufferedReader br = new BufferedReader(new FileReader(file))) {
				        StringBuilder sb = new StringBuilder();
				        String line = br.readLine();

				        while (line != null) {// nice
				            sb.append(line);
				            sb.append(System.lineSeparator());
				            line = br.readLine();
				        }
				        String contents = sb.toString();
				        System.out.println(contents);
				    } catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}

}