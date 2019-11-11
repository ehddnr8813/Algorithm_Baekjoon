package baek17779;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, total,f,s,t,fo,l, answer=Integer.MAX_VALUE;
	static int[][] map;
	static Queue<Node> q;
	
	static int[][] visited;
	static boolean[] visitDia, visitAnti;
	
	static Node first, second, third, fourth;
	
	static int dx[]= new int[] {1,-1};
	static int dy[]= new int[] {1,1};
	
	static int dx2[]= new int[] {1,0,-1,0};
	static int dy2[]= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		map= new int[N][N];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				total+=map[i][j];
			}
		}
		visitDia= new boolean[2*N];
		visitAnti= new boolean[2*N];
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				first= new Node(j,i);
				findRight(j,i);
			}
		}
		System.out.println(answer);
	}

	private static void findRight(int x, int y) {
		int nx=x;
		int ny=y;
		while(true) {
			nx+=dx[0];
			ny+=dy[0];
			if(nx>=N || ny>=N)break;
			visitAnti[nx+ny]=true;
			second= new Node(nx, ny);
			findLeft(x, y);
			visitAnti[nx+ny]=false;
		}
	}
	
	private static void makeMid() {
		int x=first.x;
		int y=first.y;
		for(int i=first.x; i<second.x; i++) {
			visited[y][x]=5;
			x+=dx[0];
			y+=dy[0];
		}
		x=first.x;
		y=first.y;
		for(int i=first.y; i<third.y; i++) {
			visited[y][x]=5;
			x+=dx[1];
			y+=dy[1];
		}
		x=third.x;
		y=third.y;
		for(int i=third.x; i<=fourth.x; i++) {
			visited[y][x]=5;
			x+=dx[0];
			y+=dy[0];
		}
		x=second.x;
		y=second.y;
		for(int i=second.y; i<fourth.y; i++) {
			visited[y][x]=5;
			x+=dx[1];
			y+=dy[1];
		}
	}
	
	private static void findLeft(int x, int y) {
		int nx=x;
		int ny=y;
		while(true) {
			nx+=dx[1];
			ny+=dy[1];
			if(nx<0 || ny>=N)break;
			visitDia[N-ny+nx]=true;
			third= new Node(nx, ny);
			findLast();
			visitDia[N-ny+nx]=false;
		}
		
	}
	
	static void findLast() {
loop:	for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visitAnti[j+i] && visitDia[N-i+j] && !(i==first.y && j==first.x)) {
					fourth= new Node(j, i);
					break loop;
				}
			}
		}
		if(fourth==null)return;
		visited= new int[N][N];
		makeMid();
		f=0;s=0;t=0;fo=0;l=0;
		getFirst();
		getSecond();
		getThird();
		getFourth();
		getLast();
		fourth=null;
	}

	private static void getLast() {
		l=total-f-s-t-fo;
		int min=Math.min(f, Math.min(s, Math.min(t, Math.min(fo, l))));
		int max=Math.max(f, Math.max(s, Math.max(t, Math.max(fo, l))));
		answer = answer > max-min ? max-min : answer;
	}

	private static void getFourth() {
		q= new LinkedList<Node>();
		q.add(new Node(N-1, N-1));
		visited[N-1][N-1]=4;
		fo+=map[N-1][N-1];
		while(!q.isEmpty()) {
			Node n=q.poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx2[k];
				int ny=n.y+dy2[k];
				if(nx<fourth.x || ny<=second.y || nx>=N || ny>=N || visited[ny][nx]>=1)continue;
				fo+=map[ny][nx];
				visited[ny][nx]=4;
				q.add(new Node(nx, ny));
			}
		}
	}

	private static void getThird() {
		q= new LinkedList<Node>();
		q.add(new Node(0, N-1));
		visited[N-1][0]=3;
		t+=map[N-1][0];
		while(!q.isEmpty()) {
			Node n=q.poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx2[k];
				int ny=n.y+dy2[k];
				if(nx<0 || ny<third.y || nx>=fourth.x || ny>=N || visited[ny][nx]>=1 )continue;
				t+=map[ny][nx];
				visited[ny][nx]=3;
				q.add(new Node(nx, ny));
			}
		}
	}

	private static void getSecond() {
		q= new LinkedList<Node>();
		q.add(new Node(N-1, 0));
		visited[0][N-1]=2;
		s+=map[0][N-1];
		while(!q.isEmpty()) {
			Node n=q.poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx2[k];
				int ny=n.y+dy2[k];
				if(nx<=first.x || ny<0 || nx>=N || ny>second.y || visited[ny][nx]>=1)continue;
				s+=map[ny][nx];
				visited[ny][nx]=2;
				q.add(new Node(nx, ny));
			}
		}
	}

	private static void getFirst() {
		q= new LinkedList<Node>();
		q.add(new Node(0, 0));
		visited[0][0]=1;
		f+=map[0][0];
		while(!q.isEmpty()) {
			Node n=q.poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx2[k];
				int ny=n.y+dy2[k];
				if(nx<0 || ny<0 || nx>first.x || ny>=third.y ||visited[ny][nx]>=1)continue;
				f+=map[ny][nx];
				visited[ny][nx]=1;
				q.add(new Node(nx, ny));
			}
		}
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
