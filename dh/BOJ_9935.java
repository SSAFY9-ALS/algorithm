package algorithm;
/* 
 * 문자열 폭발 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/9935
 */
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		char[] bomb = sc.nextLine().toCharArray();
		// charAt, index탐색 속도 차이?
		Stack<Character> stack = new Stack<>();
		int s_size = s.length();
		int bomb_size = bomb.length;
		int check = 0;

		for (int i = 0; i < s_size; i++) {
			stack.add(s.charAt(i));
			int st_size = stack.size();
			if (stack.peek() == bomb[bomb_size - 1] && st_size >= bomb_size) {
				for (int j = 1; j <= bomb_size; j++) {
					if (stack.get(st_size - j) != bomb[bomb_size - j]) {
						check = 1;
						break;
					}
				}
				// 한문자씩 스택에 넣은 후 폭탄 문자열의 마지막 문자와 비슷하면 폭탄인지 탐색
				// 폭탄 문자열이면 폭탄 문자열의 길이만큼 pop() 실행
				if (check == 0) {
					for (char c : bomb) {
						stack.pop();
					}
				}
			}
			check = 0;
		}
		if (stack.isEmpty()) {
			System.out.println("FRULA");
		} else {
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < stack.size(); i++) {
				sb.append(stack.get(i));
			}
			System.out.println(sb.toString());
		}
	}
}

// 정답비율 높은 걸 풀자...
