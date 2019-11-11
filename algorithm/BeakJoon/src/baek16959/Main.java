package baek16959;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, answer=Integer.MAX_VALUE;
	static int map[][];
	static boolean visited[][][][];		//0 : 록 ,  1: 나이트,  2: 비숍
	
	static int knightX[]=new int[] {2,1,-1,-2,-2,-1,1,2};
	static int knightY[]=new int[] {1,2,2,1,-1,-2,-2,-1};
	static int rookX[]= new int[] {1,-1,0,0};
	static int rookY[]= new int[] {0,0,1,-1};
	static int bishopX[]= new int[] {1,-1,1,-1};
	static int bishopY[]= new int[] {1,1,-1,-1};
	static Queue<Node> q= new LinkedList<Node>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		map= new int[N][N];
		visited= new boolean[3][N*N+1][N][N];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					visited[0][1][0][0]=true;
					q.add(new Node(j, i, 0, 0, 2));
					visited[1][1][0][0]=true;
					q.add(new Node(j, i, 1, 0, 2));
					visited[2][1][0][0]=true;
					q.add(new Node(j, i, 2, 0, 2));
				}
			}
		}
		bfs();
		System.out.println(answer);
	}


	private static void bfs() {
		while(!q.isEmpty()) {
			Node n=q.poll();
			if(n.next==N*N+1) {
				answer=n.count;
				return;
			}
			for(int k=0; k<3; k++) {
				if(k==n.kind || visited[k][n.next][n.y][n.x])continue;
				visited[k][n.next][n.y][n.x]=true;
				q.add(new Node(n.x, n.y, k, n.count+1, n.next));
			}
			if(n.kind==1) {
				for(int k=0; k<8; k++) {
					int nx=n.x+knightX[k];
					int ny=n.y+knightY[k];
					if(nx<0 || ny<0 || nx>=N || ny>=N || visited[1][n.next][ny][nx])continue;
					visited[1][n.next][ny][nx]=true;
					if(map[ny][nx]==n.next)q.add(new Node(nx, ny, 1, n.count+1, n.next+1));
					else q.add(new Node(nx, ny, 1, n.count+1, n.next));
				}
			}
			else if(n.kind==2) {
				for(int k=0; k<4; k++) {
					int nx=n.x, ny=n.y;
					while(true) {
						nx+=bishopX[k];
						ny+=bishopY[k];
						if(nx<0 || ny<0 || nx>=N || ny>=N)break;
						if(visited[2][n.next][ny][nx])continue;
						visited[2][n.next][ny][nx]=true;
						if(map[ny][nx]==n.next)q.add(new Node(nx, ny, 2, n.count+1, n.next+1));
						else q.add(new Node(nx, ny, 2, n.count+1, n.next));
					}
				}
			}
			else {
				for(int k=0; k<4; k++) {
					int nx=n.x, ny=n.y;
					while(true) {
						nx+=rookX[k];
						ny+=rookY[k];
						if(nx<0 || ny<0 || nx>=N || ny>=N)break;
						if(visited[0][n.next][ny][nx])continue;
						visited[0][n.next][ny][nx]=true;
						if(map[ny][nx]==n.next)q.add(new Node(nx, ny, 0, n.count+1, n.next+1));
						else q.add(new Node(nx, ny, 0, n.count+1, n.next));
					}
				}
			}
		}
		
	}

	static class Node{
		int x, y;
		int kind;
		int count;
		int next;
		public Node(int x, int y, int kind, int count, int next) {
			super();
			this.x = x;
			this.y = y;
			this.kind = kind;
			this.count = count;
			this.next=next;
		}
	}
	
}
