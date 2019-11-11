package sw2117;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution {


	static ArrayList<Node> list;
	static int size, profit;
	static int[] cost = new int[41];
	static int result;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);
		int tnum = input.nextInt();

		for (int i = 1; i < 41; i++) {
			cost[i] = i * i + (i - 1) * (i - 1);
		}

		for (int t = 1; t <= tnum; t++) {
			size = input.nextInt();
			profit = input.nextInt();
			list = new ArrayList<>();
			result = 0;

			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (input.nextInt() == 1) {
						list.add(new Node(i, j));
					}
				}
			}
		
			calc();
			System.out.println("#" + t + " " + result);
		}

	}

	private static void calc() {

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				int[] temp = new int[size + size];
				distance(temp, i, j);

				int sum = 0;
				for (int k = 0; k < size + size; k++) {
					sum += temp[k];
					if (sum * profit >= cost[k + 1] && sum > result) {
						result = sum;
					}
				}
			}
		}
	}

	private static void distance(int[] temp, int x, int y) {

		for (int i = 0; i < list.size(); i++) {
			temp[Math.abs(list.get(i).x - x) + Math.abs(list.get(i).y - y)]++;
		}

	}
	
	private static class Node {
		int x;
		int y;
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

}


