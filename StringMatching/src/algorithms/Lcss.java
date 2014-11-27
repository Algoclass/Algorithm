package algorithms;

public class Lcss {

	public static void mainLCSS(String pattern, String text) {
	//public static void main(String[] args) {
		//String str1 = "abhinav bansal";
		//String str2="acsbdwvhgvrigbrbnbtbavfwtb bfdgagdsn acsbdwvhgvrigbrbnbtbavfwtb bfdgagdsnl";
		char [] str1char= pattern.toCharArray();
		char [] str2char= text.toCharArray();
		int [][] box=new int[pattern.length()+1][text.length()+1];	

		for(int i=1;i<=pattern.length();i++){
			for(int j=1;j<=text.length();j++){
				if(str1char[i-1]==str2char[j-1]){
					box[i][j]=1+box[i-1][j-1];
				}
				else{
					box[i][j]=Math.max(box[i-1][j], box[i][j-1]);
				}
			}
		}
		for(int i=0;i<=pattern.length();i++){
			for(int j=0;j<=text.length();j++){
				System.out.print(box[i][j]);
			}
			System.out.println();
		}

		String reverse=(printLCSS(box, pattern));
		String substring = new StringBuffer(reverse).reverse().toString();
		System.out.println(substring);
	}

	public static String printLCSS(int[][] box, String s1){
		String cmnStr = "";

		int x= box.length-1;
		int y = box[0].length-1;

		while(x > 0 && y > 0){			
			if(box[x-1][y] == box[x][y]){
				x--;
			}
			else if(box[x][y-1] == box[x][y]){
				y--;
			}
			else 
			{
				cmnStr = cmnStr + s1.charAt(x-1);
				x--;
				y--;
			}
		}

		return cmnStr;
	}



}
