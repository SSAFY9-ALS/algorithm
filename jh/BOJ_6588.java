package algorithm;

import java.util.Scanner;

/*
 * 처음에는 n / 2 까지의 소수를 전부 구한 다음, (n - 대상 숫자)가 소수인지 판단하게 작성 => 시간 초과 발생
 * 따라서 가장 작은 값부터 1씩 증가하며 현재 숫자가 소수일 때, (n - 현재 숫자)가 소수인지 바로 판단하도록 작성
 */

public class BOJ_6588 {
	static boolean searchPrime(int n) {
		for(int i = 3; i < (int)Math.sqrt(n) + 1; i += 2) { // 시간 복잡도를 줄이기 위해 Math.sqrt()를 최대로 지정
			if(n % i == 0)
				return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n, sub;
		String result;
		while((n = sc.nextInt()) != 0) { // 0을 입력 받을 때까지 반복
			result = "Goldbach's conjecture is wrong.";
			for(int i = 3; i <= n / 2; i += 2) { // 소수인지 판단할 값 반복
				if(searchPrime(i)) { // 소수면
					sub = n - i; // 뺄셈하고
					if(searchPrime(sub)) { // 다시 소수인지 판단
						result = String.format("%d = %d + %d", n, i, sub); // 가장 작은 값부터 반복문을 수행했기 때문에 처음 조건에 맞는 경우가 답이 됨
						break;
					}
				}
			}
			System.out.println(result);
		}
	}
}
