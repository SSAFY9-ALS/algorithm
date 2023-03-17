package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 마법사 상어와 토네이도 / 골드 3 / 70분
 * @author 민정인
 * https://www.acmicpc.net/problem/20057
 */

public class BOJ_20057 {
	static int n;
	static int[][] map;
	static Point start;
	// 좌 하 우 상 순서
	static int[][] dx = {	// 5, 10, 10, 7, 7, 1, 1, 2, 2, 나머지
		{0, 1, -1, 1, -1, 1, -1, 2, -2, 0},
		{2, 1, 1, 0, 0, -1, -1, 0, 0, 1},
		{0, 1, -1, 1, -1, 1, -1, 2, -2, 0},
		{-2, -1, -1, 0, 0, 1, 1, 0, 0, -1}
	};
	static int[][] dy = {
		{-2, -1, -1, 0, 0, 1, 1, 0, 0, -1},
		{0, 1, -1, 1, -1, 1, -1, 2, -2, 0},
		{2, 1, 1, 0, 0, -1, -1, 0, 0, 1},
		{0, 1, -1, 1, -1, 1, -1, 2, -2, 0}
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		StringTokenizer st;
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int d = 0;
		int cnt = 1;
		int s = 1;
		start = new Point(n / 2, n / 2);
		while(start.x != 0 || start.y != 0) {	// 0, 0에 도달할때까지 반복
			moveTornado(d, s);
			if(++cnt > 2) {						// 두번 꺾을 때마다 이동 거리 늘어남
				cnt = 1;
				s++;
			}
			d = (d + 1) % 4;					// 매번 방향 전환
		}
		System.out.println(result);
	}
	static void moveTornado(int d, int s) {		// 한칸씩 이동하면서 모래 옮기기
		for(int i = 0; i < s; i++) {
			if(start.x == 0 && start.y == 0) {
				return;
			}
			start.setLocation(start.x + dx[d][9], start.y + dy[d][9]);
			int val = map[start.x][start.y];
			tmp = 0;
			map[start.x][start.y]= 0; 
			if(val == 0) {
				continue;
			}
			for(int j = 0; j < 10; j++) {
				chkRange(d, j, val);
			}
		}
	}
	static int tmp;
	static int result;
	static void chkRange(int d, int r, int val) {	// 각 케이스에 맞게 진행
		switch(r) {
		case 0:
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val * 0.05;
				tmp += val * 0.05;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val * 0.05;
				tmp += val * 0.05;
			}
			break;
		case 1:
		case 2:
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val * 0.1;
				tmp += val * 0.1;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val * 0.1;
				tmp += val * 0.1;
			}
			break;
		case 3:
		case 4:
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val * 0.07;
				tmp += val * 0.07;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val * 0.07;
				tmp += val * 0.07;
			}
			break;
		case 5:
		case 6:
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val * 0.01;
				tmp += val * 0.01;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val * 0.01;
				tmp += val * 0.01;
			}
			break;
		case 7:
		case 8:
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val * 0.02;
				tmp += val * 0.02;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val * 0.02;
				tmp += val * 0.02;
			}
			break;
		case 9:		// 모든 이동이 끝난 후 마지막으로 남은 양(전체 값 - 배치된 값들)
			if(start.x + dx[d][r] < 0 || start.x + dx[d][r] >= n
			|| start.y + dy[d][r] < 0 || start.y + dy[d][r] >= n) {
				result += val - tmp;
			} else {
				map[start.x + dx[d][r]][start.y + dy[d][r]] += val - tmp;
			}
			break;
		}
	}
}
