package wintercoding;

import java.util.ArrayList;

public class Solution {

	static int dx[]= new int[]{1,0,-1,0};
	static int dy[]= new int[]{0,1,0,-1};
	
	static int[][] visited;
	static boolean[][] check;
	static boolean[] visitIsland;
	static int N,count=1, diff, answer=Integer.MAX_VALUE;
	static int map[][];
	
	static int dx2[] = new int[]{1,0};
	static int dy2[]= new int[]{0,1};
	
	public static void main(String[] args) {
		solution(new int[][]{{2,3,4,7,8},{12,13,14,9,10},{35,36,37,25,26},{40,18,19,20,27},{41,42,30,29,28}},2);

	}
	
    public static int solution(int[][] land, int height) {
    	N=land.length;
    	diff=height;
    	map= new int[N][N];
    	for(int i=0; i<N; i++){
    		map[i]=land[i].clone();
    	}
    	simulate();
        int answer = 0;
        return answer;
    }

	private static void simulate() {
		visited=new int[N][N];
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				if(visited[i][j]==0){
					visited[i][j]=count;
					dfs(j,i, count);
					count++;
				}
			}
		}
		visitIsland= new boolean[count];
		dfs2(0,0,0);
		System.out.println(answer);
	}
	
	private static void dfs2(int idx, int count, int total) {
		int y=idx/N;
		int x=idx%N;
		if(total>answer || idx==N*N)return;
		if(allVisited()){
			answer= answer > total ? total: answer;
		}
		for(int k=0; k<2; k++){
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx<0 || ny<0 ||nx>=N || ny>=N || visited[y][x]==visited[ny][nx] || (visitIsland[visited[ny][nx]]&& visitIsland[visited[y][x]]))continue;
			visitIsland[visited[ny][nx]]=true;
			visitIsland[visited[y][x]]=true;
			dfs2(idx+1, count+1, total+Math.abs(map[y][x]-map[ny][nx]));
			visitIsland[visited[ny][nx]]=false;
			visitIsland[visited[y][x]]=false;
			
		}
		dfs2(idx+1, count, total);

	}

	private static void dfs(int x, int y, int count) {
		for(int k=0; k<4; k++){
			int nx=x+dx[k];
			int ny=y+dy[k];
			if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx]!=0)continue;
			if(Math.abs(map[ny][nx]-map[y][x])>diff)continue;
			visited[ny][nx]=count;
			dfs(nx, ny, count);
		}
	}
	
	static boolean allVisited(){
		for(int i=1; i<count; i++){
			if(!visitIsland[i])return false;
		}
		return true;
	}

	static class Node{
		int x;
		int y;
		public Node(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

}
