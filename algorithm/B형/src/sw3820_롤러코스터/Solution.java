package sw3820_롤러코스터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	static int N,a,b;
	static long x;
	static Role[] roles;
	
	public static void main(String[] args) throws IOException {
		  BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		  StringTokenizer st= new StringTokenizer(br.readLine());
		  int T=Integer.parseInt(st.nextToken());
		  for(int t=1; t<=T; t++) {
			  x=1;
			  st= new StringTokenizer(br.readLine());
			  N=Integer.parseInt(st.nextToken());
			  roles=new Role[N];
			  for(int i=0; i<N; i++) {
				  st=new StringTokenizer(br.readLine());
				  a=Integer.parseInt(st.nextToken());
				  b=Integer.parseInt(st.nextToken());
				  roles[i]=new Role(a, b);
			  }
			  Arrays.sort(roles);
			  for(int i=0; i<N; i++) {
				  x=(x*roles[i].a+roles[i].b)%1000000007;
			  }
			  System.out.println("#"+t+" "+(x%1000000007));
		  }
	}
	
	static class Role implements Comparable<Role>{
		long a;
		long b;
		public Role(long a, long b) {
			super();
			this.a = a;
			this.b = b;
		}
		@Override
		public int compareTo(Role o) {
			if(this.b*(o.a-1)<o.b*(this.a-1))return -1;
			else return 1;
		}
		
	}

}
