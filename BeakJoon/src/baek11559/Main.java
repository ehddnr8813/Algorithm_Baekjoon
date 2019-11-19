package baek11559;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static char map[][]= new char[12][6];
	static char temp[][];
	
	static boolean visited[][];
	static ArrayList<Node> list;
	static int answer, lastX , lastY;
	static boolean flag=true;
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st =null;
		for(int i=0; i<12; i++) {
			st= new StringTokenizer(br.readLine());
			String s=st.nextToken();
			for(int j=0; j<6; j++) {
				map[i][j]=s.charAt(j);
			}
		}
		simulate();
		System.out.println(answer);
	}


	private static void simulate() {
		while(flag) {
			flag=false;
			temp= new char[12][6];
			for(int i=0; i<12; i++) {
				temp[i]=map[i].clone();
			}
			visited= new boolean[12][6];
			for(int i=0; i<12; i++) {
				for(int j=0; j<6; j++) {
					if(map[i][j]=='.' || visited[i][j])continue;
					else {
						list= new ArrayList<>();
						int count=dfs(j,i, map[i][j]);
						if(count>=4) {
							flag=true;
							remove();
						}
					}
				}
			}
			for(int i=0; i<12; i++) {
				map[i]=temp[i].clone();
			}
			if(flag)answer++;
		}
	}

	private static void remove() {
		for(int i=0; i<list.size(); i++) {
			int x=list.get(i).x;
			int y=list.get(i).y;
			temp[y][x]='.';
			goDown(x,y);
		}
	}


	private static void goDown(int x, int y) {
		for(int i=y; i>0 ;i--) {
			temp[i][x]=temp[i-1][x];
		}
		temp[0][x]='.';
	}


	private static int dfs(int x, int y, char c) {
		visited[y][x]=true;
		list.add(new Node(x, y));
		int count=0;
		for(int k=0; k<4; k++) {
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx<0 || ny<0 || nx>=6 || ny>=12 || visited[ny][nx] || map[ny][nx]!=c)continue;
			count+=dfs(nx,ny,c);
		}
		return count+1;
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
