package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/**
 * A와 B 2 / 골드 5 / 63분
 * @author 민정인
 * https://www.acmicpc.net/problem/12919
 */
public class BOJ_12919 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		String t = br.readLine();
		if(changeString(s, t)) {
			System.out.println(1);
		} else {
			System.out.println(0);
		}
	}
	static boolean changeString(String s, String t) {	// 만들어진 문자열에 대한 비교
		Queue<String> q = new LinkedList<>();			// q 생성
		q.add(s);										// 초기값 s
		while(!q.isEmpty()) {
			String tmp = q.poll();						// tmp에 q의 값 뽑아내기
			if(tmp.length() < t.length()) {				// 길이가 더 짧다면
				if(t.indexOf(tmp) == -1 && t.indexOf(reverse(tmp)) == -1) { // t에 tmp의 값이 기존 순서와 역순 모두 부분문자열로 들어가지 못하는 경우
					continue;	// q에 넣지 않음
				}
				String a = new String(tmp + "A");		// 문자열 뒤에 A 추가 후 q에 넣기
				String b = new String(reverse(tmp + "B"));	// 문자열 뒤에 B 추가 및 역순으로 정리 후 q에 넣기
				q.add(a);
				q.add(b);
			} else if(tmp.toString().equals(t)) {		// t와 동일한 문자열이 있다면 true 반환
				return true;
			}
		}
		return false;
	}
	static String reverse(String s) {					// 역순 함수
		StringBuilder sb = new StringBuilder(s);
		return sb.reverse().toString();
	}
}
