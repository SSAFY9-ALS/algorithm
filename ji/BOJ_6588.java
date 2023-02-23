package net.acmicpc;

import java.util.Scanner;
/** 소요시간: 25 */

public class BOJ_6588 {
	public static boolean checkNum(int n) {
		double mid = Math.sqrt(n);
		for(int i = 2; i <= mid; i++) {
			if(n % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	public static int prove(int n) {
		for(int val = 3; val <= n / 2; val += 2) {
			if(checkNum(val) && checkNum(n - val)) {
				return val;
			}
		}
		return 0;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n;
		while((n = sc.nextInt()) != 0) {
			if(prove(n) != 0) {
				System.out.println(n + " = " + prove(n) + " + " + (n - prove(n)));
			} else {
				System.out.println("Goldbach's conjecture is worng.");
			}
		}
	}
}
