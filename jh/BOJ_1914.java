package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * 하노이 탑 / 실버1 / 25분
 * https://www.acmicpc.net/problem/1914
 */

public class BOJ_1914 {
	static StringBuilder sb = new StringBuilder();
	
	static void findHanoi(int n, char from, char to, char temp) {
		if(n == 1) // n이 1이면
			sb.append(from + " " + to + "\n"); // 경로 출력
		else { // 아닐 경우
			findHanoi(n-1, from, temp, to); // (n-1)까지는 temp로 이동시켜 놓고
			sb.append(from + " " + to + "\n"); // n을 to로 이동한 다음
			findHanoi(n-1, temp, to, from); // 다시 (n-1)을 to로 이동시킴
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(in.readLine()); // n 입력받음
		// 숫자가 너무 커져셔 long으로도 값을 받지 못함 -> BigInteger 사용해서 값을 계산
		sb.append(new BigInteger("2").pow(n).subtract(new BigInteger("1")) + "\n");
		if(n <= 20) // n이 20 이하일 때는 수행할 필요가 없음 -> 조건식 설정
			findHanoi(n, '1', '3', '2');
		System.out.println(sb);
	}
}