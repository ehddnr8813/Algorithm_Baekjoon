package baek1799;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, cur, answer;
	static int map[][];
	static int colorArr[][];
	static boolean dia[];
	static boolean anti[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		N=Integer.parseInt(st.nextToken());
		map=new int[N][N];
		dia= new boolean[2*N-1];
		anti= new boolean[2*N-1];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}

		go(-2,0,1,0);
		answer=cur;
		cur=0;
		go(-1,0,0,0);
		System.out.println(answer+cur);
	}

	static void go(int x, int y, int color, int count) {
		cur = count > cur ? count : cur;
		x+=2;
		if(x>=N && color==1) {
			y+=1;
			if(y==N)return;
			if(y%2==1) {
				x=1;
			}
			else x=0;
		}
		else if(x>=N && color==0) {
			y+=1;
			if(y==N)return;
			if(y%2==1) {
				x=0;
			}
			else x=1;
		}

		if(!anti[y+x] && !dia[N-1+y-x] && map[y][x]==1) {
			anti[y+x]=true;
			dia[N-1+y-x]=true;
			go(x, y, color, count+1);
			anti[y+x]=false;
			dia[N-1+y-x]=false;
		}
		go(x, y, color, count);
	}
}
