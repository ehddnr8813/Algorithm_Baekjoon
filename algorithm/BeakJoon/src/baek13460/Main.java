package baek13460;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N,M, startBx, startBy, startRx, startRy, bx, by, rx, ry, count , answer=Integer.MAX_VALUE;
	static char map[][];
	static boolean visited[][][][];
	static int arr[]= new int[5];
	static int arrB[]= new int[2];
	static int arrR[]= new int[2];
	static Queue<int []> q = new LinkedList<int[]>();
	static boolean isNext;
	
	static int dx[]=new int[] {1,0,-1,0};
	static int dy[]=new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map= new char[N][M];
		visited= new boolean[M][N][M][N];			//0 : blue  ,  1:red
		for(int i=0; i<N; i++) {
			char c[]=br.readLine().toCharArray();
			for(int j=0; j<M; j++) {
				map[i][j]=c[j];
				if(map[i][j]=='B') {
					startBx=j;
					startBy=i;
				}
				else if(map[i][j]=='R') {
					startRx=j;
					startRy=i;
				}
			}
		}
		visited[startBx][startBy][startRx][startRy]=true;
		q.add(new int[] {startBx, startBy, startRx, startRy, 0});
		bfs();
		if(answer!=Integer.MAX_VALUE)System.out.println(answer);
		else System.out.println(-1);
	}
	
	private static void bfs() {
		while(!q.isEmpty()) {
			arr=q.poll();
			bx=arr[0];
			by=arr[1];
			rx=arr[2];
			ry=arr[3];
			count=arr[4];
			if(count+1==answer || count+1>10)continue;
			for(int k=0; k<4; k++) {
				arrB=moveEnd(bx, by, k);
				if(map[arrB[1]][arrB[0]]=='O')continue;
				arrR=moveEnd(rx, ry, k);
				if(arrB[0]==arrR[0] && arrB[1]==arrR[1]) {
					if(k==0) {
						if(rx>bx) {
							arrB[0]--;
						}
						else {
							arrR[0]--;
						}
					}
					else if(k==1) {
						if(ry>by) {
							arrB[1]--;
						}
						else {
							arrR[1]--;
						}
					}
					else if(k==2) {
						if(rx>bx) {
							arrR[0]++;
						}
						else {
							arrB[0]++;
						}
					}
					else {
						if(ry>by) {
							arrR[1]++;
						}
						else {
							arrB[1]++;
						}
					}
				}
				if(visited[arrB[0]][arrB[1]][arrR[0]][arrR[1]])continue;
				if(map[arrR[1]][arrR[0]]=='O') {
					answer = answer > count+1 ? count+1 : answer;
					continue;
				}
				visited[arrB[0]][arrB[1]][arrR[0]][arrR[1]]=true;
				q.add(new int[] {arrB[0], arrB[1], arrR[0], arrR[1], count+1});
			}
		}
		
	}

	static int [] moveEnd(int x, int y, int dir) {
		int nx=x;
		int ny=y;
		while(true) {
			nx+=dx[dir];
			ny+=dy[dir];
			if(map[ny][nx]=='#') {
				nx-=dx[dir];
				ny-=dy[dir];
				break;
			}
			if(map[ny][nx]=='O')break;
		}
		return new int[] {nx,ny};
	}
	
}
