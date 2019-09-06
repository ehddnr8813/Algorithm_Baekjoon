package test44;

/*
5
4
1 2 3 4 
4
4 2 3 1 
6
6 5 4 2 3 1 
8
6 1 4 7 2 5 8 3 
12
2 7 4 1 3 5 8 10 12 9 6 11 
*/

import java.util.Scanner;
import java.util.Stack;

public class joCode {
   
   static int[] deck;
   static int[] copy;
   static int[] selected;
   static int N, X, T, ans;
   
   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      
      T = sc.nextInt();
      
      for(int t = 1 ; t <= T ; ++t){
         N = sc.nextInt();
         
         deck = new int[N + 1];
         selected = new int[5];
         ans = Integer.MAX_VALUE;
         
         for(int i = 1 ; i <= N ; ++i){
            deck[i] = sc.nextInt();
         }
         
         for(int i = 0 ; i <= 5 ; ++i){
            go(i, 0);
         }
         
         if(ans == Integer.MAX_VALUE) ans = -1;
         System.out.println("#" + t + " " + ans);
      }
   }

   private static void go(int limit, int depth) {
      if(depth == limit){
         
         copy = copyDeck();
         
         for(int i = 0 ; i < limit ; ++i){
            shuffle(selected[i]);
         }
         
         if(check()) ans = ans > depth ? depth : ans;
         return;
      }
      
      for(int i = 0 ; i < N ; ++i){
         selected[depth] = i;
         go(limit, depth + 1);
      }
   }

   private static void shuffle(int x) {
      int[] result = new int[N + 1];
      Stack<Integer> left = new Stack<>();
      Stack<Integer> right = new Stack<>();
      
      for(int i = 1 ; i <= N ; ++i){
         if(i <= N / 2) left.add(copy[i]);
         else right.add(copy[i]);
      }
      
      boolean isRight = false;
      int gear = right.size() - x;
      for(int i = 1 ; i <= N ; ++i){
         if(gear > 0){
            result[i] = right.pop();
            gear--;
            isRight = false;
            continue;
         } else if (gear < 0 ){
            result[i] = left.pop();
            gear++;
            isRight = true;
            continue;
         }
         
         if(isRight) {
            if(right.isEmpty()) result[i] = left.pop();
            else {
               result[i] = right.pop();
               isRight = false;
            }
         }
         else {
            if(left.isEmpty())result[i] = right.pop();
            else {
               result[i] = left.pop();
               isRight = true;
            }
         }
      }
      copy = result;
   }

   private static boolean check() {
      for(int i = 2 ; i <= N ; ++i){
         if(Math.abs(copy[i] - copy[i - 1]) != 1) {
            return false;
         }
      }
      return true;
   }

   private static int[] copyDeck() {
      int[] result = new int[N + 1];
      for(int i = 1 ; i <= N ; ++i){
         result[i] = deck[i];
      }
      return result;
   }
}