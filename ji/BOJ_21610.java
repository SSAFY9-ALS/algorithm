package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 마법사 상어와 비바라기 / 골드 5 / 19분
 * @author 민정인
 * https://www.acmicpc.net/problem/21610
 */

public class BOJ_21610 {
	static int n, m;
	static int[][] map;
	static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0,  -1};
	static ArrayList<Point> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		list.add(new Point(n - 1, 0));	// 초기 구름의 위치 저장
		list.add(new Point(n - 1, 1));
		list.add(new Point(n - 2, 0));
		list.add(new Point(n - 2, 1));
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			moveCloud(d, s);
		}
		int result = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				result += map[i][j];
			}
		}
		System.out.println(result);
	}
	
	static void moveCloud(int d, int s) {
		ArrayList<Point> tmp = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {	// 구름들을 돌며 비내리기
			Point p = list.get(i);
			p.x += dx[d] * s;
			p.y += dy[d] * s;
			while(p.x < 0) {		// 범위 밖에 좌표가 있을 경우 범위 내로 넣어줌
				p.x += n;
			}
			while(p.y < 0) {
				p.y += n;
			}
			if(p.x >= n) {
				p.x %= n;
			}
			if(p.y >= n) {
				p.y %= n;
			}
			map[p.x][p.y]++;
			if(tmp.indexOf(p) == -1) {	// 같은 장소에 구름이 들어올 경우 물복사 버그가 겹치는 것을 피하기 위한 조건문
				tmp.add(p);
			}
		}
		for(int i = 0; i < tmp.size(); i++) {	// 물복사 버그
			Point p = tmp.get(i);
			int cnt = 0;
			for(int j = 2; j <= 8; j += 2) {	// 대각선 좌표에 물이 있는지 확인
				if(p.x + dx[j] >= 0 && p.x + dx[j] < n && p.y + dy[j] >= 0 && p.y + dy[j] < n) {
					if(map[p.x + dx[j]][p.y + dy[j]] > 0) {
						cnt++;
					}
				}
			}
			map[p.x][p.y] += cnt; 
		}
		tmp.clear();							// 임시 배열 초기화(다음 구름의 좌표 저장을 위함)
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] >= 2) {
					Point t = new Point(i, j);
					if(list.indexOf(t) != -1) {	// 구름의 좌표 리스트에 있는 위치일 경우 생략
						continue;
					}
					map[i][j] -= 2;
					tmp.add(t);
				}
			}
		}
		list = tmp;								// 다음 구름 리스트 저장
	}
}
