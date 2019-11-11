package sw1953;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N,M,R,C,L,answer;
	static int map[][];
	static boolean visited[][];
	static Queue<Node> q;
	
	static int[] dx= new int[] {1,0,-1,0};
	static int[] dy= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			R=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			L=Integer.parseInt(st.nextToken());
			map= new int[N][M];
			visited= new boolean[N][M];
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			
			q= new LinkedList<>();
			visited[R][C]=true;
			answer=0;
			q.add(new Node(C, R, 1));
			bfs();
			findVisit();
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void findVisit() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(visited[i][j])answer++;
			}
		}
		
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			Node n=q.poll();
			if(n.t==L)return;
			int y=n.y;
			int x=n.x;
			int t=n.t;
			if(map[y][x]==1) {
				int nx=x+dx[0];
				int ny=y+dy[0];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[1];
				ny=y+dy[1];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[2];
				ny=y+dy[2];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[3];
				ny=y+dy[3];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==2) {
				int nx=x+dx[1];
				int ny=y+dy[1];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[3];
				ny=y+dy[3];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==3) {
				int nx=x+dx[0];
				int ny=y+dy[0];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[2];
				ny=y+dy[2];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==4) {
				int nx=x+dx[0];
				int ny=y+dy[0];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[3];
				ny=y+dy[3];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==5) {
				int nx=x+dx[0];
				int ny=y+dy[0];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==6 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[1];
				ny=y+dy[1];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==6) {
				int nx=x+dx[2];
				int ny=y+dy[2];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[1];
				ny=y+dy[1];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==4 || map[ny][nx]==7)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
			else if(map[y][x]==7) {
				int nx=x+dx[3];
				int ny=y+dy[3];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==2 || map[ny][nx]==5 || map[ny][nx]==6)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
				nx=x+dx[2];
				ny=y+dy[2];
				if(nx>=0 && ny>=0 && nx<M && ny<N && !visited[ny][nx]&&(map[ny][nx]==1 || map[ny][nx]==3 || map[ny][nx]==4 || map[ny][nx]==5)) {
					visited[ny][nx]=true;
					q.add(new Node(nx, ny, t+1));
				}
			}
		}
		
	}

	static class Node{
		int x;
		int y;
		int t;
		public Node(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t= t;
		}
		
	}
}
