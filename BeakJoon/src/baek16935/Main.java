package baek16935;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N,M,R,tempVal,before, query;
	static int map[][];	
	static int[] temp;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		if(N>M)map= new int[N][N];
		else map=new int[M][M];
		for(int i=0; i<N; i++) {
			st=new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		
		st= new StringTokenizer(br.readLine());
		for(int i=0;i<R;i++) {
			query=Integer.parseInt(st.nextToken());
			switch(query) {
			case 1 :
				swapVertical();
				break;
			case 2:
				swapHorizontal();
				break;
			case 3:
				rotateRight();
				break;
			case 4:
				rotateLeft();
				break;
			case 5:
				divRight();
				break;
			case 6:
				divLeft();
				break;
			}
		}
		print();
	}

	static void swapVertical() {
		for(int i=0; i<N/2; i++) {
			temp= map[i];
			map[i]=map[N-1-i];
			map[N-1-i]=temp;
		}
	}
	
	
	static void swapHorizontal() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M/2; j++) {
				tempVal=map[i][j];
				map[i][j]=map[i][M-1-j];
				map[i][M-1-j]=tempVal;
			}
		}
	}
	
	static void rotateRight() {
		int tempMap[][]= new int[M][N];
		for(int i=0; i<N; i++) {
			temp=map[i];
			for(int j=0; j<M; j++) {
				tempMap[j][N-1-i]=temp[j];
			}
		}
		tempVal=N;
		N=M;
		M=tempVal;
		for(int i=0; i<N; i++) {
			map[i]=tempMap[i].clone();
		}
	}
	
	static void rotateLeft() {
		int tempMap[][]= new int[M][N];
		for(int i=0; i<N; i++) {
			temp=map[i];
			for(int j=0; j<M; j++) {
				tempMap[j][i]=temp[M-1-j];
			}
		}
		tempVal=N;
		N=M;
		M=tempVal;
		for(int i=0; i<N; i++) {
			map[i]=tempMap[i].clone();
		}
	}
	
	static void divRight() {
		int tempMap[][]= new int[N][M];
		for(int i=0; i<N/2;i++) {			//4번을 1번으로
			for(int j=0; j<M/2; j++) {
				tempMap[i][j]=map[N/2+i][j];
			}
		}
		for(int i=0; i<N/2;i++) {		//1번을 2번으로
			for(int j=M/2; j<M; j++) {
				tempMap[i][j]=map[i][j-M/2];
			}
		}
		for(int i=N/2; i<N;i++) {
			for(int j=0; j<M/2; j++) {	//3번을 4번으로
				tempMap[i][j]=map[i][j+M/2];
			}
		}
		for(int i=N/2; i<N;i++) {
			for(int j=M/2; j<M; j++) {	//2번을 3번으로
				tempMap[i][j]=map[i-N/2][j];
			}
		}
		for(int i=0; i<N; i++) {
			map[i]=tempMap[i].clone();
		}
	}
	
	static void divLeft() {
		int tempMap[][]= new int[N][M];
		for(int i=0; i<N/2;i++) {			//2번을 1번으로
			for(int j=0; j<M/2; j++) {
				tempMap[i][j]=map[i][j+M/2];
			}
		}
		for(int i=0; i<N/2;i++) {		//3번을 2번으로
			for(int j=M/2; j<M; j++) {
				tempMap[i][j]=map[i+N/2][j];
			}
		}
		for(int i=N/2; i<N;i++) {
			for(int j=0; j<M/2; j++) {	//1번을 4번으로
				tempMap[i][j]=map[i-N/2][j];
			}
		}
		for(int i=N/2; i<N;i++) {
			for(int j=M/2; j<M; j++) {	//4번을 3번으로
				tempMap[i][j]=map[i][j-M/2];
			}
		}
		for(int i=0; i<N; i++) {
			map[i]=tempMap[i].clone();
		}
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
