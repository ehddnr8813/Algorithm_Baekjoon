package baek3954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.StringTokenizer;


public class Main {

	static int T, size,query, dummy, pointer, cur, start, end, asciPointer, loop;
	static char input[];
	static String asci;
	static int[] pointerSize, indexList, countList;
	static boolean flag;
	
	static Stack<Integer> stack;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		T=Integer.parseInt(st.nextToken());
		while(T-->0) {
			st = new StringTokenizer(br.readLine());
			pointer=0; cur=0; flag=true; start=0; end=0; asciPointer=0; loop=0;
			size=Integer.parseInt(st.nextToken());
			query= Integer.parseInt(st.nextToken());
			dummy=Integer.parseInt(st.nextToken());
			pointerSize= new int[size];
			indexList= new int[query];
			countList= new int[query];
			input= new char[query];
			
			input=br.readLine().toCharArray();
			asci=br.readLine();
			
			findLoop();
			go();
		}
	}

	private static void go() {
		simul();
		if(loop == 50000000) {
			System.out.print("Loops ");
			findIter();
		}
		else System.out.println("Terminates");
	}

	private static void findIter() {
		for(int i=query-1; i>0; i--) {
			if(countList[i]>=1) {
				System.out.println(indexList[i]+" "+i);
				break;
			}
		}
	}

	private static void findLoop() {
		stack= new Stack<Integer>();
		for(int i=0; i<query; i++) {
			switch (input[i]) {
			case '[':
				stack.add(i);
				break;
			case ']':
				int temp=stack.pop();
				indexList[temp]=i;
				indexList[i]=temp;
				break;
			}
		}
	}

	private static void simul() {
		while(cur!=query && loop< 50000000) {
			loop++;
			switch(input[cur]) {
			case '.':
				cur++;
				continue;
			case '>':
				pointer= (pointer+size+1) % size;
				break;
			case '<':
				pointer= (pointer+size-1) % size;
				break;
			case '+':
				pointerSize[pointer] = (pointerSize[pointer] + 1) % 256;  
				break;
			case '-':
				pointerSize[pointer] = (pointerSize[pointer] + 255) % 256;  
				break;
			case '[':
				if(pointerSize[pointer]==0) {
					cur=indexList[cur];
					continue;
				}
				break;
			case ']':
				if(pointerSize[pointer]!=0) {
					countList[cur]++;
					cur=indexList[cur];
					continue;
				}
				break;
			case ',':
				if(asci.length()==asciPointer) {
					pointerSize[pointer]=255;
				}
				else {
					pointerSize[pointer]=asci.charAt(asciPointer);
					asciPointer++;
				}
			}
			cur++;
		}
	}
	

}
