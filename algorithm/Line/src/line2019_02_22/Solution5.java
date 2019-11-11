package line2019_02_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution5 {

	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		st= new StringTokenizer(br.readLine());
		int x=Integer.parseInt(st.nextToken());
		int y=Integer.parseInt(st.nextToken());
		if(x==0 && y==0){
			System.out.println(0);
			System.out.println(1);
			return;
		}
		if(x>M || y>N){
			System.out.println("fail");
			return;
		}
		long[][] map= new long[N][M];
		map[0][0]=1;
		for(int i=0; i<N; i++){
			map[i][0]=1;
		}
		for(int j=0; j<M; j++){
			map[0][j]=1;
		}
		for(int i=1; i<N; i++){
			for(int j=1; j<M; j++){
				map[i][j]+=map[i][j-1];
				map[i][j]+=map[i-1][j];
			}
		}
		System.out.println(map[y][x]);
		System.out.println(x+y);
	}

}
