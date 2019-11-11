package line2019_02_22;

import java.util.ArrayList;
import java.util.Scanner;

public class Solution2 {
   
   static int[] fac = new int[11];
   
   public static String foo(ArrayList<Integer> arr, int k) {
      int num = arr.size();
      
      if(num == 1)
         return arr.get(0) + "";
      
      int f = fac[num - 1];
      int index = (k-1)/f; // 몇 번째 구간인가? (0 ~ num-1)
      
      int temp = arr.get(index);
      arr.remove(index);
      
      return temp + foo(arr, k-(f*index));
   }

   public static void main(String[] args) {
      fac[0] = 1;
      for(int i=1; i<11; ++i) {
         fac[i] = fac[i-1] * i;
      }
      
      Scanner sc = new Scanner(System.in);
      
      String temp = sc.nextLine();
      String[] temp2 = temp.split(" ");
      ArrayList<Integer> arr = new ArrayList<>();
      for(String t: temp2) {
         arr.add(Integer.parseInt(t));
      }
      arr.sort(null);
      
      int k = Integer.parseInt(sc.nextLine());
      
      String foo = foo(arr, k);
      
      System.out.println(foo);
   }
}