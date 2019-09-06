package sw5656;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N,W,H, answer;
	static int visited[];
	static int[][] map, temp;
	static Queue<Node>q ;
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			answer=Integer.MAX_VALUE;
			N=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			H=Integer.parseInt(st.nextToken());
			map= new int[H][W];
			visited= new int[N];

			for(int i=0; i<H; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			go(0);
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void go(int count) {
		if(count==N) {
			int total=simualte();
			answer= Math.min(total, answer);
			return;
		}
		for(int i=0; i<W; i++) {
			visited[count]=i;
			go(count+1);
			visited[count]=0;
		}
	}

	private static int simualte() {
		temp=new int[H][W];
		for(int i=0; i<H; i++) {
			temp[i]=map[i].clone();
		}
		for(int i=0; i<N; i++) {
			delete(visited[i]);
			goDown();
		}
		int count=0;
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(temp[i][j]!=0)count++;
			}
		}
		return count;
	}

	private static void goDown() {
		for(int j=0; j<W; j++) {
			for(int i=0; i<H; i++) {
				if(temp[i][j]==0) {
					for(int k=i; k>=1; k--) {
						temp[k][j]=temp[k-1][j];
					}
					temp[0][j]=0;
				}
			}
		}
	}

	private static void delete(int c) {		
		int r=findTop(c);
		if(r==-1)return;
		q= new LinkedList<Node>();
		q.add(new Node(c, r, temp[r][c]-1));
		temp[r][c]=0;
		while(!q.isEmpty()) {
			Node n=q.poll();

			for(int k=0; k<4; k++) {
				int nx=n.x;
				int ny=n.y;
				for(int i=0; i<n.count; i++) {
					nx+=dx[k];
					ny+=dy[k];
					if(nx<0 || ny<0 || nx>=W || ny>=H)break;
					if(temp[ny][nx]==0)continue;
					if(temp[ny][nx]>1)q.add(new Node(nx, ny, temp[ny][nx]-1));
					temp[ny][nx]=0;
				}
			}

		}
				
	}
	

	private static int findTop(int c) {
		for(int i=0; i<H; i++) {
			if(temp[i][c]!=0) {
				return i;
			}
		}
		return -1;
	}
	
	static class Node{
		int x, y, count;

		public Node(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.count=count;
		}
	}
	

}
