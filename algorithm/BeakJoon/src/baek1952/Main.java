package baek1952;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static int count, M, N;
	static boolean map[][];
	static int[] dx= new int[] {1,0,-1,0};
	static int[] dy= new int[] {0,1,0,-1};
	static int[] arr= new int[3];
	static Queue<int []> q= new LinkedList<int[]>();

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		M=Integer.parseInt(st.nextToken());
		N=Integer.parseInt(st.nextToken());
		map=new boolean[M][N];
		map[0][0]=true;
		q.add(new int[] {0,0,0});
		bfs();
		System.out.println(count);
	}

	private static void bfs() {
		while(!q.isEmpty()) {
			arr=q.poll();
			int dir=arr[2];
			int nx=arr[0]+dx[dir];
			int ny=arr[1]+dy[dir];
			if(nx>=0 && ny>=0 && nx<N && ny<M && !map[ny][nx]) {
				map[ny][nx]=true;
				q.add(new int[] {nx, ny, dir});
			}
			else {
				dir=(dir+1)%4;
				nx=arr[0]+dx[dir];
				ny=arr[1]+dy[dir];
				if(nx>=0 && ny>=0 && nx<N && ny<M && !map[ny][nx]) {
					map[ny][nx]=true;
					q.add(new int[] {nx, ny, dir});
					count++;
				}
			}
		}
	}
}
