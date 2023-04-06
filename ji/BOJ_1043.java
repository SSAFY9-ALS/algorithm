package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 거짓말 / 골드 4 / 51분
 * @author 민정인
 * https://www.acmicpc.net/problem/1043
 */

public class BOJ_1043 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		boolean[] people = new boolean[n+1];
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());
		if(t > 0) {			// 현재 진실을 알고 있는 사람들의 번호에 true 입력
			for(int i = 0; i < t; i++) {
				int idx = Integer.parseInt(st.nextToken());
				people[idx] = true;
			}
		}
		boolean[] chkP = new boolean[n+1];	// 반복문 탈출조건을 위한 배열
		ArrayList<Integer>[] arr = new ArrayList[m];
		for(int i = 0; i < m; i++) {		// 각 파티에 참여한 사람들의 정보 추가
			st = new StringTokenizer(br.readLine());
			arr[i] = new ArrayList<>();
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0; j < num; j++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		while(!Arrays.equals(people, chkP)) {	// 모든 경우를 순회하며 진실을 알고 있는 사람들의 정보를 최종적으로 갱신
			chkP = people.clone();				// 더이상 갱신되지 않을때까지 반복
			for(int i = 0; i < m; i++) {
				for(int j = 0; j < arr[i].size(); j++) {
					if(people[arr[i].get(j)]) {
						for(int k = 0; k < arr[i].size(); k++) {
							people[arr[i].get(k)] = true;
						}
						break;
					}
				}
			}
		}
		boolean chk;
		int result = 0;
		for(int i = 0; i < m; i++) {			// 모든 사람이 진실을 알지 못하는 경우 결과값 + 1
			chk = false;
			for(int j = 0; j < arr[i].size(); j++) {
				if(people[arr[i].get(j)]) {
					chk = true;
					break;
				}
			}
			if(!chk) {
				result++;
			}
		}
		System.out.println(result);				// 결과 출력
	}
}
