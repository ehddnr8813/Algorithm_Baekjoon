package pro_hash2;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

	public static void main(String[] args) {
		String[] book= new String[]{"12", "123", "1235"};
		System.out.println(solution(book));
	}
	
    public static boolean solution(String[] phone_book) {
		Arrays.sort(phone_book, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				return o1.length()-o2.length();
			}	
		});
		for(int i=0; i<phone_book.length-1; i++){
			for(int j=i+1; j<phone_book.length; j++){
				if(phone_book[j].startsWith(phone_book[i]))return false;
			}
		}
        return true;
    }
}
