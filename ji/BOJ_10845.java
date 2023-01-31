package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/** 시작 11:15 끝 11:34*/
public class BOJ_10845 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Queue<Integer> q = new LinkedList<>();
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		int val = -1;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			String cmd = st.nextToken();
			switch(cmd) {
			case "push":
				val = Integer.parseInt(st.nextToken());
				q.add(val);
				break;
			case "pop":
				if(q.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(q.poll());
				}
				break;
			case "size":
				System.out.println(q.size());
				break;
			case "empty":
				if(q.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "front":
				if(q.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(q.element());
				}
				break;
			case "back":
				if(q.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(val);
				}
				break;
			}
		}
	}
}
