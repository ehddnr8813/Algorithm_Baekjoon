package baek2580;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static int map[][]= new int[9][9];
	static ArrayList<int []> blank= new ArrayList<>();
	static int arr[]=new int[2];
	static boolean isEnd;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= null;
		for(int i=0; i<9; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]==0)blank.add(new int[] {j,i});
			}
		}
		go(0);
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}

	private static void go(int i) {
		if(isEnd)return;
		if(i==blank.size()) {
			isEnd=true;
			return;
		}
		arr=blank.get(i);
		int x=arr[0];
		int y=arr[1];
		for(int cur=1;cur<=9; cur++) {
			if(!(isRow(y, cur) && isCol(x, cur) && isRec(y/3*3,x/3*3, cur))) continue;
			map[y][x]=cur;
			go(i+1);
			if(isEnd)return;
			map[y][x]=0;
		}
	}

	private static boolean isRec(int i, int j, int cur) {
		boolean flag=true;
loop:	for(int y=i; y<i+3; y++) {
			for(int x=j; x<j+3; x++) {
				if(map[y][x]==cur) {
					flag=false;
					break loop;
				}
			}
		}
		return flag;
	}

	private static boolean isCol(int j, int cur) {
		boolean flag=true;
		for(int y=0; y<9; y++) {
			if(map[y][j]==cur) {
				flag=false;
				break;
			}
		}
		return flag;
	}

	private static boolean isRow(int i,int cur) {
		boolean flag=true;
		for(int x=0; x<9; x++) {
			if(map[i][x]==cur) {
				flag=false;
				break;
			}
		}
		return flag;
	}
}
