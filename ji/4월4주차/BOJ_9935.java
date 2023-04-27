package net.acmicpc;

import java.io.*;
import java.util.*;

/** 문자열 폭발 / 골드 4 / 88분
 * 
 * @author 민정인
 * https://www.acmicpc.net/problem/9935
 */

public class BOJ_9935 {
	static String s;
	static String boom;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		boom = br.readLine();
		stack();
	}
	static void stack() {
		Stack<Character> stk = new Stack<>();	// 스택에 문자열 순회하면서 넣기
		for(int i = 0; i < s.length(); i++) {
			stk.add(s.charAt(i));
			if(stk.size() >= boom.length()) {	// 폭발 문자열 길이보다 긴 경우
				boolean chk = false;
				for(int j = 0; j < boom.length(); j++) {	// 끝에서 폭발 문자열 길이 번째부터 폭발 문자열과 비교
					if(stk.get(stk.size() - boom.length() + j) != boom.charAt(j)) {	// 일치하지 않는 부분이 있으면 패스
						chk = true;
						break;
					}
				}
				if(!chk) {	// 폭발 문자열과 일치하는 부분은 전부 pop하기
					for(int j = 0; j < boom.length(); j++) {
						stk.pop();
					}
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		while(!stk.isEmpty()) {	// 스택의 문자들 문자열에 추가
			sb.append(stk.pop());
		}
		System.out.println(sb.toString().isEmpty() ? "FRULA" : sb.reverse().toString());	// 비어있는지 확인 후 결과 출력
	}
}
