package algorithm;

import java.util.Scanner;
import java.util.Stack;

/**
 * 문자열 폭발 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/9935
 */

public class BOJ_9935 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		String sen = sc.next();
		String check = sc.next();
		Stack<Character> stack = new Stack<>();
		
		int len = check.length();
		boolean flag;
		for(int i = 0; i < sen.length(); i++) { // 앞에서부터 탐색
			stack.push(sen.charAt(i)); // 스택에 넣어줌
			
			if(stack.size() >= len) { // 스택에 있는 값이 폭발 문자열의 길이만큼 되면
				flag = true;
				
				// 폭발 문자열인지 확인
				for(int j = 0; j < len; j++) {
					if(stack.get(stack.size()-len+j) != check.charAt(j)) {
						flag = false;
						break;
					}
				}
				
				if(flag) { // 폭발 문자열이라면
					for(int j = 0; j < len; j++) { // 폭발 문자열 길이만큼
						stack.pop(); // 스택에서 제거
					}
				}
			}
		}
		
		// 폭발이 끝난 문자열 변수로 구함
		StringBuilder result = new StringBuilder();
		for(char c : stack) {
			result.append(c);
		}
		
		if(result.length() == 0) // 남은 문자가 없다면
			System.out.println("FRULA"); // 지정된 값 출력
		else // 아니면
			System.out.println(result); // 결과 출력
	}
}
