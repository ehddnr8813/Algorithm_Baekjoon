package pro2_11¹ø°¡;

public class Solution {

	static boolean[] visited;
	static int [] arr, selected;
	static int size, answer;
	
	public static void main(String[] args) {
		solution(new int[]{4, 15, 20, 10 ,8, 1});
		System.out.println(answer);
	}

	public static int solution(int [] v){
		size=v.length;
		arr= new int[size];
		arr=v.clone();
		selected= new int[size];
		visited= new boolean[size];
		dfs(0);
		return answer;
	}
	
	static void dfs(int count){
		if(count==size){
			int total=simul();
			answer = total > answer ? total : answer;
			return;
		}
		for(int i=0; i<size; i++){
			if(!visited[i]){
				visited[i]=true;
				selected[count]=arr[i];
				dfs(count+1);
				visited[i]=false;
			}
		}
	}

	private static int simul() {
		int current=0;
		for(int i=0; i<size-1; i++){
			current+=Math.abs(selected[i]-selected[i+1]);
		}
		return current;
	}
}
