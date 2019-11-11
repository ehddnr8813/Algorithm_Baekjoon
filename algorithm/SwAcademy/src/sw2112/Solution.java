package sw2112;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	
	static int D, W, K, answer;
	static int[][] map, temp;
	static boolean[] visited;
	static int[] visited2;
	static ArrayList<Integer> list;
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			flag=false;
			st= new StringTokenizer(br.readLine());
			D=Integer.parseInt(st.nextToken());
			W=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			map= new int[D][W];
			visited= new boolean[D];
			answer=-1;
			for(int i=0; i<D; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j]=Integer.parseInt(st.nextToken());
				}
			}
			for(int i=0; i<=D; i++) {
				dfs(0, 0, i);
				if(flag)break;
			}
			System.out.println("#"+t+" "+answer);
		}
	}

	private static void dfs(int index, int cur, int count) {
		if(flag)return;
		if(cur==count) {
			changeColor();
			return;
		}
		for(int i=index; i<D; i++) {
			if(!visited[i]) {
				visited[i]=true;
				dfs(i+1, cur+1, count);
				visited[i]=false;
			}
		}
	}

	private static boolean check() {
		for(int j=0; j<W; j++) {
			int total=1;
			for(int i=0; i<D-1; i++) {
				if(temp[i][j]==temp[i+1][j])total++;
				else total=1;
				if(total==K)break;
			}
			if(total!=K)return false;
		}
		return true;
	}

	private static void changeColor() {
		list= new ArrayList<>();
		for(int i=0; i<D; i++) {
			if(visited[i]) {
				list.add(i);
			}
		}
		int size=list.size();
		visited2= new int[D];
		dfs2(0,0, size);
		
	}

	private static void dfs2(int index,int count, int size) {
		if(flag)return;
		if(count==size) {
			copy();
			change();
			if(check()) {
				answer=count;
				flag=true;
			}
			return;
		}
		for(int i=index; i<list.size(); i++) {
			if(visited2[i]==0) {
				dfs2(i+1, count+1, size);
				visited2[i]=1;
				dfs2(i+1, count+1, size);
				visited2[i]=0;
			}
		}
	}

	private static void change() {
		for(int i=0; i<list.size(); i++) {
			int row=list.get(i);
			if(visited2[i]==1) {
				Arrays.fill(temp[row], 1);
			}
			else {
				Arrays.fill(temp[row], 0);
			}
		}
	}
	
	static void copy() {
		temp= new int [D][W];
		for(int i=0; i<D; i++) {
			temp[i]=map[i].clone();
		}
	}


}
