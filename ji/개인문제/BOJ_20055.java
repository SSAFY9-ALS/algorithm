package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 컨베이어 벨트 위의 로봇 / 골드 5 / 29분
 * @author 민정인
 * https://www.acmicpc.net/problem/20055
 */

public class BOJ_20055 {
	static int[] belt;	// 컨베이어 벨트 정보
	static int n;
	static int k;
	static ArrayList<Integer> robot = new ArrayList<>();	// 로봇 위치 정보
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		belt = new int[n * 2];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < 2 * n; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}
		int result = 0;
		do {
			result++;
			first();
			second();
			third();
		} while(!forth());
		System.out.println(result);
	}
	static void first() {
		// 컨베이어 벨트 이동(deque를 사용하여 한칸씩 밀어줌)
		// deque 사용시 메모리와 시간이 너무 오래 걸림. 1차원 배열인 만큼 별도 deque 없이 진행하는 부분이 훨씬 효율적
//		Deque<Integer> dq = new ArrayDeque<>();
//		for(int i = 0; i < n * 2; i++) {
//			dq.add(belt[i]);
//		}
//		dq.addFirst(dq.pollLast());
//		for(int i = 0; i < n * 2; i++) {
//			belt[i] = dq.pollFirst();
//		}
		// 1차원 배열의 값들에서 현재 위치에 이전 위치의 값을 저장
		int tmp = belt[n * 2 - 1];
		for(int i = n * 2 - 1; i >= 1; i--) {
			belt[i] = belt[i - 1];
		}
		belt[0] = tmp;
		for(int i = robot.size() - 1; i >= 0; i--) {
			if(robot.get(i) + 1 == n - 1) {
				robot.remove(i);
				continue;
			}
			robot.set(i, robot.get(i) + 1);
		}
	}
	static void second() {	// 로봇 이동(각 위치에 대한 정보 조건 이용하여 진행)
		for(int i = 0; i < robot.size(); i++) {
			if(belt[robot.get(i) + 1] > 0 && robot.indexOf(robot.get(i) + 1) == -1) {
				belt[robot.get(i) + 1]--;
				if(robot.get(i) + 1 == n - 1) {
					robot.remove(i);
					i--;
					continue;
				}
				robot.set(i, robot.get(i) + 1);
			}
		}
	}
	static void third() {	// 로봇 올리기
		if(belt[0] > 0) {
			robot.add(0);
			belt[0]--;
		}
	}
	static boolean forth() {	// 조건 확인(벨트의 내구도가 0인 위치의 개수 확인)
		int cnt = 0;
		for(int i = 0; i < n * 2; i++) {
			if(belt[i] == 0) {
				cnt++;
			}
		}
		if(cnt >= k) {
			return true;
		} else {
			return false;
		}
	}
}
