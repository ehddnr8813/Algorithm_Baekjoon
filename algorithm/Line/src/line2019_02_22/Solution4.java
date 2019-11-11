package line2019_02_22;
 
import java.util.Scanner;

public class Solution4 {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      int N = sc.nextInt(); // 자리의 개수. 2~20000
      int[] seats = new int[N];
      for(int i=0; i<N; ++i) {
         seats[i] = sc.nextInt();
      }
      
      int maxTerm = 1;
      
      for(int i=0; i<N; ++i) {
         if(seats[i] == 1) continue;
         
         for(int j=1; j<=(i>N-1-i? i:N-1-i); ++j) {
            if(i-j>=0 && seats[i-j]==1) {
               if(j>maxTerm) maxTerm = j;
               break;
            }
            if(i+j<N && seats[i+j]==1) {
               if(j>maxTerm) maxTerm = j;
               break;
            }
         }
      }
      
      System.out.println(maxTerm);
   }
}