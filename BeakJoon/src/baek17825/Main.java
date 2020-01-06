package baek17825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static int max=0;
	static int go[] = new int[10];
	static int pick[]= new int[10];
	static Node[] player= new Node[4];
	static Node start= new Node(0);
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		initTable();
		
		for(int i=0; i<10; i++){
			go[i]=Integer.parseInt(st.nextToken());
		}
		pick[0]= 0;
		permutation(1);
		System.out.println(max);
	}

	private static void permutation(int depth) {
		if(depth == 10){
			Arrays.fill(player, start);
			max= Math.max(max, simulation());
			recovery();
			return ;
		}
		
		for(int i=0; i<4; i++){
			pick[depth]= i;
			permutation(depth+1);
		}
		
	}

	private static void recovery() {
		for(int i=0; i<4; i++){
			player[i].isEmpty= true;
		}
	}

	private static int simulation() {
		int allSum=0;
		
		for(int i=0; i<4; i++){
			player[i]=start;
		}
		
		for(int i=0; i<10; i++){
			Node n= player[pick[i]];
			n.isEmpty = true;
			for(int j=0; j<go[i]; j++){
				if(j==0 && n.fast != null){
					n= n.fast;
				}else{
					n= n.next;
				}
			}
			
			player[pick[i]]=n;
			
			if(!n.isEmpty && !n.isEnd){
				return 0;
			}else{
				n.isEmpty= false;
				allSum += n.value;
			}
			
		}
		
		return allSum;
	}

	private static void initTable() {
		Node n = start;
		n.isEnd=true;
		for(int i=1; i<21; i++){
			n= n.addNormal(i<<1);
		}
		Node last= n;
		last.next= new Node(0);
		last.next.isEnd=true;
		last.next.next=last.next;
		
		n=start.get(5);
		n.fast=new Node(13);
		n= n.fast;
		
		n= n.addNormal(16);
		n= n.addNormal(19);
		n= n.addNormal(25);
		Node center= n;
		
		n= n.addNormal(30);
		n= n.addNormal(35);
		n.next=last;
		
		n=start.get(10);
		n.fast= new Node(22);
		n=n.fast;
		n= n.addNormal(24);
		n.next= center;
		
		n= start.get(15);
		n.fast= new Node(28);
		n= n.fast;
		n = n.addNormal(27);
		n= n.addNormal(26);
		n.next= center;
			
	}
	
	static class Node{
		int value;
		boolean isEmpty, isEnd;
		Node next, fast;
		
		public Node(int value) {
			this.value = value;
			this.isEmpty = true;
			this.isEnd = false;
			this.next = null;
			this.fast = null;
		}
		
		Node addNormal(int num){
			Node temp = new Node(num);
			temp.isEmpty=true;
			this.next=temp;
			return temp;
		}
		
		Node get(int num){
			Node n= start;
			for(int i=0; i<num; i++){
				n=n.next;
			}
			return n;
		}
	}
}
