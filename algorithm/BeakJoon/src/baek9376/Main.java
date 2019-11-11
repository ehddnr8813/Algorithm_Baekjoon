package baek9376;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int T,w,h,answer;
	static char[][] map, temp;
	static int[][] visited, prisoner1, prisoner2, helper;
	static Queue<Node> q[]= new Queue[3];
	
	static int dx[]=new int[] {1,0,-1,0};
	static int dy[]=new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		while(T-->0) {
			st= new StringTokenizer(br.readLine());
			h=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			answer= Integer.MAX_VALUE;
			map= new char[h+2][w+2];
			prisoner1= new int[h+2][w+2];
			prisoner2= new int[h+2][w+2];
			helper= new int[h+2][w+2];
			for(int i=0; i<3; i++) q[i]= new LinkedList<Node>();
			init();
			int c=0;
			for(int i=1; i<=h; i++) {
				st=new StringTokenizer(br.readLine());
				String s=st.nextToken();
				for(int j=1; j<=w; j++) {
					map[i][j]=s.charAt(j-1);
					if(map[i][j]=='$') {
						q[c++].add(new Node(j, i, 0));
					}
				}
			}
			q[c].add(new Node(0, 0, 0));
			prisoner1=bfs(0);	
			prisoner2=bfs(1);	
			helper=bfs(2);
			visited= new int[h+2][w+2];
			for(int i=1; i<=h; i++) {
				for(int j=1; j<=w; j++) {
					if(map[i][j]=='*')continue;
					visited[i][j]=prisoner1[i][j]+prisoner2[i][j]+helper[i][j];
					if(map[i][j]=='#')visited[i][j]-=2;
					answer = answer > visited[i][j] ? visited[i][j] : answer;
				}
			}
			System.out.println(answer);
		}
	}
	
	private static int[][] bfs(int index) {
		temp= new char[h+2][w+2];
		visited= new int[h+2][w+2];
		for(int i=0; i<h+2; i++) {
			Arrays.fill(visited[i], -1);
		}
		Node n=q[index].peek();
		visited[n.y][n.x]=0;
		while(!q[index].isEmpty()) {
			n=q[index].poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];
				if(nx<0 || ny<0 || nx>=w+2 || ny>=h+2 || map[ny][nx]=='*')continue;
				if(map[ny][nx]=='#') {
					if(visited[ny][nx]==-1 || visited[ny][nx]>n.count+1) {
						visited[ny][nx]=n.count+1;
						q[index].add(new Node(nx, ny, n.count+1));
					}
				}
				else if(map[ny][nx]=='$' || map[ny][nx]=='.') {
					if(visited[ny][nx]==-1 || visited[ny][nx]>n.count) {
						visited[ny][nx]=n.count;
						q[index].add(new Node(nx, ny, n.count));
					}
				}
			}
		}
		return visited;
	}
	

	private static void init() {
		for(int i=0; i<w+2;i++) {
			map[0][i]='.';
			map[h+1][i]='.';
		}
		for(int i=0; i<h+2; i++) {
			map[i][0]='.';
			map[i][w+1]='.';
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
