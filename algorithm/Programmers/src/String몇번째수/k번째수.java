package String몇번째수;

	import java.util.Arrays;

public class k번째수 {

    public int[] solution(int[] array, int[][] commands) {
    	int[] answer = new int[commands.length];
        for(int idx=0; idx<commands.length; idx++) {
        	int i=commands[idx][0];
        	int j=commands[idx][1];
        	int k=commands[idx][2];
        	int [] newArr=Arrays.copyOfRange(array, i-1, j);
        	Arrays.sort(newArr);
        	answer[idx]=newArr[k-1];
        }
        return answer;
    }
	
	public static void main(String[] args) {
		int[] arr=new int[] {1,5,2,6,3,7,4};
		int [][]com= new int[][] {{2,5,3},{4,4,1},{1,7,3}};
		//solution(arr,com);
	}	

}
