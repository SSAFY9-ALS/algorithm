package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 프로세서 연결하기 / SW test 샘플문제 / 42분
 * @author 민정인
 * SWEA 1767번
 */

public class SW_1767 {
	static int n;					
	static ArrayList<Point> list;	// 코어의 리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= test; t++) {
			n = Integer.parseInt(br.readLine());
			int[][] map = new int[n][n];
			list = new ArrayList<>();
			result = Integer.MAX_VALUE;
			for(int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if(map[i][j] == 1) {
						list.add(new Point(i, j));
					}
				}
			}
			for(int i = list.size() - 1; i >= 0; i--) {	// 전체 코어 중 사방에 코어가 있는 경우 제외
				Point p = list.get(i);
				int cnt = 0;
				for(int j = 1; j <= p.x; j++) {
					if(map[p.x - j][p.y] == 1) {
						cnt++;
						break;
					}
				}
				for(int j = 1; j < n - p.x; j++) {
					if(map[p.x + j][p.y] == 1) {
						cnt++;
						break;
					}
				}
				for(int j = 1; j <= p.y; j++) {
					if(map[p.x][p.y - j] == 1) {
						cnt++;
						break;
					}
				}
				for(int j = 1; j < n - p.y; j++) {
					if(map[p.x][p.y + j] == 1) {
						cnt++;
						break;
					}
				}
				if(cnt == 4) {
					list.remove(i);
				}
			}
			fillCable(0, 0, map);
			System.out.println("#" + t + " " + result);
		}
	}
	static int result;
	static void fillCable(int cnt, int len, int[][] map) {	// 각 코어에 케이블 연결
		if(cnt == list.size()) {							// 모든 코어에 케이블 연결시 케이블 길이 최소값 갱신
			result = Math.min(result, len);
			return;
		}
		Point p = list.get(cnt);
		if(p.x == 0 || p.x == n - 1 || p.y == 0 || p.y == n - 1) {	// 벽과 붙어있는 경우 길이 0
			fillCable(cnt + 1, len, map);
			return;
		}
		int[][] tMap = new int[n][n];
		for(int i = 0; i < n; i++) {		// 현 시점의 cell 데이터 저장
			tMap[i] = map[i].clone();
		}
		boolean chk = false;
		for(int j = 1; j <= p.x; j++) {		// 가려는 방향에 코어나 케이블이 있을 경우 재귀하지 않음
			if(map[p.x - j][p.y] != 0) {
				chk = true;
				break;
			}
			tMap[p.x - j][p.y] = 2; 		// 케이블 데이터 추가
		}
		if(!chk) {							// 코어와 직선으로 케이블 연결 성공시
			fillCable(cnt + 1, len + p.x, tMap);	// 다음 코어 진행
		}
		chk = false;						// 다음 방향의 경우를 위해 케이블 탐색을 위한 변수 및 cell 초기화
		for(int i = 0; i < n; i++) {
			tMap[i] = map[i].clone();
		}
		for(int j = 1; j < n - p.x; j++) {
			if(map[p.x + j][p.y] != 0) {
				chk = true;
				break;
			}
			tMap[p.x + j ][p.y] = 2;
		}
		if(!chk) {
			fillCable(cnt + 1, len + (n - p.x - 1), tMap);
		}
		chk = false;
		for(int i = 0; i < n; i++) {
			tMap[i] = map[i].clone();
		}
		for(int j = 1; j <= p.y; j++) {
			if(map[p.x][p.y - j] != 0) {
				chk = true;
				break;
			}
			tMap[p.x][p.y - j] = 2;
		}
		if(!chk) {
			fillCable(cnt + 1, len + p.y, tMap);
		}
		chk = false;
		for(int i = 0; i < n; i++) {
			tMap[i] = map[i].clone();
		}
		for(int j = 1; j < n - p.y; j++) {
			if(map[p.x][p.y + j] != 0) {
				chk = true;
				break;
			}
			tMap[p.x][p.y + j] = 2;
		}
		if(!chk) {
			fillCable(cnt + 1, len + (n - p.y - 1), tMap);
		}
	}
}
