package baek17086;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main {

	static int N,M, answer=Integer.MIN_VALUE;
	static int map[][];
	static boolean visited[][];
	static ArrayList<Node> list= new ArrayList<Node>();
	static ArrayList<Node> blank= new ArrayList<Node>();
	
	static int dx[]= new int[] {1,0,-1,-1,-1,0,1,1};
	static int dy[]= new int[] {1,1,1,0,-1,-1,-1,0};
	static Queue<Node> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==1) {
					list.add(new Node(j, i, 0));
				}
				else blank.add(new Node(j,i, 1));
			}
		}
		go();
		System.out.println(answer);
	}

	private static void go() {
		Node n;
		for(int i=0; i<blank.size(); i++) {
			n=blank.get(i);
			q=new LinkedList<>();
			visited= new boolean[N][M];
			q.add(n);
			bfs();
		}
	}

	private static void bfs(){
		Node n;
		while(!q.isEmpty()) {
			n=q.poll();
			for(int k=0; k<8; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];
				if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx])continue;
				if(map[ny][nx]==1) {
					answer = answer < n.count ? n.count : answer;
					return;
				}
				visited[ny][nx]=true;
				q.add(new Node(nx, ny, n.count+1));
			}
		}
	}
		
	static class Node{
		int x;
		int y;
		int count;
		public Node(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count=count;
		}
		
	}
}
