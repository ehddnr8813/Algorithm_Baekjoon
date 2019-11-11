package sw2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {

	static int N, count;
	static boolean flag;
	static int[] arr= new int[2];
	static int[][] map;
	static boolean[] visited;
	static Node stairs[]= new Node[2];
	static ArrayList<int []> people;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		map= new int[N][N];
		people= new ArrayList<>();
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					count++;
					people.add(new int[] {j,i});
				}
				if(!flag && map[i][j]>=2) {
					stairs[0]= new Node(j, i, map[i][j]);
					flag=true;
				}
				if(flag && map[i][j]>=2) {
					stairs[1]= new Node(j, i, map[i][j]);
					flag=false;
				}
			}
		}
		visited = new boolean[count];
		dfs(0);
	}

	
	private static void dfs(int c) {
		simulate();
		if(c==count) {
			return;
		}
		for(int i=0; i<count; i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(c+1);
				visited[i]=false;
			}
		}
	}


	private static void simulate() {
		for(int i=0; i<count; i++) {
			if(visited[i]) {
				arr=people.get(i);
				int x=arr[0];
				int y=arr[1];
			}
			else {
				
			}
		}
	}
	
	private static int distance(int r1, int c1, int r2, int c2) {
		return Math.abs(r1-r2)+Math.abs(c1-c2);
	}


	static class Node{
		int x; int y;int t;
		PriorityQueue<Integer> q;
		public Node(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t= t;
			q= new PriorityQueue<>();
		}

	}
}
