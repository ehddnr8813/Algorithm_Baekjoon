package sw4039_두번이상등장하는문자열;

import java.io.BufferedReader;
import java.util.Scanner;

public class Solution {

	static int L;
	static String s;
	static char str[];
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			L=sc.nextInt();
			sc.nextLine();
			s=sc.nextLine();
			str= new char[L];
			for(int i=0; i<L; i++) {
				str[i]=s.charAt(i);
				System.out.print(str[i]);
			}
			System.out.println();
		}
	}

}