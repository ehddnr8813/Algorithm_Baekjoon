package baek17281;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, score, result;
	static int power[][];
	static int[] selected;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		power=new int[N][9];
		selected= new int[9];
		for(int i=0; i<N; i++) {
			st= new StringTokenizer(br.readLine());
			for(int j=0; j<9; j++) {
				power[i][j]=Integer.parseInt(st.nextToken());
			}
		}
		selected[3]=0;
		dfs(8,1);
		System.out.println(score);
	}

	static void dfs(int visited, int count) {
		if(count==9) {
			result=attack();
			score = result > score ? result : score;
			return;
		}
		
		for(int i=0; i<9 ;i++) {
			if((visited & 1<<i)==0) {
				selected[i]=count;
				dfs((visited | 1<<i),count+1);
			}
		}
		
	}

	private static int attack() {
		int total=0;
		int cur=0;
		int curScore=0;
		for(int i=0; i<N; i++) {
			int cnt=1;
			int outCnt=0;
			while(true) {
				curScore=power[i][selected[cur++%9]];
				if(curScore==0)outCnt++;
				if(outCnt==3)break;
				cnt <<= curScore;
				cnt |=1;
				if(cnt>15) {
					for(int k=16; k<256; k<<=1) {
						if((cnt & k)==0)continue;
						cnt &= ~k;
						total++;
					}
				}
			}
		}
		return total;
	}

}
