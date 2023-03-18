package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 이차원 배열과 연산 / 골드 4 / 68분
 * @author 민정인
 * https://www.acmicpc.net/problem/17140
 */

public class BOJ_17140 {
	static int r, c, k;
	static int row = 3;
	static int col = 3;
	static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
	static int[][] arr = new int[3][3];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		for(int i = 0; i < 3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int result = 0;
		while(!chk() && result <= 100) {		// 계산 시간이 100초 이하거나 값이 구해지지 않은 경우
			if(row >= col) {					// 행 길이와 열 길이 비교를 통한 정렬 진행
				execR();
			} else {
				execC();
			}
			result++;
		}
		System.out.println(result > 100 ? -1 : result);
	}
	static boolean chk() {
		if(row < r || col < c) {	// 구하고자 하는 좌표보다 작은 경우 계산전에 false
			return false;
		}
		if(arr[r - 1][c - 1] == k) {	// 해당 좌표의 값이 k인 경우 true
			return true;
		}
		return false;
	}
	static void execR() {	// 행
		Map<Integer, Integer> map = new HashMap<>();	// 들어온 값의 횟수 저장
		ArrayList<Integer>[] pushR = new ArrayList[row];
		int maxCol = Integer.MIN_VALUE;					// 최대 열 길이
		for(int i = 0; i < row; i++) {
			pushR[i] = new ArrayList<>();				// 배열리스트 초기화
			for(int j = 0; j < col; j++) {
				if(arr[i][j] == 0) {					// 0은 들어가지 않는 수이므로 세지 않음
					continue;
				}
				int val = arr[i][j];
				if(map.get(val) == null) {				// 맵에 정보 없으면 1로 초기화
					map.put(val, 1);
				} else {
					map.put(val, map.get(val) + 1);		// 있다면 횟수 증가
				}
			}
			ArrayList<Integer> tmp = new ArrayList<>(map.keySet());
			tmp.sort(new Comparator<Integer>() {		// 횟수가 적은 순. 같다면 숫자가 작은 순 정렬
				@Override
				public int compare(Integer o1, Integer o2) {
					if(map.get(o1) == map.get(o2)) {
						return o1 - o2;
					}
					return map.get(o1) - map.get(o2);
				}
			});
			for(int j = 0; j < tmp.size(); j++) {
				pushR[i].add(tmp.get(j));
				pushR[i].add(map.get(tmp.get(j)));
			}
			maxCol = Math.max(maxCol, pushR[i].size());
			map.clear();
		}
		col = maxCol;
		arr = new int[row][col];					// 최대 열 길이로 배열 초기화
		for(int i = 0; i < row; i++) {				// 각 행에 추가된 리스트의 크기만큼 값 입력(나머지는 0으로 초기화됨)
			for(int j = 0; j < pushR[i].size(); j++) {
				arr[i][j] = pushR[i].get(j);
			}
		}
	}
	static void execC() {	// 열(행의 경우에서 행 리스트 대신 열 리스트 사용)
		Map<Integer, Integer> map = new HashMap<>();
		ArrayList<Integer>[] pushC = new ArrayList[col];
		int maxRow = Integer.MIN_VALUE;
		for(int i = 0; i < col; i++) {
			pushC[i] = new ArrayList<>();
			for(int j = 0; j < row; j++) {
				if(arr[j][i] == 0) {
					continue;
				}
				int val = arr[j][i];
				if(map.get(val) == null) {
					map.put(val, 1);
				} else {
					map.put(val, map.get(val) + 1);
				}
			}
			ArrayList<Integer> tmp = new ArrayList<>(map.keySet());
			tmp.sort(new Comparator<Integer>() {
				@Override
				public int compare(Integer o1, Integer o2) {
					if(map.get(o1) == map.get(o2)) {
						return o1 - o2;
					}
					return map.get(o1) - map.get(o2);
				}
			});
			for(int j = 0; j < tmp.size(); j++) {
				pushC[i].add(tmp.get(j));
				pushC[i].add(map.get(tmp.get(j)));
			}
			maxRow = Math.max(maxRow, pushC[i].size());
			map.clear();
		}
		row = maxRow;
		arr = new int[row][col];
		for(int i = 0; i < col; i++) {
			for(int j = 0; j < pushC[i].size(); j++) {
				arr[j][i] = pushC[i].get(j);
			}
		}
	}
}
