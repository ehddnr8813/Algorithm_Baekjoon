package baek17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int N, M, K, R, C, S, rx, ry, lx, ly ,size, count, before1, before2;
	static int map[][], temp[][];
	static Node query[];
	static int visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		map= new int[N+1][M+1];
		temp= new int[N+1][M+1];
		query= new Node[K];
		visited= new int[K];
		for(int i=1; i<=N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=1; j<=M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		for(int k=0; k<K; k++) {
			st= new StringTokenizer(br.readLine());
			R=Integer.parseInt(st.nextToken());
			S=Integer.parseInt(st.nextToken());
			C=Integer.parseInt(st.nextToken());
			query[k]=new Node(R, C, S);
		}
		Arrays.fill(visited, -1);
		//dfs(0);
	
		simul(query[0]);
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=M; j++) {
			System.out.print(map[i][j]+" ");	
			}
			System.out.println();
		}
	}
	
	private static void dfs(int count) {
		if(count==K) {
			find();
			return;
		}
		for(int i=0; i<K; i++) {
			if(visited[i]==-1) {
				visited[i]=i;
				dfs(count+1);
				visited[i]=-1;
			}
		}
	}

	private static void find() {
		for(int i=0; i<N; i++) {
			temp[i]= map[i].clone();
		}
		for(int i=0; i<K; i++) {
			Node n= query[visited[i]];
			simul(n);
		}
		
	}


	private static void simul(Node n) {
		lx=n.leftX;ly=n.leftY;rx=n.rightX;ry=n.rightY;size=n.size;count=n.count;	//size-=2; 그리고 map을 temp로 바꿀것
		for(int c=0; c<count; c++) {
			before1=map[ly][rx];
			for(int i=rx; i>lx; i--)map[ly][i]=map[ly][i-1];
			before2=map[ry][rx];
			for(int i=ry; i>ly; i--)map[i][rx]=map[i-1][rx];
			map[ly+1][rx]=before1;
			before1=map[ry][lx];
			for(int i=lx; i<rx; i++)map[ry][i]=map[ry][i+1];
			map[ry][rx-1]=before2;
			for(int i=ly ; i<ry;i++)map[i][lx]=map[i+1][lx];
			map[ry-1][lx]=before1;
			size-=2;
			lx++;ly++;rx--;ry--;
		}
	}


	static class Node{
		int leftX, leftY;
		int rightX, rightY;
		int size;
		int count;
		public Node(int r, int s, int c) {
			super();
			this.leftX = c-s;
			this.leftY = r-s;
			this.rightX = c+s;
			this.rightY = r+s;
			this.size= rightY-leftY+1;
			this.count= (size+1)/2;
		}
		
		
	}

}
