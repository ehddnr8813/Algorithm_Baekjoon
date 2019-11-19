package baek16983;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,L,R,X, answer;
	static int list[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		L=Integer.parseInt(st.nextToken());
		R=Integer.parseInt(st.nextToken());
		X=Integer.parseInt(st.nextToken());
		list= new int[N];
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			list[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);
		go(-1, 0, 0);
		System.out.println(answer);
	}

	static void go(int idx, int count, int visited) {
		if(count>=2) {
			int total=0, min=Integer.MAX_VALUE, max=Integer.MIN_VALUE;
			for(int i=0; i<N; i++) {
				if((visited & 1<<i)!=0) {
					total+=list[i];
					min = min > list[i] ? list[i] : min;
					max= max < list[i] ? list[i] : max;
				}
			}
			if(total>R)return;
			if(total>=L && max-min>=X) {
				answer++;
			}
			if(count==N || idx==N)return;
		}
	
		for(int i=idx+1; i<N; i++) {
			if((visited & 1<<i)==0) {
				go(i,count+1, (visited | 1<<i));
			}
		}
	}

}
