package nhn2019;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Solution {

	static HashMap<String, Integer> hash= new HashMap<>();
	static int N;
	static boolean visited[];
	static boolean flag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		N=Integer.parseInt(st.nextToken());
		st=new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			String s= st.nextToken();
			if(hash.containsKey(s)){
				int cur=hash.get(s);
				hash.put(s, cur+1);
			}
			else hash.put(s, 1);
		}
       flag=true;
       ArrayList keys = new ArrayList(hash.keySet());
       Object obj = keys.get(0);
       int index=hash.get(obj);
       for( String key : hash.keySet() ){
    	   if(index!=hash.get(key)){
    		   flag=false;
    		   break;
    	   }
       }
	    if(flag){
	    	System.out.println("Y");
	    	System.out.println(N);
	    	System.out.println(hash.size());
	    	return;
	    }
       visited= new boolean[N];
       dfs(0);
	    if(flag){
	    	System.out.println("Y");
	    	System.out.println(N+1);
	    	System.out.println(hash.size());
	    }
	    else{
	    	System.out.println("N");
	    	System.out.println(N);
	    	System.out.println(hash.size());
	    }
	}

	private static void dfs(int count) {
		if(count==1){
			int cur = 0;
			for(int i=0; i<hash.size(); i++){
				if(visited[i]){
					cur=i;
					break;
				}
			}
			if(simulate(cur))flag=true;
			return;
		}
		for(int i=0; i<hash.size(); i++){
			if(!visited[i]){
				visited[i]=true;
				dfs(count+1);
				visited[i]=false;
			}
		}
	}

	private static boolean simulate(int cur) {
		ArrayList keys = new ArrayList(hash.keySet());
	    Object obj = keys.get(cur);
	    int index=hash.get(obj);
	    int left=1;
       for( String key : hash.keySet() ){
    	   if(index==hash.get(key))continue;
    	   else if(index-hash.get(key)==1 && left==1){
    		   left--;
    	   }
    	   else{
    		   return false;
    	   }
        }
       return true;
	}

}
