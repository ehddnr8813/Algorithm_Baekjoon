	package sw1952;
	
	import java.io.BufferedReader;
	import java.io.IOException;
	import java.io.InputStreamReader;
	import java.util.StringTokenizer;
	
	public class Main {
		
		static int T;
		static int cost[]= new int[4];
		static int month[]= new int[12];
		static int answer=Integer.MAX_VALUE;
	
		public static void main(String[] args) throws IOException {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st= new StringTokenizer(br.readLine());
			T=Integer.parseInt(st.nextToken());
			for(int t=1; t<=T; t++) {
				st=new StringTokenizer(br.readLine());
				for(int i=0; i<4; i++) cost[i]=Integer.parseInt(st.nextToken());
				st= new StringTokenizer(br.readLine());
				for(int i=0; i<12; i++)month[i]=Integer.parseInt(st.nextToken());
				answer=cost[3];
				dfs(0, 0);
				System.out.println("#"+t+" "+answer);
			}
		}
	
		private static void dfs(int idx, int total) {
			if(idx>=12) {
				answer=Math.min(total, answer);
				return;
			}	
			dfs(idx+1, total+month[idx]*cost[0]);
			dfs(idx+1, total+cost[1]);				
			dfs(idx+3, total+cost[2]);
		}
	
	}
