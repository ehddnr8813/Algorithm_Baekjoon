package bruteforce;

import java.util.ArrayList;
import java.util.Arrays;

public class Solution {

	static int[] person1= new int[] {1,2,3,4,5};
	static int[] person2= new int[] {2,1,2,3,2,4,2,5};
	static int[] person3= new int[] {3,3,1,1,2,2,4,4,5,5};
	
    public static int[] solution(int[] answers) {
    	int [] count=new int[3];
    	int idx1=0, idx2=0, idx3=0;
    	for(int i=0; i<answers.length; i++) {
    		if(answers[i]==person1[idx1]) {
    			count[0]++;
    		}
    		if(answers[i]==person2[idx2]) {
    			count[1]++;
    		}
    		if(answers[i]==person3[idx3]) {
    			count[2]++;
    		}
    		idx1=(idx1+1)%5;
    		idx2=(idx2+1)%8;
    		idx3=(idx3+1)%10;
    	}
    	ArrayList<Integer> temp= new ArrayList<>();
    	
    	int max=Math.max(Math.max(count[0], count[1]), count[2]);
    	if(max==count[0])temp.add(1);
    	if(max==count[1])temp.add(2);
    	if(max==count[2])temp.add(3);
    	int[] answer= new int[temp.size()];
    	for(int i=0; i<temp.size(); i++) {
    		answer[i]=temp.get(i);
    	}
        return answer;
    } 
	
	public static void main(String[] args) {
		int [] arr=solution(new int[] {1,2,2,3,4,4,4,4,5,5});
		System.out.println(Arrays.toString(arr));
	}
}
