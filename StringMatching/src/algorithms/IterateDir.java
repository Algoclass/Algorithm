package algorithms;

import java.io.*;

public class IterateDir 
{
	String pattern="";
	public static void main(String... args) {
		File pat=new File("/Users/Devcenter/Dropbox/padhai/sem3/Algo/1.txt");
		String path="/Users/Devcenter/Dropbox/padhai/sem3/Algo";
		File parent=new File(path);
		if (parent.isDirectory()){
			File[] files = parent.listFiles();
			showFiles(files);
		}
		else{
			readFile(parent);
		}
	}

	public static void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				showFiles(file.listFiles()); // Algo class wali recursion Baby!!
			} else {

				System.out.println("parent: " + file.getName());
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

	public static void readFile(File file){

		System.out.println("parent: " + file.getName());
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {// nice
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			String contents = sb.toString();
			//System.out.println(contents);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void calculate(File test){

	}

}