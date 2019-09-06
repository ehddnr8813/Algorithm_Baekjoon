package testAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	static int N, answer;
	static int[] card, selected ,tempArr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T= Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			selected=new int[5];
			answer=Integer.MAX_VALUE;
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			card= new int[N];

			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				card[i]=Integer.parseInt(st.nextToken());
			}

			dfs(0);
			if(answer==Integer.MAX_VALUE)System.out.println(-1);
			else System.out.println(answer);
		}
	}
	
	private static void dfs(int count) {
		isAnswer(count);
		if(count==5 || count>=answer)return;

		for(int i=0; i<N; i++) {
			selected[count]=i;
			dfs(count+1);
		}
		
	}

	private static void isAnswer(int count) {
		tempArr= card.clone();
		for(int i=0; i<count; i++) {
			suffle(selected[i]);
		}
		if(isMatch())answer= answer > count ? count : answer;
	}

	static void swap(int a, int b) {
		int temp= tempArr[a];
		tempArr[a]=tempArr[b];
		tempArr[b]=temp;
	}
	
	static void suffle(int idx) {
		int mid=N/2-1;
		for(int i=0; i<idx; i++) {
			int start=Math.abs(mid-i);		//0
			for(int j=0; j<mid-start+1;j++) {
				swap(start+j*2, start+j*2+1);
			}
		}
	}
	
	static boolean isMatch() {
		for(int i=0; i<N-1; i++) {
			if(Math.abs(tempArr[i]-tempArr[i+1])!=1)return false;
		}
		return true;
	}

}
