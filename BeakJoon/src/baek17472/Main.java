package baek17472;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M,count=1, answer=Integer.MAX_VALUE;
	static int[][] map, temp, distance;
	static boolean[][] visited;
	static boolean[] bridgeV, islandV;
	static ArrayList<Node> list[];
	static ArrayList<Bridge> bridge= new ArrayList<>();
	static Queue<Node> q;
	
	static int[] dx= new int[] {1,0,-1,0};
	static int[] dy= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		temp= new int[N][M];
		visited= new boolean[N][M];
		list= new ArrayList[7];
		
		for(int i=0; i<7; i++) {
			list[i]= new ArrayList<>();
		}
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(map[i][j]==1 && !visited[i][j]) {
					temp[i][j]=count;
					list[count].add(new Node(j, i, 0));
					make(j,i, list[count], count);
					count++;
				}
			}
		}
		distance= new int[count][count];
		for(int i=0; i<count; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		for(int i=1; i<=count-1; i++) {
			bridge(i);
		}
		makeBList();
		bridgeV= new boolean[bridge.size()];
		dfs(0,0,0, new ArrayList<Bridge>());
		if(answer==Integer.MAX_VALUE)System.out.println(-1);
		else System.out.println(answer);
	}

	private static void dfs(int index, int c, int total, ArrayList<Bridge> list) {
		if(c==count-2) {
			islandV= new boolean[count];
			boolean flag=true;
			isPossible();
			if(flag) {
				answer = answer > total ? total : answer;
			}
			return;
		}
		for(int i=index; i<bridge.size(); i++) {
			if(!bridgeV[i]) {
				Bridge b=bridge.get(i);
				bridgeV[i]=true;
				list.add(b);
				dfs(i+1, c+1, total+b.dis, list);
				list.remove(list.size()-1);
				bridgeV[i]=false;

			}
		}
	}

	private static void isPossible() {
		// TODO Auto-generated method stub
		
	}

	private static void bridge(int idx) {
		for(int i=0; i<list[idx].size(); i++) {
			Node n=list[idx].get(i);
			q= new LinkedList<>();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];
				if(nx<0 || ny<0 || nx>=M || ny>=N || temp[ny][nx]!=0)continue;
				q.add(new Node(nx, ny, 1, k));
				bfs(idx);
			}
		}
	}
	
	private static void makeBList(){
		for(int i=1; i<count-1; i++) {
			for(int j=i+1; j<count; j++) {
				if(distance[i][j]!=Integer.MAX_VALUE)bridge.add(new Bridge(i, j, distance[i][j]));
			}
		}
	}

	private static void bfs(int idx) {
		while(!q.isEmpty()) {
			Node n=q.poll();
			int nx=n.x+dx[n.dir];
			int ny=n.y+dy[n.dir];
			if(nx<0 || ny<0 || nx>=M || ny>=N  || temp[ny][nx]==idx)continue;
			if(temp[ny][nx]!=0 && n.d!=1) {
				int origin=distance[idx][temp[ny][nx]];
				if(origin > n.d) {
					distance[temp[ny][nx]][idx]=n.d;
					distance[idx][temp[ny][nx]]=n.d;
				}
			}
			if(temp[ny][nx]==0)q.add(new Node(nx, ny, n.d+1, n.dir));
		}
		
	}

	private static void make(int x, int y, ArrayList<Node> list, int number) {
		for(int k=0; k<4; k++) {
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx] || map[ny][nx]==0)continue;
			temp[ny][nx]=number;
			visited[ny][nx]=true;
			list.add(new Node(nx, ny, 0));
			make(nx, ny, list, number);
		}
	}
	
	static void print() {
		for(int i=0; i<count; i++) {
			for(int j=0; j<count; j++) {
				System.out.print(distance[i][j]+" ");
			}
			System.out.println();
		}
	}

	static class Node{
		int x;
		int y;
		int d;
		int dir;
		public Node(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}
		public Node(int x, int y, int d, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
			this.dir = dir;
		}
		
	}
	
	static class Bridge{
		int b;
		int f;
		int dis;
		public Bridge(int b, int f, int dis) {
			super();
			this.b = b;
			this.f = f;
			this.dis = dis;
		}
		
	}

}
