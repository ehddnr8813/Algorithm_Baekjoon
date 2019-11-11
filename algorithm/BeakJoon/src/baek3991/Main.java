package baek3991;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int map[][];
	static int h,w,k, startX, startY, startD, nx, ny;
	static int kinds[];
	static Queue<Node> q;
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		h=Integer.parseInt(st.nextToken());
		w=Integer.parseInt(st.nextToken());
		k=Integer.parseInt(st.nextToken());

		map=new int[h][w];
		kinds= new int[k+1];	
		st= new StringTokenizer(br.readLine());
		for(int i=1; i<=k;i++) {
			kinds[i]=Integer.parseInt(st.nextToken());
		}
		
		while(k>0) {
			dfs(startX,startY,startD,k, kinds[k]);
			k--;
		}
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}

	private static void dfs(int x, int y,int dir, int kind, int count) {
		map[y][x]=kind;
		if(count==1) {
			nx=x+dx[dir];
			ny=y+dy[dir];
			if(nx<0 || ny<0 || nx>=w || ny>=h ) {
				dir=(dir+1)%4;
				nx=x+dx[dir];
				ny=y+dy[dir];
				startX=nx;startY=ny;startD=dir;
			}
			else if(dir%2==1) {
				if(x-1<0) {
					startX=x+1;
					startY=y;
					startD=0;
				}
				else {
					startX=x-1;
					startY=y;
					startD=2;
				}
				return;
			}
			else {
				startX=nx;startY=ny;startD=dir;
			}
			return;
		}
		if(dir%2==1) {
			if(x-1<0) {
				nx=x+1;
				dir=0;
			}
			else {
				nx=x-1;
				dir=2;
			}
			dfs(nx, ny, dir, kind, count-1);
			return;
		}
		
		nx=x+dx[dir];
		ny=y+dy[dir];
		
		if(nx<0 || ny<0 || nx>=w || ny>=h) {
			dir=(dir+1)%4;
			nx=x+dx[dir];
			ny=y+dy[dir];
			dfs(nx, ny, dir, kind, count-1);
			return;
		}
		dfs(nx, ny, dir, kind, count-1);
	}

	static class Node{
		int x,y,kind,count;

		public Node(int x, int y, int kind, int count) {
			super();
			this.x = x;
			this.y = y;
			this.kind = kind;
			this.count = count;
		}
	}

}
