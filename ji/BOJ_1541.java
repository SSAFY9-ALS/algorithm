package net.acmicpc;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 잃어버린 괄호 / 실버 2 / 40분
 * @author 민정인
 * https://www.acmicpc.net/problem/1541
 */

public class BOJ_1541 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		int st = 0;		// 숫자부분의 시작점
		int ed = s.indexOf("+") != -1 & s.indexOf("-") != -1		// 숫자부분의 끝점
				? Math.min(s.indexOf("+"), s.indexOf("-"))
				: Math.max(s.indexOf("+"), s.indexOf("-"));
		int val = 0;	// 최소값
		if(ed == -1) {	// 만약 연산자가 없는 경우라면 이후의 while문에도 들어가지 않으므로 현재 값만 그대로 출력됨
			val = Integer.parseInt(s);
		}
		int chk = 1;
		while(ed != -1) {	// 연산자가 존재하는 경우 -와 - 사이의 모든 +들을 괄호로 묶어 주면 최소값이 나온다(만약 이후에 -가 나오지 않는다면 마지막 + 연산자까지)
			int t = Integer.parseInt(s.substring(st, ed));
			val += t * chk;
			if(ed == s.length()) {
				break;
			}
			if(s.charAt(ed) == '-') {
				if(chk == 1) {					
					chk *= -1;
				}
			}
			st = ed + 1;
			int plus = s.indexOf("+", st) == -1 ? s.length(): s.indexOf("+", st);
			int minus = s.indexOf("-", st) == -1 ? s.length(): s.indexOf("-", st);
			ed = Math.min(plus, minus);
		}
		System.out.println(val);	// 결과 출력
	}
}
