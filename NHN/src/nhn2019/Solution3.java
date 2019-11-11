package nhn2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution3 {

	static int N, idx;
	static ArrayList<Integer> [] list;
	static int[] arr;
	static boolean[] visited;
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		list= new ArrayList[N];
		arr= new int[N];
		for(int i=0; i<N; i++){
			list[i]= new ArrayList<>();
		}
		st= new StringTokenizer(br.readLine());
		while(st.hasMoreTokens()){
			String s= st.nextToken();
			if(s.equals("A")){
				visited= new boolean[N];
				arr[idx]++;
				visited[idx]=true;
				for(int i=0; i<list[idx].size();i++){
					int follow=list[idx].get(i);
					arr[follow]++;
					followed(follow);
				}
			}
			else if(s.equals("J")){
				if(idx>=1){
					arr[idx-1]++;
					for(int i=0; i<list[idx-1].size();i++){
						int follow=list[idx-1].get(i);
						arr[follow]++;
					}
				}
				if(idx<N-1){
					arr[idx+1]++;
					for(int i=0; i<list[idx+1].size();i++){
						int follow=list[idx+1].get(i);
						arr[follow]++;
					}
				}
				if(idx==0){
					arr[N-1]++;
					for(int i=0; i<list[N-1].size();i++){
						int follow=list[N-1].get(i);
						arr[follow]++;
					}
				}
				if(idx==N-1){
					arr[0]++;
					for(int i=0; i<list[0].size();i++){
						int follow=list[0].get(i);
						arr[follow]++;
					}
				}
			}
			else if(s.equals("Q")){
				for(int i=0; i<N; i++){
					arr[i]++;
				}
			}
			else{
				int num=Integer.parseInt(st.nextToken());
				list[idx].add(num);
			}
			idx=(idx+1)%5;
		}
		for(int i=0; i<N; i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	static void followed(int idx){
		visited[idx]=true;
		for(int i=0; i<list[idx].size();i++){
			arr[list[idx].get(i)]++;
			if(!visited[list[idx].get(i)]){
				followed(list[idx].get(i));
			}
		}
	}

}
