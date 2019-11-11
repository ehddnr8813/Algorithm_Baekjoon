package baek16637;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, answer=Integer.MIN_VALUE;
	static char[] str;
	static int visited[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		str= new char[N];
		visited= new int[N];
		str= br.readLine().toCharArray();
		dfs(0);
		System.out.println(answer);
	}

	private static void dfs(int idx) {
		answer=Math.max(answer,simulate());
		for(int i=idx; i<=N; i+=2) {
			if(i+2<N && visited[i]==0 && visited[i+2]==0) {	
				visited[i]=2;
				visited[i+2]=1;
				dfs(i+2);
				visited[i]=0;
				visited[i+2]=0;
			}
		}
	}

	private static int simulate() {
		Queue<Character> array= new LinkedList<>();
		for(int i=0; i<N; i++) {
			if(visited[i]==2) {
				array.add((char) (calculate(str[i]-'0', str[i+2]-'0', str[i+1])+'0'));
				i+=2;
			}
			else if(visited[i]==0){
				array.add(str[i]);
			}
		}
		int res=array.poll()-'0';
		while(!array.isEmpty()) {
			char operation=array.poll();
			char operand=array.poll();
			if(operation=='*') {
				res=res*(operand-'0');
			}
			else if(operation=='+') {
				res+=operand-'0';
			}
			else if(operation=='-') {
				res-=operand-'0';
			}
		}
		return res;
	}

	static int calculate(int a, int b, char op) {
		switch (op) {
		case '+':
			return a+b;
		case '-':
			return a-b;
		case '*':
			return a*b;
		default:
			break;
		}
		return -1;
	}
	
}
