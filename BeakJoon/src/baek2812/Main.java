package baek2812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static long N,K;
	static int count;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		arr=new int[10];
		st= new StringTokenizer(br.readLine());
		String s= st.nextToken();
		for(int i=0; i<N; i++)arr[(s.charAt(i)-'0')]++;
		while(K>0) {
			for(int k=0; k<10; k++) {
				if(arr[count]==0) {
					count++;
					continue;
				}
				arr[count]--;
				K--;
				s=s.replaceFirst(Integer.toString(count), "");
				if(K==0)break;
			}
		}
		System.out.println(s);

	}

}
