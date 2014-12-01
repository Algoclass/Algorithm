package algorithms;

public class Lcss {

	public static String search(String pattern, String text) {
		char[] str1char = pattern.toCharArray();
		char[] str2char = text.toCharArray();
		int[][] box = new int[pattern.length() + 1][text.length() + 1];

		// Input the values in matrix
		for (int i = 1; i <= pattern.length(); i++) {
			for (int j = 1; j <= text.length(); j++) {
				if (str1char[i - 1] == str2char[j - 1]) {
					box[i][j] = 1 + box[i - 1][j - 1];
				} else {
					box[i][j] = Math.max(box[i - 1][j], box[i][j - 1]);
				}
			}
		}
		// Display the matrix
		/*
		 * for(int i = 0; i <= pattern.length(); i++) { for(int j = 0;j <=
		 * text.length(); j++) { System.out.print(box[i][j]); }
		 * System.out.println(); }
		 */
		String reverse = (printLCSS(box, pattern));
		String substring = new StringBuffer(reverse).reverse().toString();
		// System.out.println(substring);
		return substring;
	}

	public static String printLCSS(int[][] box, String pattern) {
		String cmnStr = "";

		int x = box.length - 1;
		int y = box[0].length - 1;

		while (x > 0 && y > 0) {
			if (box[x - 1][y] == box[x][y]) {
				x--;
			} else if (box[x][y - 1] == box[x][y]) {
				y--;
			} else {
				cmnStr = cmnStr + pattern.charAt(x - 1);
				x--;
				y--;
			}
		}
		return cmnStr;
	}

	/* Driver program to test above function */
	public static void main(String[] args) {
		String pattern = "abhinav bansal cc";
		String text = "acsbdwvhgvrigbrbnbtbavfwtb bfdgagdsn acsbdwvhgvrigbrbnbtbavfwtb bfdgagdsnl";
		String lcss = search(pattern, text);
		double lcssLength = lcss.length();
		double percent = (lcssLength * 100) / pattern.length();
		System.out.println("Percentage match: " + percent);
	}
}