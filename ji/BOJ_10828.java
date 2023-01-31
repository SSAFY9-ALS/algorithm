package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/** 10:05 시작 11:08 끝
 * 늦어진 이유: 40분가량 입력값에 대한 문제 해
*/
public class BOJ_10828 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer str;
		int n = Integer.parseInt(br.readLine());
		Stack<Integer> st = new Stack<>();
		for(int i = 0; i < n; i++) {
			str = new StringTokenizer(br.readLine());
			String cmd = str.nextToken();
			switch(cmd) {
			case "push":
				st.add(Integer.parseInt(str.nextToken()));
				break;
			case "pop":
				if(st.isEmpty()) {
					System.out.println(-1);
				} else {
					System.out.println(st.lastElement());
					st.pop();
				}
				break;
			case "size":
				System.out.println(st.size());
				break;
			case "empty":
				if(st.isEmpty()) {
					System.out.println(1);
				} else {
					System.out.println(0);
				}
				break;
			case "top":
				if(st.isEmpty()) {
					System.out.println(-1);
				} else {					
					System.out.println(st.lastElement());
				}
				break;
			}
		}
	}
}
