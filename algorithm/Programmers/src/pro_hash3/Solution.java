package pro_hash3;

import java.util.HashMap;

public class Solution {

	static HashMap<String, Integer> hash= new HashMap<>();

	public static void main(String[] args) {
		System.out.println(solution(new String[][]{{"yellow_hat", "headgear"},{"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}}));
	}
	
	public static int solution(String[][] clothes) {
		for(String[] s : clothes){
			hash.put(s[1], hash.getOrDefault(s[1], 0)+1);
		}
		int count=1;
		for(String s : hash.keySet()){
			count*=(hash.get(s)+1);
		}
        return count-1;		
    }
	
	
    
}