package line2019_02_22;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class newSol2 {

	static boolean visited[];
	static int N, tLen;
	static char[] temp2;
	static ArrayList<String> list= new ArrayList<>();;
	
	public static void main(String[] args) {
	      Scanner sc = new Scanner(System.in);
	      
	      String temp = sc.nextLine();
	      N=sc.nextInt();
	      temp2 = temp.toCharArray();
	      tLen=temp2.length;
	      visited= new boolean[tLen];
	      dfs("",0);
	      Collections.sort(list);
	      System.out.println(list.get(N-1));
	}

	private static void dfs(String s, int len) {
		if(len==tLen){
			list.add(s);
			return;
		}
		for(int i=0; i<tLen; i++){
			if(!visited[i]){
				visited[i]=true;
				dfs(s+temp2[i],len+1);
				visited[i]=false;
			}
		}
	}

}
