package sw2383;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, counts, x1, y1, x2, y2, count, answer;
	static int map[][];
	static boolean visited[];
	static ArrayList<Node> stairs[][];
	static ArrayList<Node> people;
	static Queue<Node> stair1, stair2;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st=new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			map=new int[N][N];
			stairs= new ArrayList[N][N];
			people= new ArrayList<>();
			count=0;counts=0;answer=Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				st=new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					stairs[i][j]= new ArrayList<Node>();
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
			dfs(0, 0);
			System.out.println("#"+t+" "+answer);
		}
	}
	
	private static void dfs(int idx, int count) {
		if(count==counts) {
			simulate();
			return;
		}
		for(int i=idx; i<counts; i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(i+1, count+1);
				visited[i]=false;
				dfs(i+1, count+1);
			}
		}
		
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
		stairs[y1][x1].sort(null);
		stairs[y2][x2].sort(null);
		int t1=0, t2=0, count=0;
		stair1= new LinkedList<Node>();
		stair2= new LinkedList<Node>();
		
		

		answer=Math.min(t1+t2, answer);
	}
	
	private static int distance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1-x2)+Math.abs(y1-y2);
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
