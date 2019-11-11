package baek5373;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
 
        int T = Integer.parseInt(br.readLine().trim());
 
        for (int tc = 0; tc < T; tc++) {
            char[][] U = new char[3][3]; // 윗면
            Arrays.fill(U[0], 'w');
            Arrays.fill(U[1], 'w');
            Arrays.fill(U[2], 'w');
            char[][] D = new char[3][3]; // 아랫면
            Arrays.fill(D[0], 'y');
            Arrays.fill(D[1], 'y');
            Arrays.fill(D[2], 'y');
            char[][] F = new char[3][3]; // 앞면
            Arrays.fill(F[0], 'r');
            Arrays.fill(F[1], 'r');
            Arrays.fill(F[2], 'r');
            char[][] B = new char[3][3]; // 뒷면
            Arrays.fill(B[0], 'o');
            Arrays.fill(B[1], 'o');
            Arrays.fill(B[2], 'o');
            char[][] L = new char[3][3]; // 왼쪽면
            Arrays.fill(L[0], 'g');
            Arrays.fill(L[1], 'g');
            Arrays.fill(L[2], 'g');
            char[][] R = new char[3][3]; // 오른쪽면
            Arrays.fill(R[0], 'b');
            Arrays.fill(R[1], 'b');
            Arrays.fill(R[2], 'b');
 
            int N = Integer.parseInt(br.readLine().trim());
 
            StringTokenizer st = new StringTokenizer(br.readLine().trim(), " ");
            for (int n = 0; n < N; n++) {
                String str = st.nextToken();
                char vec = str.charAt(0);
                char turn = str.charAt(1);
                char[][] temp1 = new char[3][3];
 
                switch (vec) {
                case 'U':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = U[2][0];
                        temp1[0][1] = U[1][0];
                        temp1[0][2] = U[0][0];
                        temp1[1][0] = U[2][1];
                        temp1[1][1] = U[1][1];
                        temp1[1][2] = U[0][1];
                        temp1[2][0] = U[2][2];
                        temp1[2][1] = U[1][2];
                        temp1[2][2] = U[0][2];
                        for (int i = 0; i < 3; i++) {
                            U[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = B[0][2];
                        char b = B[0][1];
                        char c = B[0][0];
                        B[0][2] = L[0][0];
                        B[0][1] = L[0][1];
                        B[0][0] = L[0][2];
                        L[0][0] = F[0][0];
                        L[0][1] = F[0][1];
                        L[0][2] = F[0][2];
                        F[0][0] = R[0][0];
                        F[0][1] = R[0][1];
                        F[0][2] = R[0][2];
                        R[0][0] = a;
                        R[0][1] = b;
                        R[0][2] = c;
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = U[0][2];
                        temp1[0][1] = U[1][2];
                        temp1[0][2] = U[2][2];
                        temp1[1][0] = U[0][1];
                        temp1[1][1] = U[1][1];
                        temp1[1][2] = U[2][1];
                        temp1[2][0] = U[0][0];
                        temp1[2][1] = U[1][0];
                        temp1[2][2] = U[2][0];
                        for (int i = 0; i < 3; i++) {
                            U[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = B[0][0];
                        char b = B[0][1];
                        char c = B[0][2];
                        B[0][0] = R[0][2];
                        B[0][1] = R[0][1];
                        B[0][2] = R[0][0];
                        R[0][2] = F[0][2];
                        R[0][1] = F[0][1];
                        R[0][0] = F[0][0];
                        F[0][2] = L[0][2];
                        F[0][1] = L[0][1];
                        F[0][0] = L[0][0];
                        L[0][2] = a;
                        L[0][1] = b;
                        L[0][0] = c;
                    }
                    break;
 
                case 'D':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = D[0][2];
                        temp1[0][1] = D[1][2];
                        temp1[0][2] = D[2][2];
                        temp1[1][0] = D[0][1];
                        temp1[1][1] = D[1][1];
                        temp1[1][2] = D[2][1];
                        temp1[2][0] = D[0][0];
                        temp1[2][1] = D[1][0];
                        temp1[2][2] = D[2][0];
                        for (int i = 0; i < 3; i++) {
                            D[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = F[2][2];
                        char b = F[2][1];
                        char c = F[2][0];
                        F[2][2] = L[2][2];
                        F[2][1] = L[2][1];
                        F[2][0] = L[2][0];
                        L[2][2] = B[2][0];
                        L[2][1] = B[2][1];
                        L[2][0] = B[2][2];
                        B[2][0] = R[2][2];
                        B[2][1] = R[2][1];
                        B[2][2] = R[2][0];
                        R[2][2] = a;
                        R[2][1] = b;
                        R[2][0] = c;
                        
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = D[2][0];
                        temp1[0][1] = D[1][0];
                        temp1[0][2] = D[0][0];
                        temp1[1][0] = D[2][1];
                        temp1[1][1] = D[1][1];
                        temp1[1][2] = D[0][1];
                        temp1[2][0] = D[2][2];
                        temp1[2][1] = D[1][2];
                        temp1[2][2] = D[0][2];
                        
                        for (int i = 0; i < 3; i++) {
                            D[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = F[2][0];
                        char b = F[2][1];
                        char c = F[2][2];
                        F[2][0] = R[2][0];
                        F[2][1] = R[2][1];
                        F[2][2] = R[2][2];
                        R[2][0] = B[2][2];
                        R[2][1] = B[2][1];
                        R[2][2] = B[2][0];
                        B[2][2] = L[2][0];
                        B[2][1] = L[2][1];
                        B[2][0] = L[2][2];
                        L[2][0] = a;
                        L[2][1] = b;
                        L[2][2] = c;
                    }
                    break;
 
                case 'F':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = F[2][0];
                        temp1[0][1] = F[1][0];
                        temp1[0][2] = F[0][0];
                        temp1[1][0] = F[2][1];
                        temp1[1][1] = F[1][1];
                        temp1[1][2] = F[0][1];
                        temp1[2][0] = F[2][2];
                        temp1[2][1] = F[1][2];
                        temp1[2][2] = F[0][2];
                        for (int i = 0; i < 3; i++) {
                            F[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[2][2];
                        char b = U[2][1];
                        char c = U[2][0];
                        U[2][2] = L[0][2];
                        U[2][1] = L[1][2];
                        U[2][0] = L[2][2];
                        L[0][2] = D[2][0];
                        L[1][2] = D[2][1];
                        L[2][2] = D[2][2];
                        D[2][0] = R[2][0];
                        D[2][1] = R[1][0];
                        D[2][2] = R[0][0];
                        R[2][0] = a;
                        R[1][0] = b;
                        R[0][0] = c;
 
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = F[0][2];
                        temp1[0][1] = F[1][2];
                        temp1[0][2] = F[2][2];
                        temp1[1][0] = F[0][1];
                        temp1[1][1] = F[1][1];
                        temp1[1][2] = F[2][1];
                        temp1[2][0] = F[0][0];
                        temp1[2][1] = F[1][0];
                        temp1[2][2] = F[2][0];
                        for (int i = 0; i < 3; i++) {
                            F[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[2][0];
                        char b = U[2][1];
                        char c = U[2][2];
                        U[2][0] = R[0][0];
                        U[2][1] = R[1][0];
                        U[2][2] = R[2][0];
                        R[0][0] = D[2][2];
                        R[1][0] = D[2][1];
                        R[2][0] = D[2][0];
                        D[2][2] = L[2][2];
                        D[2][1] = L[1][2];
                        D[2][0] = L[0][2];
                        L[2][2] = a;
                        L[1][2] = b;
                        L[0][2] = c;
                    }
                    break;
 
                case 'B':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = B[0][2];
                        temp1[0][1] = B[1][2];
                        temp1[0][2] = B[2][2];
                        temp1[1][0] = B[0][1];
                        temp1[1][1] = B[1][1];
                        temp1[1][2] = B[2][1];
                        temp1[2][0] = B[0][0];
                        temp1[2][1] = B[1][0];
                        temp1[2][2] = B[2][0];
                        for (int i = 0; i < 3; i++) {
                            B[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[0][0];
                        char b = U[0][1];
                        char c = U[0][2];
                        U[0][0] = R[0][2];
                        U[0][1] = R[1][2];
                        U[0][2] = R[2][2];
                        R[0][2] = D[0][2];
                        R[1][2] = D[0][1];
                        R[2][2] = D[0][0];
                        D[0][2] = L[2][0];
                        D[0][1] = L[1][0];
                        D[0][0] = L[0][0];
                        L[2][0] = a;
                        L[1][0] = b;
                        L[0][0] = c;
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = B[2][0];
                        temp1[0][1] = B[1][0];
                        temp1[0][2] = B[0][0];
                        temp1[1][0] = B[2][1];
                        temp1[1][1] = B[1][1];
                        temp1[1][2] = B[0][1];
                        temp1[2][0] = B[2][2];
                        temp1[2][1] = B[1][2];
                        temp1[2][2] = B[0][2];
                        for (int i = 0; i < 3; i++) {
                            B[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[0][2];
                        char b = U[0][1];
                        char c = U[0][0];
                        U[0][2] = L[0][0];
                        U[0][1] = L[1][0];
                        U[0][0] = L[2][0];
                        L[0][0] = D[0][0];
                        L[1][0] = D[0][1];
                        L[2][0] = D[0][2];
                        D[0][0] = R[2][2];
                        D[0][1] = R[1][2];
                        D[0][2] = R[0][2];
                        R[2][2] = a;
                        R[1][2] = b;
                        R[0][2] = c;
                        
                    }
                    break;
 
                case 'L':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = L[2][0];
                        temp1[0][1] = L[1][0];
                        temp1[0][2] = L[0][0];
                        temp1[1][0] = L[2][1];
                        temp1[1][1] = L[1][1];
                        temp1[1][2] = L[0][1];
                        temp1[2][0] = L[2][2];
                        temp1[2][1] = L[1][2];
                        temp1[2][2] = L[0][2];
                        for (int i = 0; i < 3; i++) {
                            L[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[2][0];
                        char b = U[1][0];
                        char c = U[0][0];
                        U[2][0] = B[0][0];
                        U[1][0] = B[1][0];
                        U[0][0] = B[2][0];
                        B[0][0] = D[0][0];
                        B[1][0] = D[1][0];
                        B[2][0] = D[2][0];
                        D[0][0] = F[2][0];
                        D[1][0] = F[1][0];
                        D[2][0] = F[0][0];
                        F[2][0] = a;
                        F[1][0] = b;
                        F[0][0] = c;
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = L[0][2];
                        temp1[0][1] = L[1][2];
                        temp1[0][2] = L[2][2];
                        temp1[1][0] = L[0][1];
                        temp1[1][1] = L[1][1];
                        temp1[1][2] = L[2][1];
                        temp1[2][0] = L[0][0];
                        temp1[2][1] = L[1][0];
                        temp1[2][2] = L[2][0];
                        for (int i = 0; i < 3; i++) {
                            L[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[0][0];
                        char b = U[1][0];
                        char c = U[2][0];
                        U[0][0] = F[0][0];
                        U[1][0] = F[1][0];
                        U[2][0] = F[2][0];
                        F[0][0] = D[2][0];
                        F[1][0] = D[1][0];
                        F[2][0] = D[0][0];
                        D[2][0] = B[2][0];
                        D[1][0] = B[1][0];
                        D[0][0] = B[0][0];
                        B[2][0] = a;
                        B[1][0] = b;
                        B[0][0] = c;
                    }
                    break;
 
                case 'R':
                    if (turn == '+') {
                        // 윗면 위치 변경
                        temp1[0][0] = R[2][0];
                        temp1[0][1] = R[1][0];
                        temp1[0][2] = R[0][0];
                        temp1[1][0] = R[2][1];
                        temp1[1][1] = R[1][1];
                        temp1[1][2] = R[0][1];
                        temp1[2][0] = R[2][2];
                        temp1[2][1] = R[1][2];
                        temp1[2][2] = R[0][2];
                        for (int i = 0; i < 3; i++) {
                            R[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[0][2];
                        char b = U[1][2];
                        char c = U[2][2];
                        U[0][2] = F[0][2];
                        U[1][2] = F[1][2];
                        U[2][2] = F[2][2];
                        F[0][2] = D[2][2];
                        F[1][2] = D[1][2];
                        F[2][2] = D[0][2];
                        D[2][2] = B[2][2];
                        D[1][2] = B[1][2];
                        D[0][2] = B[0][2];
                        B[2][2] = a;
                        B[1][2] = b;
                        B[0][2] = c;
                    } else {
                        // 윗면 위치 변경
                        temp1[0][0] = R[0][2];
                        temp1[0][1] = R[1][2];
                        temp1[0][2] = R[2][2];
                        temp1[1][0] = R[0][1];
                        temp1[1][1] = R[1][1];
                        temp1[1][2] = R[2][1];
                        temp1[2][0] = R[0][0];
                        temp1[2][1] = R[1][0];
                        temp1[2][2] = R[2][0];
                        
                        
                        for (int i = 0; i < 3; i++) {
                            R[i] = Arrays.copyOf(temp1[i], 3);
                        }
 
                        // 측면 위치 변경
                        char a = U[2][2];
                        char b = U[1][2];
                        char c = U[0][2];
                        U[2][2] = B[0][2];
                        U[1][2] = B[1][2];
                        U[0][2] = B[2][2];
                        B[0][2] = D[0][2];
                        B[1][2] = D[1][2];
                        B[2][2] = D[2][2];
                        D[0][2] = F[2][2];
                        D[1][2] = F[1][2];
                        D[2][2] = F[0][2];
                        F[2][2] = a;
                        F[1][2] = b;
                        F[0][2] = c;
                    }
                    break;
                }
            }
            for (int i = 0; i < 3; i++) {
                System.out.println(U[i][0] + "" + U[i][1] + "" + U[i][2]);
            }
 
        }
    }
}
