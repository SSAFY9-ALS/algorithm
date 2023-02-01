package net.acmicpc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/** 시작 11:35 끝 11:40 */
public class BOJ_2164 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Queue<Integer> q = new LinkedList<>();
		int n = sc.nextInt();
		for(int i = 1; i <= n; i++) {
			q.add(i);
		}
		while(q.size() > 1) {
			q.remove();
			int val = q.poll();
			q.add(val);
		}
		System.out.println(q.poll());
	}
}

// https://www.acmicpc.net/problem/2164