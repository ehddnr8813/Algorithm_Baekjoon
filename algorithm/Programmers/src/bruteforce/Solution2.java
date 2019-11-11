package bruteforce;

import java.util.HashSet;

public class Solution2 {

	static boolean isPrime[], visited[];
	static char input[];
	static int strLen, numLen;
	static HashSet<String> set= new HashSet<>();
	
	public static void main(String[] args) {
		System.out.println(solution("17"));
	}
	
    public static int solution(String numbers) {
    	numLen=numbers.length();
    	visited= new boolean[numLen];
    	input=new char[strLen];
    	input=numbers.toCharArray(); 
    	
    	strLen=(int) Math.pow(10, numLen);
        isPrime=new boolean[strLen];
        calPrime();
        dfs(0, "");
        return set.size();
    }
    
    static void calPrime() {
    	isPrime[0]=true;
    	isPrime[1]=true;
    	for(int i=2; i<strLen; i++) {
    		for(int j=2; i*j<strLen; j++) {
    			isPrime[i*j]=true;
    		}
    	}
    }

    static void dfs(int count, String str) {
    	if(str!="") {
    		int t=Integer.parseInt(str);
    		if(!isPrime[t])set.add(t+"");
    	}
    	if(count==numLen) return;
    	for(int i=0; i<numLen; i++) {
    		if(!visited[i]) {
    			visited[i]=true;
    			dfs(count+1, str+input[i]);
    			visited[i]=false;
    		}
    	}
    }
}
