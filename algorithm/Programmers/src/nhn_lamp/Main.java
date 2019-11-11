package nhn_lamp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int N, flag;
	static String[][] map, temp;
	static ArrayList<String> str;
	static int start[]= new int[3];
	
	static int dx[]= new int[] {1,0,-1,0};
	static int dy[]= new int[] {0,1,0,-1};
	
	static int dx2[]= new int[] {0,1,0,-1};
	static int dy2[]= new int[] {1,0,-1,0};
	
	public static void main(String[] args) throws IOException {
		Scanner sc= new Scanner(System.in);
		N=sc.nextInt();
		int ori=N;
		int dir=sc.nextInt();
		sc.nextLine();

		map= new String[N][N];
		for(int i=0; i<N; i++) {
			String[] str= sc.nextLine().split(" ");
			for(int j=0; j<N; j++) {
				map[i][j]=str[j];
			}
		}

		rotate(dir);
		for(int i=0; i<ori; i++) {
			for(int j=0; j<ori; j++) {
				System.out.print(temp[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	static void rotate(int dir) {
		int startX=0, startY=0, size=Math.abs(dir);
		temp= new String[N][N];
		if(dir>0)flag=1; //flag는 1일때 시계, 0일때 반시계
		while(N>=1) {
			if(N==1) {
				temp[startY][startX]=map[startY][startX];
				break;
			}
			int curSize=(N-2)*4+4;
			int turn=size%curSize;
			start=makeStartPoint(startX, startY,turn, flag);

			if(flag==1)make(startX, startY);
			else make2(startX, startY);
			N-=2;
			startX+=1;startY+=1;
			flag= (flag+1)%2;
		}
		if(dir<0) dir=3;
		else dir=0;
	}
	
		


	private static int[] makeStartPoint(int x, int y,int turn, int flag) {
		int dir=0;
		int startX=x, startY=y;
		if(flag==0) {
			while(turn-->0) {
				int nx=x+dx2[dir];
				int ny=y+dy2[dir];
				if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
					x=nx;
					y=ny;
				}
				else {
					dir++;
					turn++;
				}
			}
			return new int[] {x,y, dir};
		}
		else {
			while(turn-->0) {
				int nx=x+dx[dir];
				int ny=y+dy[dir];
				if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
					x=nx;
					y=ny;
				}
				else {
					dir++;
					turn++;
				}
			}
			return new int[] {x,y, dir};
		}
	}

	static void make(int x, int y) {
		int startX=x, startY=y;
		int dir=0;
		str= new ArrayList<>();
		str.add(map[y][x]);
		while(dir!=4) {
			int nx=x+dx[dir];
			int ny=y+dy[dir];
			if(nx==startX && ny==startY)break;
			if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
				str.add(map[ny][nx]);
				x=nx;
				y=ny;
			}
			else {
				dir++;
			}
		}
		int nx=start[0], ny=start[1], sDir=start[2], idx=1;
		temp[ny][nx]=str.get(0);
		while(str.size()!=idx) {
			nx+=dx[sDir];
			ny+=dy[sDir];
			if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
				temp[ny][nx]=str.get(idx);
				idx++;
			}
			else {
				nx-=dx[sDir];
				ny-=dy[sDir];
				sDir=(sDir+1)%4;
			}
		}
	}

	static void make2(int x, int y) {
		int startX=x, startY=y;
		int dir=0;
		str= new ArrayList<>();
		str.add(map[y][x]);
		while(dir!=4) {
			int nx=x+dx2[dir];
			int ny=y+dy2[dir];
			if(nx==startX && ny==startY)break;
			if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
				str.add(map[ny][nx]);
				x=nx;
				y=ny;
			}
			else {
				dir++;
			}
		}
		int nx=start[0], ny=start[1], sDir=start[2], idx=1;
		temp[ny][nx]=str.get(0);
		while(str.size()!=idx) {
			nx+=dx2[sDir];
			ny+=dy2[sDir];
			if(nx>=startX && ny>=startY && nx<startX+N && ny<startY+N) {
				temp[ny][nx]=str.get(idx);
				idx++;
			}
			else {
				nx-=dx2[sDir];
				ny-=dy2[sDir];
				sDir=(sDir+1)%4;
			}
		}
	}
}
