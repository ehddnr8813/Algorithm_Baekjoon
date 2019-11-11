package H_index;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Solution {

    public static int solution(int[] citations) {
    	
    	ArrayList<Integer> list= new ArrayList<>();
    	for(int i=0; i<citations.length; i++) {
    		list.add(citations[i]);
    	}
    	Collections.sort(list, new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return -1*(o1-o2);
			}
    	});
    	for(int i=0; i<list.size();i++) {
    		if(i >=list.get(i))return i;
    	}
    	return list.size();
    }
	public static void main(String[] args) {
		int[] arr= new int[] {7};
		System.out.println(solution(arr));
	}
	
	// 1 0 3 5 6
	// 22 42 

}
