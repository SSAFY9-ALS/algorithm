package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;
/** 10:50 시작 11:08 끝*/
public class BOJ_10886 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		Deque<Integer> dq = new LinkedList<>();
		int n = Integer.parseInt(br.readLine());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch(cmd) {
			case "push_front":
				int fVal = Integer.parseInt(st.nextToken());
				dq.addFirst(fVal);
				break;
			case "push_back":
				int bVal = Integer.parseInt(st.nextToken());
				dq.addLast(bVal);
				break;
			case "pop_front":
				if(dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.pollFirst());
				}
				break;
			case "pop_back":
				if(dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.pollLast());
				}
				break;
			case "size":
				System.out.println(dq.size());
				break;
			case "empty":
				if(dq.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "front":
				if(dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.getFirst());
				}
				break;
			case "back":
				if(dq.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(dq.getLast());
				}
				break;
			}
		}
	}
}

// https://www.acmicpc.net/problem/10866
