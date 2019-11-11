package sw2477;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {

	static int N,M,K,A,B,count, total;
	static Node[] recep, repair;
	static Queue<Person> waiting;
	static Queue<Person> waiting2;
	static int [][] answer;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st= new StringTokenizer(br.readLine());
		int T=Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st= new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			M=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			A=Integer.parseInt(st.nextToken());
			B=Integer.parseInt(st.nextToken());
			count=0;total=0;
			recep=new Node[N];
			repair=new Node[M];
			waiting= new LinkedList<>();
			waiting2= new LinkedList<>();
			answer = new int[K][2];
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				recep[i]= new Node(Integer.parseInt(st.nextToken()), new LinkedList<Person>());
			}
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<M; i++) {
				repair[i]= new Node(Integer.parseInt(st.nextToken()), new LinkedList<Person>());
			}
			st= new StringTokenizer(br.readLine());
			for(int i=0; i<K; i++) {
				waiting.add(new Person(i+1, Integer.parseInt(st.nextToken())));
			}
			int time=0;
			while(count!=K) {
				for(int i=0; i<N; i++) {
					if(!recep[i].q.isEmpty()) {
						if(time==recep[i].q.peek().time) {
							waiting2.add(recep[i].q.poll());
						}
					}
				}

				for(int i=0; i<M; i++) {
					if(!repair[i].q.isEmpty()) {
						if(time==repair[i].q.peek().time) {
							Person p=repair[i].q.poll();
							count++;
						}
					}
				}				
				if(!waiting.isEmpty() && waiting.peek().time<=time) {
					for(int i=0; i<N; i++) {
						if(recep[i].q.isEmpty() && !waiting.isEmpty() && waiting.peek().time<=time) {
							Person p=waiting.poll();
							answer[p.id-1][0]=i+1;
							p.time=time+recep[i].t;
							recep[i].q.add(p);
						}
					}
				}
				
				if(!waiting2.isEmpty()) {
					for(int i=0; i<M; i++) {
						if(repair[i].q.isEmpty() && !waiting2.isEmpty() && waiting2.peek().time<=time) {
							Person p=waiting2.poll();
							answer[p.id-1][1]=i+1;
							p.time=time+repair[i].t;
							repair[i].q.add(p);
						}
					}
				}
				time++;
			}
			for(int i=0; i<K; i++) {
				if(answer[i][0]==A && answer[i][1]==B)total+=(i+1);
			}
			if(total!=0)System.out.println("#"+t+" "+total);
			else System.out.println("#"+t+" "+-1);
		}
	}
	

	static class Person{
		int id;
		int time;
		public Person(int id, int time) {
			super();
			this.id = id;
			this.time = time;
		}

		@Override
		public String toString() {
			return "Person [id=" + id + ", time=" + time + "]";
		}
		
	}
	
	static class Node{
		int t;
		Queue<Person> q;
		public Node(int t, Queue<Person> pq) {
			super();
			this.t = t;
			this.q = pq;
		}
	}


}
