package baek2798;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer=-1;
	static boolean[] visited;
	static int[] card;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		card=new int[N];
		visited= new boolean[N];
		st= new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++)card[i]=Integer.parseInt(st.nextToken());
		dfs(0,0,0);
		System.out.println(answer);
	}

	private static void dfs(int idx, int count, int total) {
		if(total>M)return;
		if(count==3) {
			answer=Math.max(total, answer);
			return;
		}
		for(int i=idx; i<N; i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(i+1, count+1, total+card[i]);
				visited[i]=false;
			}
		}
	}

}
