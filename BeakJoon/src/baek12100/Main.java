package baek12100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Main {

    static int N;
    static int[][] map, temp;
    static boolean[][] visited;

    static int[] dx= new int[]{1,0,-1,0};
    static int[] dy= new int[]{0,1,0,-1};
    static int[] dir= new int[5];

    static int max= Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        map= new int[N][N];
        temp= new int[N][N];

        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        dfs(0);
        System.out.println(max);
    }

    private static void dfs(int depth) {
        if(depth==5){
            max= Math.max(max, move());
            return;
        }
        for(int i=0; i<5; i++){
            dir[depth]=i;
            dfs(depth+1);
        }
    }

    private static int move() {
        for(int i=0; i<N; i++){
            temp[i]=map[i].clone();
        }
        for(int d=0; d<5; d++){
        	visited= new boolean[N][N];
            if(dir[d]==0) {
            	for(int j=N-1; j>=0; j--){
            		for(int i=0; i<N; i++){
            			move(i,j,0);
            		}
            	}
            }else if(dir[d]==1){
            	for(int i=N-1; i>=0; i--){
            		for(int j=0; j<N; j++){
            			move(i,j,1);
            		}
            	}
            }else if(dir[d]==2){
            	for(int j=0; j<N; j++){
            		for(int i=0; i<N; i++){
            			move(i,j,2);
            		}
            	}
            }else{
            	for(int i=0; i<N; i++){
            		for(int j=0; j<N; j++){
            			move(i,j,3);
            		}
            	}
            }
            
        }
        return search();
    }

    private static void move(int y, int x, int d) {
		if(temp[y][x]==0)return;
		while(true){
			int nx=x+dx[d];
			int ny=y+dy[d];
			if(nx<0 || ny<0 || nx>=N || ny>=N || visited[ny][nx])return;
			if(temp[ny][nx]==temp[y][x]){
				temp[ny][nx]*=2;
				temp[y][x]=0;
				visited[ny][nx]=true;
			}
			else if(temp[ny][nx]==0){
				temp[ny][nx]=temp[y][x];
				temp[y][x]=0;
				y=ny;
				x=nx;
			}
			else if(temp[ny][nx]!=0)return;
		}
	}


    private static int search() {
        int max=Integer.MIN_VALUE;
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(max<temp[i][j])max=temp[i][j];
            }
        }
        return max;
    }

}