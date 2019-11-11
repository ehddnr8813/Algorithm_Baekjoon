package pro가장큰수;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {
    
	public static String solution(int[] numbers) {
		String answer = "";
		ArrayList<Integer> list= new ArrayList<>();
		for(int i=0; i<numbers.length;i++) {
			list.add(numbers[i]);
		}
		Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -1*(Integer.parseInt(o1.toString()+o2.toString())-Integer.parseInt(o2.toString()+o1.toString()));
			}
		});
		for(int i=0; i<list.size(); i++) {
			if(list.get(0)==0) {
				answer="0";
				break;
			}
			answer+=list.get(i);
		}
        return answer;
    }
	
	public static void main(String[] args) {
		int arr[]= new int[] {0,0};
		System.out.println(solution(arr))	;
	}

}
