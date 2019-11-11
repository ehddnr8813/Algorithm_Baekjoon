package baek2151;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

   static int N,nx,ny,answer=Integer.MAX_VALUE, sX, sY, eX,eY, idx;
   static char map[][];
   static int mapCount[][];
   static boolean visited[][][];
   static PriorityQueue<Node> q= new PriorityQueue<Node>();
   static int arr[]= new int[4];
   
   static int dx[]= new int[] {1,0,-1,0};
   static int dy[]= new int[] {0,1,0,-1};
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      map= new char[N][N];
      mapCount= new int[N][N];
      for(int i=0; i<N; i++) {
         map[i]=br.readLine().toCharArray();
      }
      
loop: for(int i=0; i<N; i++) {
         for(int j=0; j<N; j++) {
            if(map[i][j]=='#') {
               if(idx==0){
            	   sY=i;
            	   sX=j;
               }
               else{
            	   eY=i;
            	   eX=j;
            	   break loop;
               }
               idx++;
            }
         }
      }
      visited= new boolean[4][N][N];
      init();
      bfs();
      System.out.println(answer);
   }

   private static void init() {
      for(int k=0; k<4; k++) {
         nx=sX+dx[k];
         ny=sY+dy[k];
         if(nx<0 || ny<0 || nx>=N || ny>=N || map[ny][nx]=='*')continue;
         q.add(new Node(nx,ny,0,k));
      }
      
   }

   private static void bfs() {
      while(!q.isEmpty()) {
         Node n=q.poll();
         if(visited[n.dir][n.y][n.x])continue;
         visited[n.dir][n.y][n.x]=true;
         if(n.x==eX && n.y==eY) {
        	answer =n.count;
            break;
         }         
         if(map[n.y][n.x]=='!') {
            for(int k=0; k<4; k++) {
               if((k+2)%4==n.dir)continue;
               nx=n.x+dx[k];
               ny=n.y+dy[k];
               if(nx<0 || ny<0 || nx>=N || ny>=N || map[ny][nx]=='*')continue;
               if(k==n.dir) {
            	  q.add(new Node (nx, ny, n.count, k));
                  continue;
               }
               q.add(new Node (nx, ny, n.count+1, k));
               
            }
         }
         else if(map[n.y][n.x]=='.') {
            nx=n.x+dx[n.dir];
            ny=n.y+dy[n.dir];
            if(nx<0 || ny<0 || nx>=N || ny>=N || map[ny][nx]=='*')continue;
            q.add(new Node (nx, ny, n.count, n.dir));
         }
      }
   }
  static class Node implements Comparable<Node>{
	  int x;
	  int y;
	  int count;
	  int dir;
	   	
	  public Node(int x, int y, int count, int dir) {
		  super();
		  this.x = x;
		  this.y = y;
		  this.count = count;
		  this.dir = dir;
	  }

	@Override
	public int compareTo(Node o) {
		return count-o.count;
	}
   }
}
