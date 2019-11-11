package pro1_11¹ø°¡;

import java.util.Arrays;

public class Solution {

	static String current;
	static int max=Integer.MIN_VALUE, answer;
	static int answerK;
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(solution(14)));
	}
	
    static void dfs(int input, int n) {
    	if( input/n >0 ) {
            dfs(input/n, n);
        }
        if( input/n>0) {
            current+=input%n+"";
        }else {
            current+=input+"";
        }
    }

	static public int[] solution(int N){
		answer=Integer.MIN_VALUE;
		for(int k=2; k<=9 ; k++){
			current="";
			dfs(N,k);
			int curNum=mul(current);
			if(curNum >=answer){
				answer=curNum;
				answerK=k;
			}
		}
		int [] ret = {answerK ,answer};
		return ret;
	}
	
	static public int mul(String s){
		int answer=1;
		for(int i=0; i<s.length(); i++){
			if(s.charAt(i)-'0'==0)continue;
			answer*=(s.charAt(i)-'0');
		}
		return answer;
	}
	
	
	
}
