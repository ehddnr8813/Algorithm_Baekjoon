package baek9204;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int startX, endX;
	static int startY, endY;
	static Queue<Node> q;
	static int dx[]=new int [] {-1,1,-1,1};
	static int dy[]=new int [] {-1,-1,1,1};
	static int color[][]= new int[8][8];
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		for(int i=0; i<8; i++) {
			for(int j=0; j<8; j++) {
				if(i%2==0 && j%2==0)color[i][j]=1;
				if(i%2==1 && j%2==1)color[i][j]=1;
			}
		}
		
		int T=Integer.parseInt(st.nextToken());
		while(T-->0) {
			st= new StringTokenizer(br.readLine());
			visited= new boolean[8][8];
			startX=st.nextToken().charAt(0)-'A';
			startY=(9-Integer.parseInt(st.nextToken()))-1;
			endX=st.nextToken().charAt(0)-'A';
			endY=(9-Integer.parseInt(st.nextToken()))- 	1;
			if(color[startY][startX]!=color[endY][endX]) System.out.println("Impossible");
			else {
				if(startX==endX && endY==startY) {
					System.out.print(0+" "+(char)(startX+'A')+" "+(9-startY-1)+" ");
					continue;
				}
				bishopPath(endX, endY);
				if(visited[startY][startX]) {
					System.out.print(1+" "+(char)(startX+'A')+" "+(9-startY-1)+" ");
					System.out.println((char)(endX+'A')+" "+(9-endY-1));	
				}
				else {
					System.out.print(2+" "+(char)(startX+'A')+" "+(9-startY-1)+" ");
					find(startX, startY);
					System.out.println((char)(endX+'A')+" "+(9-endY-1));					
				}
			}
		}

	}	

	private static void find(int x, int y) {
		for(int k=0; k<4; k++) {
			int nx=x, ny=y;
			while(true) {
				nx=nx+dx[k];
				ny=ny+dy[k];
				if(nx>=0 && ny>=0 && nx<8 && ny<8) {
					if(visited[ny][nx]) {
						System.out.print((char)(nx+'A')+" "+(9-ny-1)+" ");
						return;
					}
					continue;
				}
				break;
			}
		}
	}

	private static void bishopPath(int x, int y) {
		visited[y][x]=true;
		for(int k=0; k<4; k++) {
			int nx=x, ny=y;
			while(true) {
				nx=nx+dx[k];
				ny=ny+dy[k];
				if(nx<0 || ny<0 || nx>=8 || ny>=8)break;
				visited[ny][nx]=true;
			}
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
