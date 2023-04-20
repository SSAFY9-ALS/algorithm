package april;

import java.util.Scanner;

/**
 * 광고 / 플래티넘4 / 1시간 30분+
 * https://www.acmicpc.net/problem/1305
 */

public class BOJ_1305 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int l = sc.nextInt(); // l 입력
		String sen = sc.next(); // 광고 문자열 입력
		
		int length = sen.length(); // 문자열의 총 길이
		int j = 0;
		int[] pi = new int[length]; // pi[i] -> i까지 문자열 중에 prefix == suffix가 되는 문자열의 최대 길이
		
		// kmp 알고리즘 응용
		for(int i = 1; i < length; i++) {
			while(j > 0 && sen.charAt(j) != sen.charAt(i))
				j = pi[j - 1];
			
			if(sen.charAt(j) == sen.charAt(i))
				pi[i] = ++j;
		}
		
		// 정답은 i가 문자열 길이일 때, 전체 길이에서 만들 수 있는 부분 문자열의 최대값을 뺀 것
		System.out.println(length - pi[length - 1]);
	}
}
