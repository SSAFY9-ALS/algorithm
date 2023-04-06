package algorithm;

import java.util.Scanner;
import java.util.Stack;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		Stack<String> stack = new Stack<>();
		if (s.length() == 0) {
			return;
		}

		int num = 0;
		int answer = 0;
		for (int i = 0; i < s.length(); i++) {
			String temp = Character.toString(s.charAt(i));
			if (temp.equals("(")) { // 여는 괄호면 스택에 저장
				stack.push(temp);
			} else if (temp.equals(")")) { // 닫힌 괄호일 때 실행
				stack.pop();
				if (s.charAt(i - 1) == '(') {
					// 레이저면 실행
					answer += stack.size();
				} else if (s.charAt(i - 1) == ')') {
					// 쇠막대기의 끝점이면 실행
					answer++;
				}
			}

		}

		System.out.println(answer);

	}

}
