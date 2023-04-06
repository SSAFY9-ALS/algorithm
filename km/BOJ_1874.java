import java.util.Scanner;
import java.util.Stack;

public class BOJ_1874 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		StringBuilder answer = new StringBuilder(); // 정답을 담을 문자열
		
		Stack<Integer> stack = new Stack<>(); // 사용할 stack
		
		int n = input.nextInt();
		int start = 0; // push할 숫자 
		
		while(n!=0) {
			n--;
			int num = input.nextInt();
			
			if(num>start) {
				for(int i= start+1;i<=num;i++) {
					stack.push(i);
					answer.append('+');   // stack에 push한 후 + 저장
				}
				start = num;
			}
			else if(stack.peek()<num) { // 
				System.out.println("NO");
				return;
			}
			stack.pop();
			answer.append('-');
		}
		
		
		for(int i=0;i<answer.length();i++) {
			System.out.println(answer.charAt(i));
		}
	}
}
