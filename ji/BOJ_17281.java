package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 야구 / 골드 4 / 40분
 * @author 민정인
 * https://www.acmicpc.net/problem/17281
 */

public class BOJ_17281 {
	static int n;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		arr = new int[9];
		int[][] inning = new int[n][9];
		for(int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 9; j++) {
				inning[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		arr[0] = 4;
		for(int i = 1; i < 9; i++) {
			if(i > 3) {
				arr[i] = i + 1;
				continue;
			}
			arr[i] = i;
		}
		int result = Integer.MIN_VALUE;
		do {
			Map<Integer, Integer> map = new HashMap<>();
			for(int i = 0; i < 9; i++) {	// 선수들의 타석 정보 map에 저장
				map.put(arr[i], i);
			}
			int num = 1;
			int score = 0;
			for(int i = 0; i < n; i++) {
				int cnt = 0;
				boolean[] base = new boolean[3];	// 베이스의 주자 여부
				while(cnt < 3) {			// 아웃카운트가 3이 되기 전까지 진행
					int hitter = num++;
					if(hitter > 9) {		// 9번 타자 이후 1번타자로 이동(num은 이미 증가했으므로 2로 초기화)
						hitter = 1;
						num = 2;
					}
					switch(inning[i][map.get(hitter)]) {
					case 0:	// 아웃시 아웃카운트 + 1
						cnt++;
						break;
					case 1:	// 안타시 3루 위치 false 및 점수 1점 추가, 다른 베이스들은 1루씩 진루
						if(base[2]) {
							base[2] = false;
							score++;
						}
						if(base[1]) {
							base[2] = true;
							base[1] = false;
						}
						if(base[0]) {
							base[1] = true;
							base[0] = false;
						}
						base[0] = true;
						break;
					case 2:	// 2루타시 2루, 3루 위치 점수 추가, 1루는 3루로 이동
						for(int j = 2; j >= 1; j--) {
							if(base[j]) {
								base[j] = false;
								score++;
							}
						}
						if(base[0]) {
							base[2] = true;
							base[0] = false;
						}
						base[1] = true;
						break;
					case 3:	// 3루타시 모든 베이스 주자 점수 추가, 3루에만 주자 존재
						for(int j = 2; j >= 0; j--) {
							if(base[j]) {
								base[j] = false;
								score++;
							}
						}
						base[2] = true;
						break;
					case 4:	// 홈런시 모든 주자 점수 추가, 베이스에는 아무도 없음
						for(int j = 0; j < 3; j++) {
							if(base[j]) {
								base[j] = false;
								score++;
							}
						}
						score++;
						break;
					}
				}
			}
			result = Math.max(result, score);	// 최대값 갱신
		} while(np());
		System.out.println(result);
	}
	
	static boolean np() {	// np 알고리즘. 1번 선수를 4번 타자로 고정하므로 0번을 제외한 장소들만 순서 바꿈
		int i = 8;
		while(i > 1 && arr[i - 1] >= arr[i]) --i;
		if(i == 1) return false;
		int j = 8;
		while(arr[i - 1] >= arr[j]) --j;
		swap(i - 1, j);
		int k = 8;
		while(i < k) swap(i++, k--);
		return true;
	}
	static void swap(int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
