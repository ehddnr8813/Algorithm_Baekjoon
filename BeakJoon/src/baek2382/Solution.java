package baek2382;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, K, total;
	static ArrayList<Micro> micros;
	static ArrayList<Micro>map[][];
	
	static int dx[]=new int[] {0,0,0,-1,1};
	static int dy[]=new int[] {0,-1,1,0,0};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			micros= new ArrayList<>();
			total=0;
			map = new ArrayList[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					map[i][j]= new ArrayList<Micro>();
				}
			}
			for(int i=0; i<K; i++) {
				st= new StringTokenizer(br.readLine());
				Micro m= new Micro(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
				micros.add(m);
				map[m.y][m.x].add(m);
			}
			for(int m=0; m<=M-1; m++) {
				print();
				move();
				add();
			}
			print();
			for(int i=0; i<micros.size(); i++) {
				total+=micros.get(i).large;
			}
			System.out.println("#"+t+" "+total);
		}
	}
	
	private static void add() {
		micros = new ArrayList<>();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size()>=2) {
					Collections.sort(map[i][j]);
					Micro first=map[i][j].get(0);
					for(int k=1; k<map[i][j].size(); k++)first.large+=map[i][j].get(k).large;
					micros.add(first);
				}
				else if(map[i][j].size()==1) micros.add(map[i][j].get(0));
			}
		}
	}

	private static void move() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++)map[i][j].clear();
		}
		for(Micro m : micros) {
			int nx=m.x+dx[m.dir];
			int ny=m.y+dy[m.dir];
			int dir=0;
			if(nx==0 || ny==0 || nx==N-1 || ny==N-1) {
				if(m.dir==1)dir=2;
				else if(m.dir==2)dir=1;
				else if(m.dir==3)dir=4;
				else if(m.dir==4)dir=3;
				map[ny][nx].add(new Micro(ny, nx, m.large/2, dir));
			}
			else {
				map[ny][nx].add(new Micro(ny, nx, m.large, m.dir));
			}
		}
	}
	
	static void print() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j].size()!=0) {
					System.out.print(map[i][j].get(0).large+" ");
				}
				else System.out.print(0+" ");
			}
			System.out.println();
		}
		System.out.println("=====================");
	}

	static class Micro implements Comparable<Micro>{
		int x, y, dir, large;
		public Micro(int y, int x, int large, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.large = large;
			this.dir = dir;
		}
		@Override
		public int compareTo(Micro o) {
			return -1*(this.large-o.large);
		}
	}

}
