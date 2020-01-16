package baek1018;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N,M, answer=Integer.MAX_VALUE;
    static int startX, startY;
    static char [][] map;
    static char[][] white= new char[][]{{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'}};
    static char[][] black= new char[][]{{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'},{'B','W','B','W','B','W','B','W'},{'W','B','W','B','W','B','W','B'}};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N=Integer.parseInt(st.nextToken());
        M=Integer.parseInt(st.nextToken());
        map=new char [N][M];
        for(int i=0; i<N; i++){
            st= new StringTokenizer(br.readLine());
            String s=st.nextToken();
            for(int j=0; j<M; j++){
                map[i]=s.toCharArray();
            }
        }
        for(int i=0; i<=N-8 ;i++){
            for(int j=0; j<=M-8; j++){
                blackDif();
                whiteDif();

                startX++;
                if(startX>

                        M-8){
                    startX=0;
                    startY++;
                }
            }
        }
        System.out.println(answer);
    }

    private static void blackDif() {
        int count=0;
        for(int i=startY; i<startY+8; i++){
            for(int j=startX; j<startX+8; j++){
                if(map[i][j]!=black[i-startY][j-startX]){
                    count++;
                }
            }
        }
        answer = answer > count ? count : answer;
    }

    private static void whiteDif() {
        int count=0;
        for(int i=startY; i<startY+8; i++){
            for(int j=startX; j<startX+8; j++){
                if(map[i][j]!=white[i-startY][j-startX]){
                    count++;
                }
            }
        }
        answer = answer > count ? count : answer;
    }

}
