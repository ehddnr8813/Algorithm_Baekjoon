package baek1976;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N,M;
    static int[] parents, rank;
    static boolean flag=true;

    static int visited[];
    static int [][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        map= new int[N][N];
        parents= new int[N];
        rank= new int[N];
        for(int i=0; i<N; i++){
            parents[i]=i;
            rank[i]=0;
        }
        st = new StringTokenizer(br.readLine());
        M=Integer.parseInt(st.nextToken());
        visited=new int[M];
        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0; j<i; j++){
                map[i][j]=Integer.parseInt(st.nextToken());
                if(map[i][j]==1){
                    union(i, j);
                }
            }
        }
        st=new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            visited[i]=Integer.parseInt(st.nextToken())-1;
        }
        for(int i=0; i<M-1; i++){
            if(find(visited[i])!=find(visited[i+1])){
                flag=false;
                break;
            }
        }
        if(flag) System.out.println("YES");
        else System.out.println("NO");
    }

    static int find(int n){
        if(n==parents[n]){
            return n;
        }
        return parents[n]= find(parents[n]);
    }

    static boolean union(int a, int b){
        int x= find(a);
        int y= find(b);
        if(x==y){
            return false;
        }

        if(rank[x]>rank[y]){
            parents[y]=x;
        }
        else{
            parents[x]=y;
            if(rank[x]==rank[y]){
                rank[y]++;
            }
        }
        return true;
    }
}
