package sw2105;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int T,N, answer, startX, startY;
	static int map[][];
	static boolean visited[];
	
	static int dx[]= new int[]{-1,1,1,-1};
	static int dy[]= new int[]{1,1,-1,-1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			answer=-1;
			map= new int[N][N];
			visited= new boolean[101];
			for(int i=0; i<N; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					startX=j;
					startY=i;
					if(j-1>=0 && i+1<N) {
						visited[map[i+1][j-1]]=true;
						dfs(j-1,i+1,0,1);
						visited[map[i+1][j-1]]=false;
					}
				}
			}
			System.out.println("#"+t+" "+answer);
		}
	}

	static void dfs(int j, int i, int dir, int count) {
		if(startX==j && startY==i) {
			answer= answer < count ? count : answer;
			return;
		}
		for(int k=0; k<2; k++) {
			if(dir+k>=4)continue;
			int nx=j+dx[dir+k];
			int ny=i+dy[dir+k];
			if(nx<0 || ny<0 || nx>=N || ny>=N)continue;
			if(!visited[map[ny][nx]]) {
				visited[map[ny][nx]]=true;
				dfs(nx, ny, dir+k, count+1);
				visited[map[ny][nx]]=false;	
			}
		}
	}

}
