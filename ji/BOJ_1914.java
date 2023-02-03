package net.acmicpc;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** 11:13 11:56 + 17분 */
public class BOJ_1914 {
	public static void move(int st, int ar) {
		System.out.println(st + " " + ar);
	}
	public static void hanoi(int n, int st, int to, int ar) {
		if(n < 1) {
			return;
		}
		hanoi(n - 1, st, ar, to);
		move(st, ar);
		hanoi(n - 1, to, st, ar);
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		BigInteger val = new BigInteger("2");
		BigInteger val2 = val.pow(n);
		System.out.println(val2.subtract(BigInteger.valueOf(1)));
		if(n <= 20) {
			hanoi(n, 1, 2, 3);			
		}
	}
}

// https://www.acmicpc.net/problem/1914