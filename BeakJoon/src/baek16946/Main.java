package baek16946;

import java.io.*;
import java.util.*;

public class Main {

   static int N,M, count;
   static int map[][];
   static int answer[][];
   static boolean visited[][];
   static Queue<int []> blank = new LinkedList<int []>();
   static Queue<int []> wall = new LinkedList<int []>();
   
   static int dx[]= new int [] {1,0,-1,0};
   static int dy[]= new int [] {0,1,0,-1};
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st= new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      M=Integer.parseInt(st.nextToken());
      map= new int[N][M];
      answer= new int[N][M];
      visited= new boolean[N][M];
      for(int i=0; i<N; i++) {
         char [] c= br.readLine().toCharArray();
         for(int j=0; j<M; j++) {
            map[i][j]=c[j]-'0';
            if(map[i][j]==1)answer[i][j]=1;
         }
      }
      
      for(int i=0; i<N; i++) {
         for(int j=0; j<M; j++) {
            if(!visited[i][j] && map[i][j]==0) {
               count=1;
               visited[i][j]=true;
               blank.add(new int[] {j,i});
               bfs();
            }
         }
      }
      
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(answer[i][j]%10);
			}
			sb.append('\n');
		}
		System.out.print(sb);
   }

   private static void bfs() {
      while(!blank.isEmpty()) {
         int arr[]=blank.poll();
         int x=arr[0];
         int y=arr[1];
         for(int k=0; k<4; k++) {
            int nx=x+dx[k];
            int ny=y+dy[k];
            if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx])continue;
            if(map[ny][nx]==0) {
               visited[ny][nx]=true;
               blank.add(new int[] {nx, ny});
               count++;
            }
            else {
            	visited[ny][nx]=true;
            	wall.add(new int[] {nx, ny});
            }
         }
      }
      
      while(!wall.isEmpty()) {
         int arr[]=wall.poll();
         int x=arr[0];
         int y=arr[1];
         answer[y][x]+=count;
         visited[y][x]=false;
      }
      
   }

}