package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 미세먼지 안녕! / 골드 4 / 40분
 * @author 민정인
 * https://www.acmicpc.net/problem/17144
 */

public class BOJ_17144 {
	static int r, c, t;
	static int[][]map;
	static ArrayList<Point> list = new ArrayList<>();	// 현재 미세먼지 정보 리스트
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[] machine = new int[2];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(st.nextToken());
		map = new int[r][c];
		int idx = 0;
		for(int i = 0; i < r; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < c; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] > 0) {
					list.add(new Point(i, j));
				} else if(map[i][j] == -1) {
					machine[idx++] = i;
				}
			}
		}
		for(int i = 0; i < t; i++) {
			spread();
			airCleaner();
			for(int j = 0; j < r; j++) {	// 1초 후 미세먼지 위치정보 갱신
				for(int k = 0; k < c; k++) {
					if(map[j][k] > 0) {
						list.add(new Point(j, k));
					}
				}
			}
		}
		int result = 0;
		for(int i = 0; i < r; i++) {
			for(int j = 0; j < c; j++) {
				if(map[i][j] > 0) {
					result += map[i][j];
				}
			}
		}
		System.out.println(result);
	}
	static void spread() {
		int[][] result = new int[r][c];	// 새롭게 데이터를 덮어씌울 맵
		result[machine[0]][0] = -1;		// 공기청정기 위치 미리 선언
		result[machine[1]][0] = -1;
		for(int i = 0; i < list.size(); i++) {	// 미세먼지 리스트 순회
			Point p = list.get(i);
			int cnt = 0;
			for(int j = 0; j < 4; j++) {
				if(p.x + dx[j] < 0 || p.x + dx[j] >= r || p.y + dy[j] < 0 || p.y + dy[j] >= c
						|| result[p.x + dx[j]][p.y + dy[j]] == -1) {
					continue;	// 범위 밖이거나 공기청정기 위치면 생략
				}
				result[p.x + dx[j]][p.y + dy[j]] += map[p.x][p.y] / 5;
				cnt++;
			}
			result[p.x][p.y] += map[p.x][p.y] - (map[p.x][p.y] / 5 * cnt);
		}
		list.clear();
		map = result;	// 맵에 덮어씌우기
	}
	
	static void airCleaner() {	// 각 방향에 따른 deque 사용으로 미세먼지 이동
		int p1 = machine[0];
		Deque<Integer> dq1 = new ArrayDeque<>();
		for(int i = 1; i < c; i++) {
			dq1.add(map[p1][i]);
		}
		for(int i = p1 - 1; i >= 0; i--) {
			dq1.add(map[i][c - 1]);
		}
		for(int i = c - 2; i >= 0; i--) {
			dq1.add(map[0][i]);
		}
		for(int i = 1; i < p1; i++) {
			dq1.add(map[i][0]);
		}
		dq1.addFirst(0);
		dq1.pollLast();
		for(int i = 1; i < c; i++) {
			map[p1][i] = dq1.pollFirst();
		}
		for(int i = p1 - 1; i >= 0; i--) {
			map[i][c - 1] = dq1.pollFirst();
		}
		for(int i = c - 2; i >= 0; i--) {
			map[0][i] = dq1.pollFirst();
		}
		for(int i = 1; i < p1; i++) {
			map[i][0] = dq1.pollFirst();
		}
		
		int p2 = machine[1];
		Deque<Integer> dq2 = new ArrayDeque<>();
		for(int i = 1; i < c; i++) {
			dq2.add(map[p2][i]);
		}
		for(int i = p2 + 1; i < r; i++) {
			dq2.add(map[i][c - 1]);
		}
		for(int i = c - 2; i >= 0; i--) {
			dq2.add(map[r - 1][i]);
		}
		for(int i = r - 2; i > p2; i--) {
			dq2.add(map[i][0]);
		}
		dq2.addFirst(0);
		dq2.pollLast();
		for(int i = 1; i < c; i++) {
			map[p2][i] = dq2.pollFirst();
		}
		for(int i = p2 + 1; i < r; i++) {
			map[i][c - 1]= dq2.pollFirst();
		}
		for(int i = c - 2; i >= 0; i--) {
			map[r - 1][i] = dq2.pollFirst();
		}
		for(int i = r - 2; i > p2; i--) {
			map[i][0] = dq2.pollFirst();
		}
	}
}
