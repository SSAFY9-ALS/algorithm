package net.acmicpc;

import java.util.Scanner;
/** 소요시간: 10분 */
public class BOJ_4948 {
	public static boolean checkNum(int n) {
		double mid = Math.sqrt(n);
		for(int i = 2; i <= mid; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while((n = sc.nextInt()) != 0) {
			int cnt = 0;
			for(int i = n + 1; i <= 2 * n; i++) {
				if(checkNum(i) == true) {
					cnt++;
				}
			}
			System.out.println(cnt);
		}
	}
}
