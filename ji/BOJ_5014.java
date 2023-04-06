package net.acmicpc;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 스타트링크 / 실버 1 / 45분
 * @author 민정인
 * https://www.acmicpc.net/problem/5014
 * 
 */
public class BOJ_5014 {
	static int f;		// 건물 층수
	static int u;		// 올라가는 층수
	static int d;		// 내려가는 층수
	static int s;		// 현재 층수
	static int g;		// 스타트링크의 층수
	static boolean[] chk;	// 방문여부 확인
	static int cnt = 0;		// 이동 횟수
	static int elevator() {
		if(s == g) {		// 스타트링크 도착시 이동 횟수 출력
			return cnt;
		}
		if(chk[s] == true) {	// 이미 도달한 장소에 다시 도착하면 -1 출력(해당 위치에서 다른 장소로 이동을 이미 진행했다는 의미이기 때문)
			return -1;
		}
		chk[s] = true;			// 방문여부 true로 설정
		if(s > g) {				// 스타트링크가 현재 위치보다 아래에 있을 때
			if(s - d > 0) {		// 1층에 도달하기 전까지 계속 내려감
				s -= d;
				cnt++;
				return elevator();
			} else {			// 계속 내려가서 1층 아래로 내려가도 도착하지 못할 경우: 올라감(단, 최고층을 넘지 않도록)
				if(s + u <= f) {
					s += u;
					cnt++;					
				}
				return elevator();
			}
		} else {				// 스타트링크가 현재 위치보다 위에 있을 때
			if(s + u <= f) {	// 최고층에 도달하기 전까지 계속 올라감
				s += u;
				cnt++;
				return elevator();				
			} else {			// 올라가는 중 최고층을 넘어서 가게되도 도착하지 못할 경우: 내려감(단 1층 아래로 내려가지 않도록)
				if(s - d > 0) {					
					s -= d;
					cnt++;
				}
				return elevator();
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		f = sc.nextInt();
		s = sc.nextInt();
		g = sc.nextInt();
		u = sc.nextInt();
		d = sc.nextInt();
		chk = new boolean[f+1];
		Arrays.fill(chk, false);
		if(elevator() == -1) {	// -1인 경우: 이미 도착했던 층에 도착하여 움직이더라도 해당 위치에 도달하지 못하는 경우
			System.out.println("use the stairs");	// 메시지 출력
		} else {
			System.out.println(cnt);	// 도달한 경우: 움직인 횟수 출력
		}
	}
}
