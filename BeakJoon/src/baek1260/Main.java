package baek1260;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, V;
	static int[][] nodes;
	static int from, to;
	static boolean[] visited;
	
	static Queue<Integer> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		V=Integer.parseInt(st.nextToken());
		nodes= new int[N+1][N+1];
		visited= new boolean[N+1];

		for(int i=0; i<M; i++){
			st= new StringTokenizer(br.readLine());
			from= Integer.parseInt(st.nextToken());
			to= Integer.parseInt(st.nextToken());
			nodes[from][to]=1;
			nodes[to][from]=1;
		}
		
		dfs(V);
		System.out.println();
		bfs(V);
	}

	private static void bfs(int start) {
		visited= new boolean[N+1];
		visited[start]=true;
		System.out.print(start+" ");
		q= new LinkedList<Integer>();
		for(int i=1; i<=N; i++){
			if(nodes[start][i]==1)q.add(i);
		}
		
		while(!q.isEmpty()){
			int n= q.poll();
			if(visited[n])continue;
			visited[n]=true;
			System.out.print(n+" ");
			
			for(int i=1; i<=N; i++){
				if(nodes[n][i]==1 && !visited[i]){
					q.add(i);
				}
			}
		}
	}

	private static void dfs(int node) {
		visited[node]=true;
		System.out.print(node+" ");
		for(int i=1; i<=N; i++){
			if(nodes[node][i]==1 && !visited[i])dfs(i);
		}
	}

}
