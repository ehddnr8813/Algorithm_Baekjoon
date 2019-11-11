package baek1707;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T, V, E, start, end;
	static ArrayList<ArrayList<Integer>> arrayList;
	static int visited[];
	static Queue<int []> q;
	static int arr[]=new int [2];
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		while(T-->0) {
			flag=true;
			st= new StringTokenizer(br.readLine());
			V=Integer.parseInt(st.nextToken());
			E=Integer.parseInt(st.nextToken());
			q= new LinkedList<>();
			arrayList= new ArrayList<>();
			visited= new int[V+1];
			
			for(int i=0; i<V+1; i++) {
				arrayList.add(new ArrayList<Integer>());
				visited[i]=0;
			}
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				start=Integer.parseInt(st.nextToken());
				end=Integer.parseInt(st.nextToken());
				arrayList.get(start).add(end);
				arrayList.get(end).add(start);
				
			}
			for(int i=0; i<V; i++) {
				if(!flag) {
					break;
				}
				if(visited[i]==0) {
					q.add(new int[] {i,1});
					visited[i]=1;
					bfs();
				}
			}
			if(flag)System.out.println("YES");
			else System.out.println("NO");
		}
	}

	private static void bfs() {
		while(!q.isEmpty() && flag) {
			arr=q.poll();
			int n=arr[0];
			int color=arr[1];
			for(int node: arrayList.get(n)) {
				if(visited[node]==0) {
					visited[node]=color*(-1);
					q.add(new int[] {node, visited[node]});
				}
				else {
					if(visited[node]+visited[n]!=0) {
						flag= false;
					}
				}
			}
		}
	}
}
