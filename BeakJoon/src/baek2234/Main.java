package baek2234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static class Node{
		int x;
		int y;
		int dir;
		
		public Node(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}
	
	static int N,M, num, maxSize=Integer.MIN_VALUE, castleCount, maxSize2=Integer.MIN_VALUE;
	static boolean map[][][];
	static boolean visited[][];
	static ArrayList<Node> list= new ArrayList<Node>();
	static Queue<int []> q= new LinkedList<int []>();
	static int arr[]= new int[3];
	
	static int dx[]= new int[] {-1,0,1,0};
	static int dy[]= new int[] {0,-1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map= new boolean[M][N][4];
		visited= new boolean[M][N];
		for(int i=0; i<M; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				num=Integer.parseInt(st.nextToken());
				for(int k=0; k<4; k++) {
					if(((num>>k) & 1)==1) {
						if(i==0 && k==1) continue;
						else if(j==0 && k==0)continue;
						map[i][j][k]=true;
						if(k==0 && j>=1 && map[i][j-1][2])continue;
						if(k==1 && i>=1 && map[i-1][j][3])continue;
						list.add(new Node(j, i, k));
						
					}
				}
			}
		}
		for(int i=0; i<M; i++) {
			for(int j=0; j<N; j++) {	
				if(!visited[i][j]) {
					castleCount++;
					visited[i][j]=true;
					q.add(new int[] {j,i});
					int ret=sol1();
					maxSize = maxSize < ret ? ret : maxSize;
				}
			}
		}
		sol2();
		System.out.println(castleCount);
		System.out.println(maxSize);
		System.out.println(maxSize2);
	}

	private static void sol2() {
		for(int k=0; k<list.size(); k++) {
			Node n=list.get(k);
			map[n.y][n.x][n.dir]=false;
			visited= new boolean[M][N];
			for(int i=0; i<M; i++) {
				for(int j=0; j<N; j++) {	
					if(!visited[i][j]) {
						visited[i][j]=true;
						q.add(new int[] {j,i});
						int ret=sol1();
						maxSize2 = maxSize2 < ret ? ret : maxSize2;
					}
				}
			}
			map[n.y][n.x][n.dir]=true;
		}
	}

	private static int sol1() {
		int cur=1;
		while(!q.isEmpty()) {
			arr=q.poll();
			int x=arr[0];
			int y=arr[1];

			for(int k=0; k<4; k++) {
				if(map[y][x][k])continue;
				int nx=x+dx[k];
				int ny=y+dy[k];
				if(nx<0 || ny<0 || nx>=N || ny>=M || visited[ny][nx])continue;
				visited[ny][nx]=true;
				q.add(new int[] {nx, ny});
				cur++;
			}
		}
		return cur;
	}

}
