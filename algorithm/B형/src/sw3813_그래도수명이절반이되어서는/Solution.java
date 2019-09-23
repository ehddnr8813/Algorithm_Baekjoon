package sw3813_그래도수명이절반이되어서는;

import java.util.Scanner;

public class Solution {

	static int N,K;
	static int[] arr, groups;
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		int T=sc.nextInt();
		for(int t=1; t<=T; t++) {
			N=sc.nextInt();
			K=sc.nextInt();
			arr= new int[N];
			groups= new int[K];
			for(int i=0; i<N; i++) {
				arr[i]=sc.nextInt();
			}
			for(int i=0; i<K; i++) {
				groups[i]=sc.nextInt();
			}
		}
	}

}
