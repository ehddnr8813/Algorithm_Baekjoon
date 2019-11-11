package baek16954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int size, answer;
	static char map[][]= new char[8][8];
	static boolean visited[][][]= new boolean [14][8][8];
	static Queue<Node> wall= new LinkedList<>();
	static Queue<Node> player= new LinkedList<>();
	
	static int dx[]= new int[]{1,0,-1,0,1,-1,1,-1,0};
	static int dy[]= new int[]{0,1,0,-1,1,-1,-1,1,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;
		for(int i=0; i<8; i++) {
			map[i]=br.readLine().toCharArray();
		}
		for(int i=7; i>=0; i--) {
			for(int j=0; j<8; j++) {
				if(map[i][j]=='#')wall.add(new Node(j, i));
			}
		}
		player.add(new Node(0, 7, 0));
		bfs();
		System.out.println(answer);
	}
	
	private static void bfs() {
		while(!player.isEmpty()) {
			size=player.size();
			while(size-->0) {
				Node n= player.poll();
				if(map[n.y][n.x]=='#')continue;
				if(n.x==7 && n.y==0) {
					answer=1;
					return;
				}
				for(int k=0; k<9; k++) {
					int nx=n.x+dx[k];
					int ny=n.y+dy[k];
					if(nx<0 || ny<0 || nx>=8 || ny>=8 || visited[n.t+1][ny][nx] || map[ny][nx]=='#')continue;
					visited[n.t+1][ny][nx]=true;
					player.add(new Node(nx, ny, n.t+1));
				}
			}
			size=wall.size();
			while(size-->0) {
				Node n= wall.poll();
				if(n.y<=6) {
					wall.add(new Node(n.x, n.y+1));
					map[n.y+1][n.x]='#';
				}
				map[n.y][n.x]='.';
			}
		}
	}

	static class Node{
		int x;
		int y;
		int t;
		public Node(int x, int y, int t) {
			super();
			this.x = x;
			this.y = y;
			this.t=t;
		}
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
