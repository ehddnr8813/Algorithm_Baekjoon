package baek15684;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	//TODO: 연속적으로 못 놓게 하기 , goDown함수에 direction을 추가해서 지난 번에 0또는 1을 움직였는데 이번에 dir과 같으면 false return할 것.
	static int N,M,H,listSize, answer=4;
	static int map[][];
	static boolean visited[][];
	static int a,b;
	static ArrayList<Node> list;
	static boolean flag;
	
	static int dx[]= new int[] {1,-1,0};
	static int dy[]= new int[] {0,0,1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		H=Integer.parseInt(st.nextToken());
		H=H+2;
		N=2*N-1;
		map=new int[H][N];
		visited=new boolean[H][N];
		make();
		for(int i=0; i<M; i++) {
			st=new StringTokenizer(br.readLine());
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			map[a][2*b-1]=0;
			if(2*b-3>=0)map[a][2*b-3]=-2;
			if(2*b+1<N)map[a][2*b+1]=-2;
		}
		solve(1,1,0);
		if(answer==4)System.out.println(-1);
		else System.out.println(answer);
	}

	static void print() {
		for(int i=0; i<H; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}


	static void make() {
		int count=1;
		for(int i=0; i<N; i+=2) {
			map[0][i]=count;
			map[H-1][i]=count;
			count++;
		}
		for(int i=1; i<N; i+=2) {
			for(int j=0; j<H; j++) {
				map[j][i]=-1;
			}
		}
	}
	
	static void solve(int row, int col, int count) {
		if(answer<=count)return;
		if(check()) {
			answer=Math.min(answer, count);
			return; 
		}
		if(count>=3)return;
		if(row>=H-1)return;
		if(col>N-1) {
			solve(row+1,1, count);
			return;
		}
		if(map[row][col]==0) {
			solve(row, col+4, count);
		}
		else if(map[row][col]==-1) {
			map[row][col]=0;
			solve(row, col+4, count+1);
			map[row][col]=-1;
			solve(row, col+2, count);
		}
		else if(map[row][col]==-2) {
			solve(row, col+2, count);
		}
		
	}
	
	static boolean check() {
		for(int i=N-1; i>=0; i-=2) {
			flag=false;
			visited[1][i]=true;
			dfs(i,1,map[0][i]);
			visited[1][i]=false;
			if(!flag)return flag;
		}
		return flag;
	}
	

	static void dfs(int x, int y, int find) {
		for(int k=0; k<3; k++) {
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx<0 || nx>=N || visited[ny][nx] || map[ny][nx]<0)continue;
			if(map[ny][nx]>0) {
				if(map[ny][nx]==find)flag=true;
				return;
			}
			visited[ny][nx]=true;
			dfs(nx, ny, find);
			visited[ny][nx]=false;
			return;
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
