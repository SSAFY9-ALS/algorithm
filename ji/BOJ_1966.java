package net.acmicpc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/** 11:38 시작 */
public class BOJ_1966 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for(int i = 0; i < n; i++) {
			Queue<Integer> q = new LinkedList<>();
			int m = sc.nextInt();
			int pos = sc.nextInt();
			int cnt = 1;
			for(int j = 0; j < m; j++) {
				q.add(sc.nextInt());
			}
			while(!q.isEmpty()) {
				int max = Collections.max(q);
				int front = q.poll();
				if(front != max) {
					q.add(front);
				} else {
					if(pos == 0) {
						System.out.println(cnt);
						break;
					}
					cnt++;
				}
				pos--;
				if(pos < 0) {
					pos = q.size()-1;
				}
			}
		}
	}
}

// https://www.acmicpc.net/problem/1966