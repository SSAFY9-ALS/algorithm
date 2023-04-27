package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 문자열 제거 / 골드 4 / 60분
 * @author 민정인
 * https://www.acmicpc.net/problem/21941
 */

public class BOJ_21941 {
	static String s;
	static ArrayList<String> list = new ArrayList<>();
	static ArrayList<Integer> score = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		s = br.readLine();
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int val = Integer.parseInt(st.nextToken());
			if(val / a.length() < 1) {
				continue;
			}
			list.add(a);
			score.add(val);
		}
		int[] dp = new int[s.length() + 1];	// idx : s의 idx번째 문자로
		for(int i = 1; i <= s.length(); i++) {
			dp[i] = i;
		}
		for(int i = 1; i <= s.length(); i++) {	// 문자열 순회
			for(int j = 0; j < list.size(); j++) {	// 부분 문자열 체크
				if(i + list.get(j).length() - 1 > s.length()) {	// 문자열의 길이보다 현재 위치 + 부분 문자열 길이가 더 크면 생략
					continue;
				}
				if(s.indexOf(list.get(j), i - 1) == i - 1) {	// 현재 위치에 해당 문자열 존재하면
					dp[i + list.get(j).length() - 1] = Math.max(dp[i + list.get(j).length() - 1], dp[i-1] + score.get(j));	// 현재 위치 + 부분문자열 길이 위치의 점수는 기존값 + 점수와 해당 위치의 값 중 최대값
				} else {	// 일치하지 않는다면 현재위치는 기존값 + 1
					dp[i + list.get(j).length() - 1] = Math.max(dp[i + list.get(j).length() - 1], dp[i + list.get(j).length() - 2] + 1);
				}
			}
		}
		System.out.println(dp[s.length()]);
	}
}
