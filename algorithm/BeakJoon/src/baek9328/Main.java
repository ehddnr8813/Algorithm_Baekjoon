package baek9328;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int T,w,h, inputKey, answer;
	static char map[][];
	static boolean visited[][];
	static Queue<Node> q;
	static Queue<Node> door[];
	
	static int dx[]= new int[] {1, 0, -1, 0};
	static int dy[]= new int[] {0, 1, 0, -1};
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		while(T-->0) {
			answer=0; inputKey=0;
			st=new StringTokenizer(br.readLine());
			h=Integer.parseInt(st.nextToken());
			w=Integer.parseInt(st.nextToken());
			map=new char[h+2][w+2];
			visited=new boolean[h+2][w+2];
			door= new Queue[26];
			for(int i=0; i<26; i++) {
				door[i]= new LinkedList<Node>();
			}
			q=new LinkedList<>();
			for(int i=1; i<h+1; i++) {
				st=new StringTokenizer(br.readLine());
				String s=st.nextToken();
				for(int j=0; j<s.length(); j++) {
					map[i][j+1]=s.charAt(j);
				}
			}
			startPoint();
			
			st=new StringTokenizer(br.readLine());
			String s= st.nextToken();
			for(int i=0; i<s.length(); i++) {
				if(s.charAt(0)=='0')continue;
				inputKey = inputKey | (1<<s.charAt(i)-'a');
			}
			q.add(new Node(0,0));
			visited[0][0]=true;
			bfs();
			System.out.println(answer);
		}

	}


	private static void bfs() {
		while(!q.isEmpty()) {
			Node n=q.poll();
			for(int k=0; k<4; k++) {
				int nx=n.x+dx[k];
				int ny=n.y+dy[k];
				if(nx<0 || ny<0 || nx>=w+2 || ny>=h+2 || visited[ny][nx] || map[ny][nx]=='*')continue;
				visited[ny][nx]=true;
				if(map[ny][nx]>=65 && map[ny][nx]<=90) {
					if((1<<map[ny][nx]-'A' & inputKey)==0){	
						door[map[ny][nx]-'A'].add(new Node(nx, ny));
					}
					else q.add(new Node(nx, ny));
				}
				else if(map[ny][nx]>=97 && map[ny][nx]<=122){
					door[map[ny][nx]-'a'].add(new Node(nx, ny));
					q.add(new Node(nx,ny));
					if((1<<map[ny][nx]-'a' & inputKey)==0) {
						inputKey = inputKey | (1<<map[ny][nx]-'a');
						while(!door[map[ny][nx]-'a'].isEmpty()) {
							Node d=door[map[ny][nx]-'a'].poll();
							q.add(d);
						}
					}
				}
				else {
					q.add(new Node(nx, ny));
					if(map[ny][nx]=='$')answer++;
				}
			}
		}
		
	}


	private static void startPoint() {
		for(int i=0; i<h+2; i++)map[i][0]='.';
		for(int i=0; i<h+2; i++)map[i][w+1]='.';
		for(int j=0; j<h+2; j++)map[0][j]='.';
		for(int j=0; j<h+2; j++)map[h+1][j]='.';
		
	}


	static class Node{
		int x; int y;
		
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;

		}
		
	}
	
}

