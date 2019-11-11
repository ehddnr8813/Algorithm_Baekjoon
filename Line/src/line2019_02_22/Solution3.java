package line2019_02_22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution3 {

	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int N=Integer.parseInt(st.nextToken());
		int startIndex=0, finIndex=0, count=0, room=0;//count==지금 사람수, room은 answer
		int[] start= new int[N];
		int[] fin= new int[N];
		for(int i=0; i<N; i++){
			st= new StringTokenizer(br.readLine());
			start[i]=Integer.parseInt(st.nextToken());
			fin[i]=Integer.parseInt(st.nextToken());
		}
		Arrays.sort(start);
		Arrays.sort(fin);
		while(finIndex<N){
			if(startIndex==N || start[startIndex]>=fin[finIndex]){
				count--;
				finIndex++;
			}
			else{
				count++;
				startIndex++;
				room = count > room ? count : room;
			}
			
		}
		System.out.println(room);	
	}
	

}


