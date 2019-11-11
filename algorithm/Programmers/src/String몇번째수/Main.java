package String몇번째수;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int[] fact= new int[11];
	
	static String findPlace(ArrayList<Integer> list, int k) {
		int size=list.size();
		if(size==1)return list.get(0)+"";
		
		int partition=fact[size-1];
		int index=(k-1)/partition;
		
		int number=list.get(index);
		list.remove(index);
		return number+findPlace(list, k-(partition*index));
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<Integer> arr= new ArrayList<>();
		String str[]=sc.nextLine().split(" ");
		for(int i=0; i<str.length; i++) {
			arr.add(Integer.parseInt(str[i]));
		}
		arr.sort(null);
		fact[0]=1;
		for(int i=1; i<11; i++) {
			fact[i]=fact[i-1]*i;
		}
		System.out.println(findPlace(arr, 17));
	}

}
