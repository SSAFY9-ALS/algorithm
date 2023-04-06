package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 연구소 3 / 골드 3 / 81분
 * @author 민정인
 * https://www.acmicpc.net/problem/17142
 */

public class BOJ_17142 {
	static int n, m;
	static int[][] tMap;	// 데이터 확인을 위한 배열
	static ArrayList<Point> list = new ArrayList<>();	// 바이러스 위치 리스트
	static int[] idx;		// 조합을 위한 인덱스 배열
	static class Virus{		// 바이러스 전염 데이터 저장 클래스
		Point p;
		int cnt;
		public Virus (Point p, int cnt) {
			this.p = p;
			this.cnt = cnt;
		}
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int[][] map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {
					list.add(new Point(i, j));
				}
			}
		}
		idx = new int[list.size()];
		Arrays.fill(idx, list.size() - m, list.size(), 1);
		int minVal = Integer.MAX_VALUE;
		do {	// np를 통한 조합
			tMap = new int[n][n];
			Queue<Virus> q = new LinkedList<>();
			for(int i = 0; i < n; i++) {	// 맵 정보 복사
				tMap[i] = map[i].clone();
			}
			for(int i = 0; i < idx.length; i++) {
				if(idx[i] == 1) {
					q.add(new Virus(list.get(i), 0));
				}
			}
			int result = 0;
			if(fill()) {	// 이미 빈칸이 없는 경우 0으로 초기화하고 반복문 탈출
				minVal = 0;
				break;
			}
			boolean[][] chk = new boolean[n][n];	// 방문여부 배열
			while(!q.isEmpty()) {					// bfs를 통한 바이러스 복제
				Virus tmp = q.poll();
				result = Math.max(result, tmp.cnt);
				Point p = tmp.p;
				for(int j = 0; j < 4; j++) {
					if(p.x + dx[j] < 0 || p.x + dx[j] >= n || p.y + dy[j] < 0 || p.y + dy[j] >= n
							|| tMap[p.x + dx[j]][p.y + dy[j]] == 1 || chk[p.x + dx[j]][p.y + dy[j]]) {
						continue;
					}
					Point next = new Point(p.x + dx[j], p.y + dy[j]);
					if(tMap[next.x][next.y] == 2) {	// 다음 칸에 바이러스가 있는 경우
						if(fill()) {				// 이미 전부 차있는 경우 bfs 탈출
							break;
						}
						if(chk[next.x][next.y]) {	// 이미 방문했던 바이러스의 경우 생략
							continue;
						}
					}
					chk[next.x][next.y] = true;		// 방문여부 갱신 및 바이러스 복제
					tMap[next.x][next.y] = 2; 
					q.add(new Virus(next, tmp.cnt + 1));	// 큐에 추가
				}
			}
			if(fill()) {
				minVal = Math.min(minVal, result);
			} else {
				continue;
			}
		} while(np());
		System.out.println(minVal == Integer.MAX_VALUE ? -1 : minVal);
	}
	static boolean fill() {
		for(int j = 0; j < n; j++) {
			for(int k = 0; k < n; k++) {
				if(tMap[j][k] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	static boolean np() {
		int i = idx.length - 1;
		while(i > 0 && idx[i - 1] >= idx[i]) --i;
		if(i == 0) return false;
		int j = idx.length - 1;
		while(idx[i - 1] >= idx[j]) --j;
		swap(i - 1, j);
		int k = idx.length - 1;
		while(i < k) swap(i++, k--);
		return true;
	}
	static void swap(int i, int j) {
		int tmp = idx[i];
		idx[i] = idx[j];
		idx[j] = tmp;
	}
}

