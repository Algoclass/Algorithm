package algorithms;

public class Lcss {

	public static void main(String[] args) {
		String str1 = "abhinav";
		String str2="acsbdwvhgvrigbrbnbtbavfwtb";
		char [] str1char= str1.toCharArray();
		char [] str2char= str2.toCharArray();
		int [][] box=new int[str1.length()+1][str2.length()+1];	

		for(int i=1;i<=str1.length();i++){
			for(int j=1;j<=str2.length();j++){
				if(str1char[i-1]==str2char[j-1]){
					box[i][j]=1+box[i-1][j-1];
				}
				else{
					box[i][j]=Math.max(box[i-1][j], box[i][j-1]);
				}
			}
		}
		for(int i=0;i<=str1.length();i++){
			for(int j=0;j<=str2.length();j++){
				System.out.print(box[i][j]);
			}
			System.out.println();
		}

		String reverse=(printLCSS(box, str1));
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
