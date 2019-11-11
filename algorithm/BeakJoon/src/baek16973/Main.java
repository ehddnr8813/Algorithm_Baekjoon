package baek16973;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

   static int N,M,H,W,nx,ny,answer=Integer.MAX_VALUE;
   static int map[][];
   static boolean visited[][];
   static int startX, startY, endX, endY;
   static Queue<int []> q= new LinkedList<int[]>();
   static int arr[]= new int[3];
   
   static int dx[]=new int[]{1,0,-1,0};
   static int dy[]=new int[]{0,1,0,-1};
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      M=Integer.parseInt(st.nextToken());
      map = new int[N][M];
      visited= new boolean[N][M];
      for(int i=0; i<N; i++){
         st = new StringTokenizer(br.readLine());
         for(int j=0; j<M; j++){
            map[i][j]=Integer.parseInt(st.nextToken());
         }
      }
      st = new StringTokenizer(br.readLine());
      H=Integer.parseInt(st.nextToken());
      W=Integer.parseInt(st.nextToken());
      startY=Integer.parseInt(st.nextToken())-1;
      startX=Integer.parseInt(st.nextToken())-1;
      endY=Integer.parseInt(st.nextToken())-1;
      endX=Integer.parseInt(st.nextToken())-1;
      visited[startY][startX]=true;
      q.add(new int[]{startX, startY, 0});
      bfs();
      if(answer==Integer.MAX_VALUE)System.out.println(-1);
      else System.out.println(answer);
   }

   private static void bfs() {
      while(!q.isEmpty()){
         arr=q.poll();
         int x=arr[0];
         int y=arr[1];
         int count=arr[2];
         if(count+1==answer)return;
         if(x==endX && y==endY) {
        	 answer=count;
        	 return;
         }
         for(int k=0; k<4; k++){
        	nx=x+dx[k];
        	ny=y+dy[k];
            if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx])continue;
            if(check(nx, ny, k)) {
            	visited[ny][nx]=true;
            	q.add(new int[] {nx, ny, count+1});
            }
         }
      }
   }
	
	private static boolean check(int x, int y, int dir) {
		switch(dir) {
		case 0 :
			int ex=x+W-1;
			if(ex>=M)return false;
			for(int i=y; i<y+H; i++) {
				if(map[i][ex]==1)return false;
			}
			break;
		case 1 :
			int ey=y+H-1;
			if(ey>=N)return false;
			for(int i=x; i<x+W; i++) {
				if(map[ey][i]==1)return false;
			}
			break;
		case 2 :
			for(int i=y; i<y+H; i++) {
				if(map[i][x]==1)return false;
			}
			break;
		case 3:
			for(int i=x; i<x+W; i++) {
				if(map[y][i]==1)return false;
			} 
			break;	
		}
		return true;
	} 
}