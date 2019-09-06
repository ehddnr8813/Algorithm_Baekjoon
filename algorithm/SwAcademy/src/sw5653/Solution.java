package sw5653;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

import sw5653.Main.Node;

public class Solution {
	
	static int N, M, K, T;
	static int dx[]= new int[] {1,-1,0,0};
	static int dy[]= new int[] {0,0,1,-1};
	
	static int map[][];
	static boolean visited[][];
	static PriorityQueue<Node> pq;
	static Queue<Node> q;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++){
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			visited= new boolean[650][650];
			map= new int[650][650];
			pq= new PriorityQueue<>();
			q= new LinkedList<>();
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[300+i][300+j]=Integer.parseInt(st.nextToken());
					if(map[300+i][300+j]!=0) {
						visited[300+i][300+j]=true;
						q.add(new Node(300+j, 300+i, map[300+i][300+j], map[300+i][300+j], 2*map[300+i][300+j]));
					}
				}
			}
			bfs();
			System.out.println("#"+t+" "+q.size());
		}
	}
	
	private static void bfs() {
		while(K-->0) {
			int size=q.size();
			for(int i=0; i<size; i++) {
				Node n=q.poll();
				if(n.t!=0) {
					q.add(new Node(n.x, n.y, n.count, n.t-1, n.remain-1));
					continue;
				}
				for(int k=0; k<4; k++) {
					int nx=n.x+dx[k];
					int ny=n.y+dy[k];
					if(nx<0 || ny<0 || nx>=650 || ny>=650 || visited[ny][nx])continue;
					pq.add(new Node(nx, ny, n.count, n.count, n.count*2));
				}
				if(n.remain!=1) {
					q.add(new Node(n.x, n.y, n.count, n.t, n.remain-1));
				}
			}
			while(!pq.isEmpty()) {
				Node n=pq.poll();
				if(!visited[n.y][n.x]) {
					visited[n.y][n.x]=true;
					q.add(n);
				}
			}
		}
	}

	static class Node implements Comparable<Node>{
		int x,y;
		int count;
		int t;
		int remain;
		public Node(int x, int y, int count, int t, int remain) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.t = t;
			this.remain=remain;
		}
		@Override
		public int compareTo(Node o) {
			return -1*(this.count-o.count);
		}
	}
}

