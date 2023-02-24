package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 퇴사 / 실버 3 / 40분
 * @author 민정인
 * https://www.acmicpc.net/problem/14501
 */

public class BOJ_14501 {
	static int n;
	static Pair[] pair;		// 클래스의 배열
	static int[] value;		// 해당 일자부터 시작할 때 받을 수 있는 최대 금액
	static class Pair{		// 소요시간과 금액을 저장하는 클래스
		int day;
		int val;
		public Pair(int day, int val) {
			this.day = day;
			this.val = val;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		pair = new Pair[n];
		value = new int[n + 1];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int day = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			pair[i] = new Pair(day, val);
		}
		int maxVal = Integer.MIN_VALUE;
		for(int i = n - 1; i >= 0; i--) {		// 뒤에서부터 값을 저장하고 해당 값을 참고해옴
			getVal(i);
			maxVal = Math.max(maxVal, value[i]);	// 최대값 비교
		}
		System.out.println(maxVal);
	}
	static void getVal(int d) {
		if(d + pair[d].day > n) {				// 해당 일자에 소요시간이 퇴사날 뒤일 경우 해당 위치는 0으로 저장하고 리턴
			value[d] = 0;
			return;
		}
		for(int i = 0; i <= n - pair[d].day - d; i++) {	// 현재 일정의 소요일 이후 일정 선택시 최대 금액이 되는 값 찾기
			value[d] = Math.max(value[d],pair[d].val + value[pair[d].day + d + i]);
		}
	}
}
