package baek16920;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node implements Comparable<Node>{
		int x;
		int y;
		int color;
		
		
		public Node(int x, int y, int color) {
			super();
			this.x = x;
			this.y = y;
			this.color = color;
		}
		@Override
		public int compareTo(Node o) {
			return color-o.color;
		}
		
	}
	
	static int N,M,P;
	static int player[];
	static int count[];
	static int map[][];
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	static Queue<Node> q[];
	static boolean flag;

	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		P=Integer.parseInt(st.nextToken());
		map=new int[N][M];
		player= new int[P+1];
		count= new int[P+1];
		q= new LinkedList[P+1];
		for(int i=1; i<=P; i++) {
			q[i]=new LinkedList<Node>();
		}
		
		st= new StringTokenizer(br.readLine());
		for(int i=1; i<=P; i++) {
			player[i]=Integer.parseInt(st.nextToken());
		}
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			String s=st.nextToken();
			for(int j=0; j<M; j++) {
				char c= s.charAt(j);
				if(c!='.') {
					map[i][j]=c-'0';
					if(map[i][j]>0) {
						count[map[i][j]]++;
						q[map[i][j]].add(new Node(j, i, map[i][j]));
					}
					
				}
			}
		}

		bfs();
		for(int i=1; i<=P; i++) {
			System.out.print(count[i]+" ");
		}
	}

	private static void bfs() {
		while(!flag) {
			int cnt=0;
			for(int i=1; i<=P; i++) {
				if(q[i].size()==0)cnt++;
			}
			if(cnt==P) {
				flag=true;
				break;
			}
			
			
			for(int i=1; i<=P; i++) {
				for(int j=0; j<player[i]; j++) {
					int qSize=q[i].size();
					if(qSize==0)break;		//빼고 제출해볼것
					while(qSize-->0) {
						Node n=q[i].poll();
						for(int k=0; k<4; k++) {
							int nx=n.x+dx[k];
							int ny=n.y+dy[k];
							if(nx<0 || ny<0 || nx>=M || ny>=N || map[ny][nx]!=0)continue;
							map[ny][nx]=n.color;
							count[n.color]++;
							q[i].add(new Node(nx, ny, n.color));
						}
					}
				}
			}
		}
	}
}
