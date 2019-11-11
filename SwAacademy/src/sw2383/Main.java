package sw2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

   static int N, counts, x1, y1, x2, y2, count, answer;
   static int map[][];
   static boolean visited[];
   static PriorityQueue<Node> stairs[][];
   static ArrayList<Node> people;
   static Queue<Integer> stair1, stair2;

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st= new StringTokenizer(br.readLine());
      int T=Integer.parseInt(st.nextToken());
      for(int t=1; t<=T; t++) {
         st=new StringTokenizer(br.readLine());
         N=Integer.parseInt(st.nextToken());
         map=new int[N][N];
         stairs= new PriorityQueue[N][N];
         people= new ArrayList<>();
         count=0;counts=0;answer=Integer.MAX_VALUE;
         for(int i=0; i<N; i++) {
            st=new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
               stairs[i][j]= new PriorityQueue<Node>();
               map[i][j]=Integer.parseInt(st.nextToken());
               if(map[i][j]==1) {
                  people.add(new Node(j, i, map[i][j]));
                  counts++;
               }
               if(map[i][j]>=2) {
                  if(count==0) {
                     x1=j; y1=i;
                     count++;
                  }
                  else {
                     x2=j; y2=i;
                  }
               }
            }
         }
         visited= new boolean[counts];
         dfs(0);
         System.out.println("#"+t+" "+answer);
      }
   }
   
   private static void dfs(int idx) {
      if(idx==counts) {
         simulate();
         return;
      }
		dfs(idx+1);
		visited[idx]=true;
		dfs(idx+1);
		visited[idx]=false;
   }

   private static void simulate() {
      
      for(int i=0; i<counts; i++) {
         if(!visited[i]) {
            int px=people.get(i).x;
            int py=people.get(i).y;
            stairs[y1][x1].add(new Node(px, py, distance(px, py, x1, y1)));
         }
         else {
            int px=people.get(i).x;
            int py=people.get(i).y;
            stairs[y2][x2].add(new Node(px, py, distance(px, py, x2, y2)));
         }
      }
      goDown();
   }

   private static void goDown() {
      int t1=0, t2=0;
      stair1= new LinkedList<Integer>();
      stair2= new LinkedList<Integer>();
      
      while(!stairs[y1][x1].isEmpty() || !stair1.isEmpty()){
    	  t1++;
    	  while(!stair1.isEmpty()){
    		  if(stair1.peek()<=t1)stair1.poll();
    		  else break;
    	  }
    	  while(!stairs[y1][x1].isEmpty()){
    		  if(stairs[y1][x1].peek().count<=t1 && stair1.size()<3){
    			  stairs[y1][x1].poll();
    			  stair1.add(new Integer(t1+map[y1][x1]));
    		  }
    		  else break;
    	  }
      }

      
      while(!stairs[y2][x2].isEmpty() || !stair2.isEmpty()){
    	  t2++;
    	  while(!stair2.isEmpty()){
    		  if(stair2.peek()<=t2)stair2.poll();
    		  else break;
    	  }
    	  while(!stairs[y2][x2].isEmpty()){
    		  if(stairs[y2][x2].peek().count<=t2 && stair2.size()<3){
    			  stairs[y2][x2].poll();
    			  stair2.add(new Integer(t2+map[y2][x2]));
    		  }
    		  else break;
    	  }
      }
      answer=Math.min(Math.max(t1, t2), answer);
   }
   
   private static int distance(int x1, int y1, int x2, int y2) {
      return Math.abs(x1-x2)+Math.abs(y1-y2)+1;
   }

   static class Node implements Comparable<Node>{
      int x,y,count;

      public Node(int x, int y, int count) {
         super();
         this.x = x;
         this.y = y;
         this.count = count;
      }

      @Override
      public int compareTo(Node o) {   
         return count-o.count;
      }
   }

}