package pro_hash1;

import java.util.HashMap;

public class Solution2 {

	static HashMap<String, Integer> hash = new HashMap<>();
	public static void main(String[] args) {
		String s=solution(new String[]{"mislav", "stanko", "mislav", "ana"}, new String[]{"stanko", "ana", "mislav"});
		System.out.println(s);
	}
    public static String solution(String[] participant, String[] completion) {
    	for(String s: participant){
    		hash.put(s, hash.getOrDefault(s, 0)+1);
    	}
    	for(String s: completion){
    		hash.put(s,hash.get(s)-1);
    	}
    	for(String key : hash.keySet()){
    		if(hash.get(key)!=0){
    			return key;
    		}
     	}
    	return "";
    }

}
