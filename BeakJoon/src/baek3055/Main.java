package baek3055;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class Main {

	static int R,C, beaberX, beaberY, answer=Integer.MAX_VALUE, time;
	static char map[][];
	static boolean visited[][], flag;
	static boolean visitedB[][];
	static int arr[]= new int[2];
	
	static Queue<int []> hedgehog= new LinkedList<int []>();
	static Queue<int []> nextHed= new LinkedList<int []>();
	static Queue<int []> water= new LinkedList<int []>();
	static Queue<int []> nextWater= new LinkedList<int []>();
	
	static int dx[]= new int[] {1,-1,0,0};
	static int dy[]= new int[] {0,0,1,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		map= new char[R][C];
		visited= new boolean[R][C];
		visitedB= new boolean[R][C];
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			String s=st.nextToken();
			for(int j=0; j<C; j++) {
				map[i][j]=s.charAt(j);
				if(map[i][j]=='*') {
					visited[i][j]=true;
					nextWater.add(new int[] {j,i});
				}
				else if(map[i][j]=='S') {
					visitedB[i][j]=true;
					nextHed.add(new int[] {j,i});
				}
				else if(map[i][j]=='D') {
					beaberX=j;
					beaberY=i;
				}	
			}
		}
		go();
		if(answer!=Integer.MAX_VALUE)System.out.println(answer);
		else System.out.println("KAKTUS");
	}

	static void go() {
		while(!flag && !nextHed.isEmpty()) {
			hedgehog.addAll(nextHed);
			water.addAll(nextWater);
			nextHed.clear();
			nextWater.clear();
			bfs();
			if(flag)break;
			time++;
		}
		if(flag)answer=time;
	}

	static void bfs() {
		while(!water.isEmpty()) {
			arr=water.poll();
			int x=arr[0];
			int y=arr[1];
			for(int k=0; k<4; k++) {
				int nx=x+dx[k];
				int ny=y+dy[k];
				if(nx<0 || ny<0 || nx>=C || ny>=R || visited[ny][nx] || map[ny][nx]=='X' || map[ny][nx]=='D')continue;
				visited[ny][nx]=true;
				map[ny][nx]='*';
				nextWater.add(new int[] {nx,ny});
			}
		}
		
		while(!hedgehog.isEmpty()) {
			arr=hedgehog.poll();
			int x=arr[0];
			int y=arr[1];
			if(x==beaberX && y==beaberY) {
				flag=true;
				return;
			}
			for(int k=0; k<4; k++) {
				int nx=x+dx[k];
				int ny=y+dy[k];
				if(nx<0 || ny<0 || nx>=C || ny>=R || map[ny][nx]=='*' || map[ny][nx]=='X' || visitedB[ny][nx])continue;
				nextHed.add(new int[] {nx, ny});
				visitedB[ny][nx]=true;
			}
		}
	}


}
