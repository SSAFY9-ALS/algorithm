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
			if (temp.equals("(")) {
				stack.push(temp);
			} else if (temp.equals(")")) {
				stack.pop();
				if (s.charAt(i - 1) == '(') {
					answer += stack.size();
				} else if (s.charAt(i - 1) == ')') {
					answer++;
				}
			}

		}
		
		System.out.println(answer);

//		String pre_s = stack.pop();;
//		while (!stack.empty()) {
//			String temp = stack.pop();
//			if (s == "(") {
//				if (pre_s == "(") {
//					num++;
//				} else if (pre_s == ")") {
//					
//				}
//			} else if (s == ")") {
//				if (pre_s == "(") {
//					answer += num;
//				} else if (pre_s == ")") {
//
//				}
//			}
//		}

	}

}
