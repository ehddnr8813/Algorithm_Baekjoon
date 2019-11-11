package baek2931;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int R,C, sx, sy;
	static char map[][];
	static Queue<int[]> q= new LinkedList<>();
	static int[] answer= new int[2];
	static int arr[]= new int[3];
	static char answerP;
	static char pipes[] = { '|', '-', '+', '1', '2', '3', '4' };
	static boolean flag;
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map= new char[R][C];
		for(int i=0; i<R; i++) {
			map[i]=br.readLine().toCharArray();
		}
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				if(map[i][j]=='M') {
					sx=j;
					sy=i;
					q.add(new int []{sx, sy, -1});
				}
			}
		}
		answer=bfs();
		for(int i=0; i<7 ; i++) {
			map[answer[1]][answer[0]]=pipes[i];
			q.add(new int[] {sx, sy, -1});
			int result[]=bfs();
			if(result[0]==-1) {
				System.out.println((answer[1]+1)+" "+(answer[0]+1)+" "+pipes[i]);
				break;
			}
		}
		
	}

	

	private static int getDir(int dir, char pipe) {
		if(pipe=='|') {
			if(dir==1 || dir==3)return dir;
		}
		else if(pipe=='-') {
			if(dir==0 || dir==2)return dir;
		}
		else if(pipe=='+') {
			return dir;
		}
		else if(pipe=='1') {
			if(dir==3) {
				return 0;
			}
			else if(dir==2) {
				return 1;
			}
		}
		else if(pipe=='2') {
			if(dir==1) {
				return 0;
			}
			else if(dir==2) {
				return 3;
			}
		}
		else if(pipe=='3') {
			if(dir==0) {
				return 3;
			}
			else if(dir==1) {
				return 2;
			}
		}
		else if(pipe=='4') {
			if(dir==0) {
				return 1;
			}
			else if(dir==3) {
				return 2;
			}
		}
		return -1;
	}

	static int[] bfs() {

		while (!q.isEmpty()) {
			arr=q.poll();
			int x =arr[0];
			int y=arr[1];
			int dir=arr[2];

			if (map[y][x] == 'M') {	
				for (int i = 0; i < 4; i++) {
					int nx = x + dx[i];
					int ny = y + dy[i];
					if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;	
					if (map[ny][nx] != '.' && map[ny][nx]!='Z') {
						dir = getDir(i, map[ny][nx]);
						q.add(new int[] {nx,ny,dir});
						break;
					}
				}
			}
			else {
				int nx = x + dx[dir];
				int ny = y + dy[dir];
				if (nx < 0 || ny < 0 || nx >= C || ny >= R) continue;	  
				if (map[ny][nx] == '.') {
					return  new int[] {nx,ny};	
				}
				if (map[ny][nx] == 'Z') return new int[] {-1,-1};
				dir = getDir(dir, map[ny][nx]);
				if (dir == -1) return new int[] {nx,ny};
				q.add(new int[] {nx,ny, dir});
			}
		}
		return arr;
	}


}
