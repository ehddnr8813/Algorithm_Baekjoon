package baek2573;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N,M, count;
	static int map[][], tempMap[][];
	static boolean visited[][];
	static ArrayList<Node> ice= new ArrayList<>();
	static ArrayList<Node> newIce;
	static boolean flag=true;
	
	static int dx[]= new int[] {1,-1,0,0};
	static int dy[]= new int[] {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map= new int[N][M];
		tempMap= new int[N][M];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]!=0)ice.add(new Node(j, i));
			}
		}
		while(ice.size()>1 && flag) {
			count++;
			visited= new boolean[N][M];
			bfs();
			if(ice.size()==0)break;
			Node n=ice.get(0);				//이럴 거 꺼낼때 항상 로직 확인하기
			dfs(n.x, n.y);
			check();
		}
		if(!flag && ice.size()>=2)System.out.println(count);
		else System.out.println(0);
	}
	
	private static void check() {
		for(int i=0; i<ice.size(); i++) {
			Node n=ice.get(i);
			if(!visited[n.y][n.x]){
				flag=false;
				break;
			}
		}
	}

	private static void bfs() {
		newIce= new ArrayList<Node>();
		for(int i=0; i<N; i++) {
			tempMap[i]=map[i].clone();
		}
		for(int i=0; i<ice.size(); i++) {
			Node n= ice.get(i);
			for(int k=0; k<4; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];
				if(nx<0 || ny<0 || nx>=M || ny>=N || map[ny][nx]!=0) continue;
				tempMap[n.y][n.x]--;
				if(tempMap[n.y][n.x]==0)break;
			}
			if(tempMap[n.y][n.x]!=0)newIce.add(n);
		}
		ice=newIce;
		for(int i=0; i<N; i++) {
			map[i]=tempMap[i].clone();
		}
	}
	
	private static void dfs(int x, int y) {
		 visited[y][x]=true;
			for(int k=0; k<4; k++) {
				int nx=x+dx[k];
				int ny=y+dy[k];
				if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx] || map[ny][nx]==0) continue;
				dfs(nx, ny);
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
