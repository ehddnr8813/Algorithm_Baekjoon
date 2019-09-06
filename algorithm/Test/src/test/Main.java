package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Main {
    static int N;
    static int M;
    static int[][] array, map;
    static boolean check[][];
    static int count;
    static int dir[][] = { { 1, 0 }, { -1, 0 }, { 0, 1 }, { 0, -1 } };
    
    static boolean zeroCount(int map[][]) {
        boolean flag = true;
        
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=M;j++) {
                if(array[i][j]!=0) {
                    flag = false;
                }
            }
        }
        
        return flag;
    }
    static int riverCount(int x, int y) {       
        int river = 0;
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx>=1 && ny>=1 && ny<=M && nx<=N && array[nx][ny] == 0)
                river++;
        }
        return river;
    }
    
    static void dfs(int x, int y) {
        
        for (int i = 0; i < 4; i++) {
            int nx = x + dir[i][0];
            int ny = y + dir[i][1];
            if (nx>=1 && ny>=1 && ny<=M && nx<=N && array[nx][ny] != 0 && check[nx][ny] == false) {
                check[nx][ny] = true;
                count--;
            	int z = riverCount(nx, ny);
                if (map[nx][ny] <= z) {
                    map[nx][ny] = 0;

                    dfs(nx, ny);    
                    
                } else {
                    map[nx][ny] = map[nx][ny] - z;
                    dfs(nx, ny);
                
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        array = new int[N + 1][M + 1];
        check = new boolean[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                array[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int start = 1;
        while (true) {
        	count=0;
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (array[i][j] != 0)
                        count++;
                }
            }
            
            map=new int[N+1][M+1];
            for(int i=1; i<=N; i++) {
            	map[i]=array[i].clone();
            }
            
            OUTER: for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (map[i][j] != 0) {
                    	dfs(i, j);
                        break OUTER;
                    }
                }
            }
            if(count!=0) {
                break;
            }
            
            check = new boolean[N+1][M+1];
            count = 0;
            start++;
            for(int i=1; i<=N; i++) {
            	array[i]=map[i].clone();
            }

            if(zeroCount(array)==true) {
                start = 1;
                break;
            }
            
        }
        System.out.println(start-1);
    }
}