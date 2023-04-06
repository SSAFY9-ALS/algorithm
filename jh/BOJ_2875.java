package algorithm;

import java.util.Scanner;

/**
 * 대회 or 인턴 / 브론즈3 / 5분
 * https://www.acmicpc.net/problem/2875
 */

public class BOJ_2875 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // n 입력
		int m = sc.nextInt(); // m 입력
		int k = sc.nextInt(); // k 입력
		for(int i = 0; i < k; i++) { // k번만큼 반복
			// 여학생 2명, 남학생 1명이 짝 -> (여학생 > 2 * 남학생)일 때 여학생 수가 많은 것
			if(n > 2 * m) // 여학생 수가 남학생 수의 2배 이상이면
				n--; // 여학생에서 한 명 감소
			else // 아니라면
				m--; // 남학생에서 한 명 감소
		}
		System.out.println(Math.min(n / 2, m)); // 여학생이 더 적을 때와 남학생이 더 적을 때로 나누어 만들 수 있는 팀의 개수 출력
	}
}
