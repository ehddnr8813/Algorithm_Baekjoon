package Solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class sw2115 {

	static int N, M, C, max, answer;
	static int[][] map;
	static boolean[][] visited;
	static boolean[] visited2;
	static ArrayList<Node> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			map= new int[N][N];
			visited= new boolean[N][N];
			list= new ArrayList<>();
			answer=0;
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			dfs(0,0,0);
			System.out.println("#"+t+" "+answer);
		}
	}
	
	static void dfs(int count, int x, int y) {
		if(count==2) {
			findMax();
			return;
		}
		for(int i=y; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(j+M<=N && before(i, j)) {
					if(j+M<=N) {
						for(int k=j; k<j+M; k++) {
							visited[i][k]=true;
						}
						list.add(new Node(j, i));
						dfs(count+1, j+M-1, i);
						for(int k=j; k<j+M; k++) {
							visited[i][k]=false;
						}
						list.remove(list.size()-1);
					}
				}
			}
		}
	}
	
	private static boolean before(int y, int x) {
		for(int j=x; j<x+M; j++) {
			if(visited[y][j])return false;
		}
		return true;
	}

	private static void findMax() {
		visited2 = new boolean[M];
		int newAns=0;
		for(int i=0; i<list.size(); i++) {
			max=Integer.MIN_VALUE;
			Node n= list.get(i);
			dfs2(0, 0, 0, n.x, n.y);
			newAns+=max;
		}
		answer = newAns > answer ? newAns : answer;
	}

	private static void dfs2(int index, int total, int honey, int x, int y) {
		max = total > max ? total: max;
		for(int i=index; i<M; i++) {
			if(!visited2[i] && honey+map[y][x+i]<=C) {
				visited2[i]=true;
				dfs2(index+1, total+(int) Math.pow(map[y][x+i], 2), honey+map[y][x+i],x, y);
				visited2[i]=false;
			}
		}
		
	}

	static class Node{
		int x, y;

		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		
	}

}
