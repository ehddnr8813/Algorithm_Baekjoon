package baek5427;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T,w,h, startX, startY, answer=Integer.MAX_VALUE;
	static char map[][];
	static boolean visited[][];
	static Queue<Node> fire;
	static Queue<Node> runner;
	
	static int dx[]= new int []{1,0,-1,0};
	static int dy[]= new int []{0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			w=Integer.parseInt(st.nextToken());
			h=Integer.parseInt(st.nextToken());
			answer=Integer.MAX_VALUE;
			map=new char[h][w];
			visited= new boolean[h][w];
			fire=new LinkedList<Node>();
			runner= new LinkedList<Node>();
			for(int i=0; i<h; i++) {
				st=new StringTokenizer(br.readLine());
				String s=st.nextToken();
				for(int j=0; j<w; j++) {
					map[i][j]=s.charAt(j);
					if(map[i][j]=='*') {
						fire.add(new Node(j, i));
					}
					else if(map[i][j]=='@') {
						runner.add(new Node(j,i,1));
					}
				}
			}
			bfs();
			if(answer==Integer.MAX_VALUE)System.out.println("IMPOSSIBLE");
			else System.out.println(answer);
		}
	}

	private static void bfs() {
		while(!runner.isEmpty()) {	
			int size=fire.size();
			for(int i=0; i<size; i++) {
				Node n=fire.poll();
				for(int k=0; k<4; k++) {
					int nx=n.x+dx[k];
					int ny=n.y+dy[k];
					if(nx<0 || ny<0 || nx>=w || ny>=h || map[ny][nx]=='#' || map[ny][nx]=='*')continue;
					map[ny][nx]='*';
					fire.add(new Node(nx, ny));
				}
			}
			size=runner.size();
			for(int i=0; i<size; i++) {
				Node n=runner.poll();
				if(n.x==0 || n.x==w-1 || n.y==0 || n.y==h-1) {
					answer=n.count;
					return;
				}
				for(int k=0; k<4; k++) {
					int nx=n.x+dx[k];
					int ny=n.y+dy[k];
					if(nx<0 || ny<0 || nx>=w || ny>=h || visited[ny][nx] || map[ny][nx]=='#' || map[ny][nx]=='*')continue;
					visited[ny][nx]=true;
					runner.add(new Node(nx, ny, n.count+1));
				}
			}
		}
		
	}

	static class Node{
		int x;
		int y;
		int count;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
		public Node(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count=count;
		}
	}
	
}
