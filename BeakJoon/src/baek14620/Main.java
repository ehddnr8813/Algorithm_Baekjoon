package baek14620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static boolean[][] visited, visited2;
	static int[][] map;
	static int N, answer=Integer.MAX_VALUE;
	static ArrayList<Node> list = new ArrayList<>();
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		map= new int[N][N];
		visited= new boolean[N][N];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int count) {
		if(count==3) {
			visited2= new boolean[N][N];
			if(isPossible()){
				int total= getT();
				answer = answer > total ? total : answer;
			}
			return;
		}
		
		for(int i=1; i<N-1; i++) {
			for(int j=1; j<N-1; j++) {
				if(!visited[i][j]) {
					visited[i][j]=true;
					list.add(new Node(j, i));
					dfs(count+1);
					visited[i][j]=false;
					list.remove(list.size()-1);
				}
			}
		}
		
	}

	private static int getT() {
		int total=0;
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visited2[i][j]) {
					total+=map[i][j];
				}
			}
		}
		return total;
	}

	private static boolean isPossible() {
		for(int i=0; i<list.size(); i++) {
			Node n=list.get(i);
			int x=n.x;
			int y=n.y;
			visited2[y][x]=true;
			for(int k=0; k<4; k++) {
				int nx=x+dx[k];
				int ny=y+dy[k];
				if(nx<0 || ny<0 || nx>=N || ny>=N || visited2[ny][nx])return false;
				visited2[ny][nx]=true;
			}
		}
		return true;
	}
	
	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
