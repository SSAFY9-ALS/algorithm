package algorithm;

import java.util.Scanner;

/**
 * 괄호 추가하기 / 골드3 / 1시간 생각 + 구글링
 * https://www.acmicpc.net/problem/16637
 */

public class BOJ_16637 {
	static int n, result;
	static int[] nums;
	static char[] op;
	
	// 산술 계산을 수행하는 메서드
	static int calculate(char operator, int num1, int num2) {
		if(operator == '+')
			return num1 + num2;
		else if(operator == '-')
			return num1 - num2;
		else
			return num1 * num2;
	}
	
	static void dfs(int calc, int idx) {
		if(idx == n / 2) { // 연산자를 모두 탐색했을 때
			result = Math.max(calc, result); // 현재까지의 최댓값과 구한 값을 비교해 값 갱신
			return; // 종료
		}
		// 인덱스 기준: 인덱스와 다음 숫자 사이에 괄호가 없을 경우 -> 현재까지의 계산값과 현재 인덱스, 인덱스 다음 숫자로 연산하면 됨
		dfs(calculate(op[idx], calc, nums[idx+1]), idx+1);
		// 인덱스 기준: 인덱스와 다음 숫자 사이에 괄호가 있는 경우 -> 현재까지의 계산값과 현재 인덱스, (인덱스 다음 숫자, 인덱스 다음 인덱스, 인덱스 다다음 숫자)로 연산하면 됨
		if(idx + 1 < n / 2) // 조건: 다음 인덱스가 존재해야 함
			dfs(calculate(op[idx], calc, calculate(op[idx+1], nums[idx+1], nums[idx+2])), idx+2);
		
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt(); // n 입력
		String sen = sc.next();
		// 연산자는 모두 이항 연산자 -> 수식의 길이는 홀수여야 하고, 숫자의 개수가 연산자 개수보다 하나 많아야 함
		op = new char[n/2]; // 연산자를 담을 배열
		nums = new int[n/2+1]; // 숫자를 담을 배열
		result = Integer.MIN_VALUE;
		for(int i = 0, nIdx = 0, oIdx = 0; i < sen.length(); i++) {
			if(i % 2 == 0) // 숫자, 연산자, 숫자, 연산자... 의 순서 -> 인덱스로 숫자인지 연산자인지 알 수 있음
				nums[nIdx++] = Character.getNumericValue(sen.charAt(i));
			else
				op[oIdx++] = sen.charAt(i);
		}
		
		dfs(nums[0], 0); // 함수 호출
		System.out.println(result); // 결과 출력
	}
}
