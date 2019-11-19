package baek17780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

   static int N,K;
   static int [][] map;
   static ArrayList<Node>[][] drones;
   static ArrayList<Node> list= new ArrayList<>();
   
   static int dx[]= new int[]{1,0,-1,0};
   static int dy[]= new int[]{0,1,0,-1};
   
   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st= new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      K=Integer.parseInt(st.nextToken());
      map= new int[N][N];
      drones = new ArrayList[N][N];
      for(int i=0; i<N; i++){
         for(int j=0; j<N; j++){
            drones[i][j]=new ArrayList<Node>();
         }
      }
      for(int i=0; i<N; i++){
         st= new StringTokenizer(br.readLine());
         for(int j=0; j<N; j++){
            map[i][j]=Integer.parseInt(st.nextToken());
         }
      }
      for(int i=0; i<K; i++){   
         st= new StringTokenizer(br.readLine());
         int y=Integer.parseInt(st.nextToken());
         int x=Integer.parseInt(st.nextToken());
         int dir=Integer.parseInt(st.nextToken());
         list.add(new Node(i+1,x,y,dir));
         drones[y][x].add(new Node(i+1, x, y, dir));
      }
      while(true){
         move();
         count();
      }
   }

   private static boolean count() {
      list = new ArrayList<>();
      for(int i=0; i<N; i++){
         for(int j=0; j<N; j++){
            if(drones[i][j].size()==4)return true;
            if(drones[i][j].size()>=1){
               for(int k=0; k<drones[i][j].size(); k++){
                  list.add(drones[i][j].get(k));
               }
               drones[i][j].clear();
            }
         }
      }
      return false;
   }

   private static void move() {
      for(int i=0; i<list.size(); i++){
         Node n=list.get(i);
         //ÀÌ³ðÀÌ °¡Àå ¾Æ·§³ðÀÎÁö Ã¼Å©
         if(!check(n.number,n.x, n.y))continue;
         int nx=n.x+dx[n.dir];
         int ny=n.y+dy[n.dir];
         if(nx<0 || ny<0 || nx==N || ny==N){
            changeDir(n.x,n.y);
            nx=n.x+dx[n.dir];
            ny=n.y+dy[n.dir];
            if(map[ny][nx]==2)continue;
            moveAll(n.x, n.y, nx, ny);
         }
         else{
            if(map[ny][nx]==0){
               moveAll(n.x,n.y,nx, ny);
            }
            else if(map[ny][nx]==1){
               reverse(n.x, n.y, nx, ny);
            }
            else{
               changeDir(n.x,n.y);
               nx=n.x+dx[n.dir];
               ny=n.y+dy[n.dir];
               if(map[ny][nx]==2)continue;
               moveAll(n.x, n.y, nx, ny);
            }
         }
      }
   }

   private static void reverse(int x, int y,int nx, int ny) {
      for(int i=drones[y][x].size()-1; i>=0; i--){
         drones[ny][nx].add(drones[y][x].get(i));
      }
      drones[y][x].clear();
   }

   private static void moveAll(int x, int y, int nx, int ny) {
      for(int i=0; i<drones[y][x].size(); i++){
         drones[ny][nx].add(drones[y][x].get(i));
      }
      drones[y][x].clear();
   }

   private static void changeDir(int x, int y) {
      Node n=drones[y][x].get(0);
      if(n.dir==0)n.dir=1;
      else if(n.dir==1)n.dir=0;
      else if(n.dir==2)n.dir=3;
      else if(n.dir==3)n.dir=2;
   }

   private static boolean check(int number,int x, int y) {
      if(number==drones[y][x].get(0).number)return true;
      return false;
   }

   static class Node implements Comparable<Node>{
      int number;
      int x;
      int y;
      int dir;
      public Node(int number, int x, int y, int dir) {
         super();
         this.number = number;
         this.x = x;
         this.y = y;
         this.dir = dir;
      }

      @Override
      public int compareTo(Node o) {
         return number-o.number;
      }
   }
   
}