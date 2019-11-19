package baek16945;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int map[][]= new int[3][3];
	static int temp[][]= new int[3][3];
	static int visited[]= new int [10];
	static int answer=Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;
		for(int i=0; i<3; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<3; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		go(0);
		System.out.println(answer);
	}

	private static void go(int count) {
		if(count==9) {
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					temp[i][j]=visited[3*i+j+1];
				}
			}
			if(isDia() && isRow() && isCol()) {
				cal();
			}
			return;
		}
		
		for(int i=0; i<10; i++) {
			if(visited[i]==0) {
				visited[i]=count+1;
				go(count+1);
				visited[i]=0;
			}
		}
	}


	static boolean isDia() {
		if(temp[0][0]+temp[1][1]+temp[2][2]!=15)return false;
		else if(temp[0][2]+temp[1][1]+temp[2][0]!=15)return false;
		return true;
	}
	
	static boolean isRow() {
		int total;
		for(int i=0; i<3; i++) {
			total=0;
			for(int j=0; j<3; j++) {
				total+=temp[i][j];
			}
			if(total!=15)return false;
		}
		return true;
	}
	
	static boolean isCol() {
		int total;
		for(int i=0; i<3; i++) {
			total=0;
			for(int j=0; j<3; j++) {
				total+=temp[j][i];
			}
			if(total!=15)return false;
		}
		return true;
	}
	
	private static void cal() {
		int diff=0;
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				diff+=Math.abs(map[i][j]-temp[i][j]);
			}
		}
		answer = diff < answer ? diff : answer;
	}
}
