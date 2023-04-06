package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 인구 이동 / 골드 5 / 65분
 * @author 민정인
 * https://www.acmicpc.net/problem/16234
 */

public class BOJ_16234 {
	static int n, l, r;			// 땅 크기, 차이 범위
	static int[][] map;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		l = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		System.out.println(chkMap());
	}
	static int chkMap() {	// 모든 국가의 인구차이가 범위 내일 경우까지 반복 후 일수 출력
		int result = 0;
		while(!chkSub()) {
			chk = new boolean[n][n];	// 방문 초기화
			changeVal();
			result++;
		}
		return result;
	}
	static boolean chkSub() {		// 각 국가를 기준으로 4방위 탐색하여 차이가 범위 내인지 확인. 범위 내라면 false
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int val = map[i][j];
				for(int k = 0; k < 4; k++) {
					if(i + dx[k] < 0 || i + dx[k] >= n || j + dy[k] < 0 || j + dy[k] >= n) {
						continue;
					}
					int sub = Math.abs(val - map[i + dx[k]][j + dy[k]]);
					if(sub >= l && sub <= r) {
						return false;
					}
				}
			}
		}
		return true;
	}
	static void changeVal() {		// 연결된 칸들 확인
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(!chk[i][j]) {
					findSub(i, j);
				}
			}
		}
	}
	static boolean[][] chk = new boolean[n][n];
	static void findSub(int x, int y) {	// 차이가 범위 내인 국가들 전체 탐색 및 연합에 동일값 선언
		Queue<Point> q = new LinkedList<>();
		ArrayList<Point> list = new ArrayList<>();
		int cnt = 1;
		int result = map[x][y];
		chk[x][y] = true;
		list.add(new Point(x, y));
		q.add(new Point(x, y));
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				if(p.x + dx[i] < 0 || p.x + dx[i] >= n || p.y + dy[i] < 0 || p.y + dy[i] >= n
						|| chk[p.x + dx[i]][p.y + dy[i]]) {
					continue;
				}
				int sub = Math.abs(map[p.x][p.y] - map[p.x + dx[i]][p.y + dy[i]]);
				if(sub >= l && sub <= r) {
					chk[p.x + dx[i]][p.y + dy[i]] = true;
					q.add(new Point(p.x + dx[i], p.y + dy[i]));
					result += map[p.x+dx[i]][p.y+dy[i]];
					list.add(new Point(p.x + dx[i], p.y + dy[i]));
					cnt++;
				}
			}
		}
		for(int i = 0; i < list.size(); i++) {
			Point t = list.get(i);
			map[t.x][t.y] = result / cnt;
		}
	}
}
