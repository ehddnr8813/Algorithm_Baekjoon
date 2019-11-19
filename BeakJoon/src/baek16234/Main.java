package baek16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int N,L,R, count, time, nation, total;
	static int map[][];
	static int visited[][];
	static int arr[]= new int[2];
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	static ArrayList<int []> list;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());	
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		nation=N*N;
		map= new int[N][N];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		while(true) {
			count=0;
			visited= new int[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(visited[i][j]==0) {
						count++;
						list = new ArrayList<int []>();
						total=0;
						visited[i][j]=count;
						total+=map[i][j];
						list.add(new int[] {j,i});
						dfs(j,i,count, map[i][j]);
						merge();
					}
				}
			}
			if(count==nation)break;
			time++;
		}
		System.out.println(time);
	}

	static void dfs(int j, int i, int count, int before) {
		for(int k=0; k<4; k++) {
			int nx=j+dx[k];
			int ny=i+dy[k];
			if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx]!=0)continue;
			int diff=Math.abs(before-map[ny][nx]);
			if(diff>=L && diff<=R) {
				visited[ny][nx]=count;
				total+=map[ny][nx];
				list.add(new int[] {nx, ny});
				dfs(nx, ny, count, map[ny][nx]);
			}
		}
	}
	
	static void merge() {
		int size=list.size();
		for(int i=0; i<size; i++) {
			arr=list.get(i);
			map[arr[1]][arr[0]]=total/size;
		}
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println("================");
	}
	

}
