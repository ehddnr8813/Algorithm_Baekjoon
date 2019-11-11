package pro_hash1;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

	static HashMap<String, Integer> hash = new HashMap<>();
	public static void main(String[] args) {
		String s=solution(new String[]{"marina", "josipa", "nikola", "vinko", "filipa"}, new String[]{"josipa", "filipa", "marina", "nikola"});
		System.out.println(s);
	}
    public static String solution(String[] participant, String[] completion) {
        for(int i=0; i<participant.length; i++){
        	if(hash.containsKey(participant[i])){
        		int cur=hash.get(participant[i]);
        		hash.replace(participant[i], cur, cur+1);
        	}
        	else hash.put(participant[i], 1);
        }
        for(int i=0; i<completion.length; i++){
        	int cur= hash.get(completion[i]);
        	hash.remove(completion[i]);
        	if(cur>1)hash.put(completion[i], cur-1);
        }
    	ArrayList keys= new ArrayList(hash.keySet());
    	String answer=(String) keys.get(0);
        return answer;
    }
}
