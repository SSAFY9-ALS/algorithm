package algorithm;

import java.util.Scanner;

public class BOJ_4948 {
	static boolean countResult(int num) {
		if(num % 2 == 0 && num != 2) // 짝수일 때 바로 반환
			return false;
		for(int i = 3; i < (int)Math.sqrt(num) + 1; i+=2) { // 시간 복잡도를 낮추기 위해 sprt(num)까지 반복
			if(num % i == 0) // 소수가 아니면 반환
				return false;
		}
		return true; // 여기까지 오면 소수인 것 -> true 반환
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, count;
		while((n = sc.nextInt()) != 0) { // 입력값 0이 아닐 때까지 반복
			count = 0;
			for(int i = n+1; i <= 2 * n; i++) { // n < num <= 2*n까지 찾아야 함
				if(countResult(i)) // 소수 판별 함수 실행 -> 소수가 맞으면
					count++; // 1 증가
			}
			System.out.println(count);
		}
	}
}

