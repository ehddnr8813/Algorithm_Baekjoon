package baek14391;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M, answer=Integer.MIN_VALUE;
	static int map[][];
	static boolean visited[][];
	
	static int dx[]= new int[] {1,0};
	static int dy[]= new int[] {0,1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited= new boolean[N][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			String s= st.nextToken();
			for(int j=0; j<M; j++) {
				map[i][j]=s.charAt(j)-'0';
			}
		}
		findAnswer(0);
		System.out.println(answer);
	}

	private static void findAnswer(int sum) {
		int start[]=findStart();
		if(start[0]==-1) {
			answer=Math.max(answer, sum);
			return;
		}
		for(int k=0; k<2; k++) {
			if(k==0) {
				for(int i=start[0]; i<M; i++) {
					if(visited[start[1]][i])return;
					int temp=sumAll(start[0], start[1], k, i-start[0]+1);
					findAnswer(sum+temp);
					for(int j=start[0]; j<=i; j++) {
						visited[start[1]][j]=false;
					}
				}
			}
			else if(k==1) {
				for(int i=start[1]+1; i<N; i++) {
					if(visited[start[1]][start[0]])return;
					int temp=sumAll(start[0], start[1], k, i-start[1]+1);
					findAnswer(sum+temp);
					for(int j=start[1]; j<=i; j++) {
						visited[j][start[0]]=false;
					}
				}
			}
		}
	}

	private static int sumAll(int c, int r, int dir, int size) {
		int total=0;
		if(dir==0) {
			for(int i=0; i<size; i++) {
				total+=map[r][c]*Math.pow(10, size-i-1);
				visited[r][c]=true;
				r+=dy[0];
				c+=dx[0];
			}
		}
		else {
			for(int i=0; i<size; i++) {
				total+=map[r][c]*Math.pow(10, size-i-1);
				visited[r][c]=true;
				r+=dy[1];
				c+=dx[1];
			}
		}
		return total;
	}

	private static int[] findStart() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(!visited[i][j])return new int[] {j,i};
			}
		}
		return new int[] {-1,-1};
	}

	

}
