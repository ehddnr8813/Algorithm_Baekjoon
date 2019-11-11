package bruteforce;

import java.util.ArrayList;

public class Solution3 {

	static int number[]=new int[3];
	static int[] ball;
	static int[] strike;;
	static int[][] b;
	static int query, answer;
	
	
	public static void main(String[] args) {
		solution(new int[][]{{123,1,1},{356,1,0},{327,2,0},{489,0,1}});
		System.out.println(answer);
	}
	
    public static int solution(int[][] baseball) {
        query=baseball.length;
        ball= new int[query];
        strike= new int[query];
        b= new int[query][3];
        for(int i=0; i<query; i++) {
        	int num=baseball[i][0];
        	b[i][0]=num/100;
        	b[i][1]=(num%100)/10;
        	b[i][2]=num%10;
        	strike[i]=baseball[i][1];
        	ball[i]=baseball[i][2];
        } 
        simulate();
        return answer;
    }
	
    static void simulate() {
    	for(int i=1; i<=9; i++) {
    		number[0]=i;
    		for(int j=1; j<=9; j++) {
    			if(i==j)continue;
    			number[1]=j;
    			for(int k=1; k<=9 ;k++) {
    				if(j==k || k==i)continue;
    				number[2]=k;
    				if(isPossible()) {
    					answer++;
    				}
    			}
    		}
    	}
    }
    
    private static boolean isPossible() {
		boolean flag=true;
		for(int i=0; i<query; i++) {
			if(!makeInning(b[i], strike[i] ,ball[i])) {
				flag=false;
				break;
			}
		}
		return flag;
	}

	static boolean makeInning(int arr[], int strikes, int ball) {
    	int s=0, b=0;
    	for(int i=0; i<arr.length; i++) {
    		for(int j=0; j<number.length; j++) {
    			if(number[j]!=arr[i])continue;
    			if(i==j)s++;
    			else b++;
    		}
    	}
    	return s==strikes && b==ball ? true : false;
    }
}
