package baek16198;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, answer=Integer.MIN_VALUE, before, after;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		arr= new int[N];
		st= new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		dfs(0,0, 0);
		System.out.println(answer);
	}

	private static void dfs(int count, int visited, int total) {
		if(count==N-2) {
			answer = total > answer ? total : answer;
			return;
		}
		
		for(int i=1; i<N-1; i++) {
			if((visited & 1<<i)==0) {
				int temp=arr[i];
				arr[i]=0;
				before=findBefore(i);
				after=findAfter(i);
				dfs(count+1, (visited | 1<<i), total+before*after);
				arr[i]=temp;
			}
		}
	}

	private static int findAfter(int i) {
		while(i++<N) {
			if(arr[i]!=0)return arr[i];
		}
		return 0;
		
	}

	private static int findBefore(int i) {
		while(i-->0) {
			if(arr[i]!=0)return arr[i];
		}
		return 0;
		
	}

}
