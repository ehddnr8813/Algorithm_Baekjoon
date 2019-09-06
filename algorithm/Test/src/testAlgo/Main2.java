package testAlgo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main2 {

	static int N, answer;
	static int map[][]= new int[4002][4002];
	static Queue<Node> atom, boom;
	
	static int dx[] = new int[] {0,0,-1,1};
	static int dy[]= new int[] {-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			atom= new LinkedList<Node>();
			answer=0;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				int x=(Integer.parseInt(st.nextToken())+1000)*2;
				int y=4000-(Integer.parseInt(st.nextToken())+1000)*2;
				int dir=Integer.parseInt(st.nextToken());
				int en=Integer.parseInt(st.nextToken());
				atom.add(new Node(x, y, dir, en));
				map[y][x]=en;
			}
			bfs();
			System.out.println("#"+t+" "+answer);
		}
	}
	
	private static void bfs() {
		while(!atom.isEmpty()) {
			Node n=atom.poll();
			if(map[n.y][n.x]!=n.en) {
				answer+=map[n.y][n.x];
				map[n.y][n.x]=0;
				continue;
			}
			int nx=n.x+dx[n.dir];
			int ny=n.y+dy[n.dir];
			if(nx<0 || ny<0 || nx>4000 || ny>4000)continue;
			if(map[ny][nx]==0) {
				map[ny][nx]=n.en;
				atom.add(new Node(nx, ny, n.dir, n.en));
			}
			else {
				map[ny][nx]+=n.en;
			}
			map[n.y][n.x]=0;
		}
	}

	static class Node{
		int x;
		int y;
		int dir;
		int en;
		public Node(int x, int y, int dir, int en) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
			this.en = en;
		}
	}

}
