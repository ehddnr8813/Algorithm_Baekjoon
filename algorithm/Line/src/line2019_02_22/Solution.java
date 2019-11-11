package line2019_02_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int M=Integer.parseInt(st.nextToken());
		int[] work=new int[N];
		int left=0;
		for(int i=0; i<N; i++){
			st= new StringTokenizer(br.readLine());
			work[i]=Integer.parseInt(st.nextToken());
		}
		Queue<Integer>[] consumer= new Queue[M];
		for(int i=0; i<M; i++){
			consumer[i]=new LinkedList<>();
		}
		int time=0;
		boolean flag=true;
		while(flag){
			flag=false;
			for(int i=0; i<M; i++){
				if(consumer[i].isEmpty() && left==N){
					continue;
				}
				else if(consumer[i].isEmpty() && left!=N){
					consumer[i].add(work[left]);
					left++;
					flag=true;
				}
				else{
					int run=consumer[i].poll();
					if(run==1){
						if(left!=N){
							consumer[i].add(work[left]);
							left++;
							flag=true;
						}
						continue;
					}
					else {
						consumer[i].add(run-1);
						flag=true;
					}
				}
			}
			time++;
		}
		System.out.println(time-1);
	}

}
