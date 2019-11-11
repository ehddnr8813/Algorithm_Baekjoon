package nhn2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution2 {

	static int N;
	static ArrayList<Integer> arr= new ArrayList<>();
	static int[] numbers= new int[101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());

		for(int i=0; i<N; i++){
			st= new StringTokenizer(br.readLine());
			String s=st.nextToken();
			if(s.equals("enqueue")){
				int num=Integer.parseInt(st.nextToken());
				arr.add(num);
				numbers[num]++;
			}
			else if(s.equals("dequeue")){
				if(arr.size()==0){
					System.out.println("-1");
				}
				else{
					int max=Integer.MIN_VALUE;
					for(int j=0; j<arr.size(); j++){
						int idx=arr.get(j);
						max= max > numbers[idx] ? max : numbers[idx];
					}
					for(int j=0; j<arr.size(); j++){
						int idx=arr.get(j);
						if(max==numbers[idx]){
							System.out.print(idx+" ");
							numbers[idx]--;
							arr.remove(j);
							break;
						}
					}
				}
			}
		}
	}
}
