package baek14500;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M, answer;
    static int [][] map;
    static boolean visited[][];
    static int dx[] = new int[]{1,0,-1,0};
    static int dy[] = new int[]{0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map= new int[N][M];
        visited= new boolean[N][M];
        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                visited[i][j]=true;
                dfs(j , i,0, 0);
                visited[i][j]=false;
                calCenter(j,i);
            }
        }
        System.out.println(answer);
    }

    private static void calCenter(int x, int y) {
        for(int i=0; i<4; i++){
            int total=map[y][x];
            boolean flag= true;
            for(int j=0; j<3; j++){
                int nx=x+dx[(i+j)%4];
                int ny=y+dy[(i+j)%4];
                if(nx<0 || ny<0 || nx>=M || ny>=N){
                    flag=false;
                    break;
                }
                total+=map[ny][nx];
            }
            if(flag){
                answer = answer > total ? answer : total;
            }
        }

    }

    private static void dfs(int x, int y, int count, int total) {
        if(count==4){
            answer = answer > total ? answer : total;
            return;
        }
        for(int k=0; k<4; k++){
            int nx=x+dx[k];
            int ny=y+dy[k];
            if(nx<0 || ny<0 || nx>=M || ny>=N || visited[ny][nx])continue;
            visited[ny][nx]=true;
            dfs(nx, ny, count+1, total+map[ny][nx]);
            visited[ny][nx]=false;
        }
    }
}
