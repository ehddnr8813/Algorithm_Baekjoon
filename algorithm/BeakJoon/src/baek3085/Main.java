package baek3085;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, answer=Integer.MIN_VALUE;
	static int dx[] =new int [] {1,0};
	static int dy[] =new int [] {0,1};
	static char map[][];
	static char temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		map= new char[N][N];
		for(int i=0; i<N; i++) {
			map[i]=br.readLine().toCharArray();
		}
		searchAll();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dfs(j,i);
			}
		}
		
		System.out.println(answer);
	}

	private static void dfs(int x, int y) {
		for(int k=0; k<2; k++) {
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx>=N || ny>=N)continue;
			swap(x, y, nx, ny);
			if(k==0) {
				switch1(x, y);
			}
			else {
				switch2(x, y);
			}
			swap(x, y, nx, ny);
		}
	}

	static int switch1(int x, int y) {
		int count=1;
		for(int j=0; j<N-1; j++) {
			if(map[y][j]==map[y][j+1])count++;
			else {
				answer = count > answer ? count : answer;
				count=1;
			}
		}
		answer = count > answer ? count : answer;
		for(int j=x; j<=x+1; j++) {
			count=1;
			for(int i=0; i<N-1; i++) {
				if(map[i][j]==map[i+1][j])count++;
				else {
					answer = count > answer ? count : answer;
					count=1;
				}
			}
			answer = count > answer ? count : answer;
		}
		return count;
	}
	
	static void searchAll() {
		for(int i=0; i<N; i++) {
			int count=1;
			for(int j=0; j<N-1; j++) {
				if(map[i][j]==map[i][j+1])count++;
				else {
					answer = count > answer ? count : answer;
					count=1;
				}
			}
			answer = count > answer ? count : answer;
		}
		for(int j=0; j<N; j++) {
			int count=1;
			for(int i=0; i<N-1; i++) {
				if(map[i][j]==map[i+1][j])count++;
				else {
					answer = count > answer ? count : answer;
					count=1;
				}
			}
			answer = count > answer ? count : answer;
		}
	}
	
	static int switch2(int x, int y) {
		int count=1;
		for(int i=0; i<N-1; i++) {
			if(map[i][x]==map[i+1][x])count++;
			else {
				answer = count > answer ? count : answer;
				count=1;
			}
		}
		answer = count > answer ? count : answer;
		for(int i=y; i<=y+1; i++) {
			count=1;
			for(int j=0; j<N-1; j++) {
				if(map[i][j]==map[i][j+1])count++;
				else {
					answer = count > answer ? count : answer;
					count=1;
				}
			}
			answer = count > answer ? count : answer;
		}
		return count;
	}

	
	static void swap(int x1, int y1, int x2, int y2) {
		temp=map[y1][x1];
		map[y1][x1]=map[y2][x2];
		map[y2][x2]=temp;
	}

	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
}
