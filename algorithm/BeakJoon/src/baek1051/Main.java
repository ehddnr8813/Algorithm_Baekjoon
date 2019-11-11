package baek1051;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N,M, count,answer;
	static char map[][];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		map= new char[N][M];
		for(int i=0; i<N; i++) {
			map[i]=br.readLine().toCharArray();
		}
		count= Math.min(N, M);
		simul();
		System.out.println(answer);
	}

	private static void simul() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				for(int k=0; k<count; k++) {
					getMax(j,i,k);
				}
			}
		}
		
	}

	private static void getMax(int x, int y, int size) {
		if(x+size>=M || y+size>=N)return;
		check(x, y, size);
	}

	private static void check(int x, int y, int size) {
		if((map[y][x]-'0')==(map[y+size][x+size]-'0') && (map[y+size][x]-'0')==(map[y][x+size]-'0') && (map[y+size][x]-'0')==(map[y+size][x+size]-'0')) {
			answer=Math.max((size+1)*(size+1), answer);
		}
		
	}

}
