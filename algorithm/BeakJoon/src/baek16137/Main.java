package baek16137;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M, nx, ny, answer=Integer.MAX_VALUE, count1, count2;
	static int map[][];
	static Node n;
	static boolean visited[][][][];
	static Queue<Node> q= new LinkedList<Node>();
	
	static int dx[]= new int [] {1,0,-1,0};
	static int dy[]= new int [] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		visited=new boolean[2][400][N][N];			
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		q.add(new Node(0, 0, 0, 0));
		cross();
		bfs();
		System.out.println(answer);
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			n=q.poll();
			if(n.x==N-1 && n.y==N-1) {
				answer=n.count;
				return;
			}
			for(int k=0; k<4; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];

				if(isRange(nx, ny)) {
					if(map[ny][nx]==1 && !visited[n.used][n.count+1][ny][nx]) {
						visited[n.used][n.count+1][ny][nx]=true;
						q.add(new Node(nx, ny, n.count+1, n.used));
					}
					else if(map[ny][nx]==0 && (n.count+1)%M==0 && n.used==0 && map[n.y][n.x]==1 && !visited[1][n.count+1][ny][nx]) {
						visited[1][n.count+1][ny][nx]=true;
						q.add(new Node(nx, ny, n.count+1, 1));
					}
					else if((n.count+1)%map[ny][nx]==0 && map[n.y][n.x]==1 && !visited[n.used][n.count+1][ny][nx] ) {			//map[ny][nx]>=2 없어도 될듯
						visited[n.used][n.count+1][ny][nx]=true;
						q.add(new Node(nx, ny, n.count+1, n.used));
					}
	
				}
			}
			if(!visited[n.used][n.count+1][n.y][n.x] && map[n.y][n.x]==1) {
				visited[n.used][n.count+1][n.y][n.x]=true;
				q.add(new Node(n.x, n.y, n.count+1, n.used));
			}
		}
	}

	private static void cross() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]==0) {
					count1=0; count2=0;
					if(isRange(j+dx[0], i+dy[0]) && (map[i+dy[0]][j+dx[0]]==0 || map[i+dy[0]][j+dx[0]]==-1))count1++;
					if(isRange(j+dx[2], i+dy[2]) && (map[i+dy[2]][j+dx[2]]==0 || map[i+dy[2]][j+dx[2]]==-1))count1++;
					if(isRange(j+dx[1], i+dy[1]) && (map[i+dy[1]][j+dx[1]]==0 || map[i+dy[1]][j+dx[1]]==-1))count2++;
					if(isRange(j+dx[3], i+dy[3]) && (map[i+dy[3]][j+dx[3]]==0 || map[i+dy[3]][j+dx[3]]==-1))count2++;
					if(count1>=1 && count2>=1)map[i][j]=-1;
				}
			}
		}
		
	}
	
	public static boolean isRange(int x, int y) {
		return (x>=0 && y>=0 && x<N && y<N) ? true : false;
	}

	static class Node{
		int x;
		int y;
		int count;
		int used;
		
		public Node(int x, int y, int count, int used) {
			super();
			this.x = x;
			this.y = y;
			this.count=count;
			this.used=used;
		}
	}
}

