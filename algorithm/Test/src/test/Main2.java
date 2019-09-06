package test;

import java.util.Arrays;
import java.util.Comparator;

public class Main2 {

	public static void main(String[] args) {
		int[] numbers= new int[] {3, 30, 34, 5, 9};
		String answer="";
		boolean flag=false;
        String[] arr=new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            arr[i]=Integer.toString(numbers[i]);
            if(!arr[i].equals("0"))flag=true;
        }
        if(!flag) {
        	System.out.println("0");
        }
		Arrays.sort(arr, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return (o2+o1).compareTo(o1+o2);
			}
		});
		for(int i=0; i<numbers.length; i++) {
			answer+=arr[i];
		}
		System.out.println(answer);
	}
}
