package baek1065;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, hun, ten, one;
	static int[] han=new int[3];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		han[0]=9;
		han[1]=90;
		for(int i=100; i<=N; i++) {
			hun=i/100;
			ten=i%100/10;
			one=i%10;
			if(hun>one) {
				if(hun-ten==ten-one)han[2]++;
			}
			else {
				if(one-ten==ten-hun)han[2]++;
			}
		}
		if(N<=99) {	
			System.out.println(N);
		}
		else System.out.println(han[0]+han[1]+han[2]);
	}

}
