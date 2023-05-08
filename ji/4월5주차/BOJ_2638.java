package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

public class BOJ_2638 {
	static int n, m;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		getVal();
	}
	static void getVal() {
		int result = 0;
		map[0][0] = 2;
		while(!chk()) {	// 빈칸이 없을 때까지 반복
			for(int i = 0; i < n; i++) {	// 남은 빈칸에 대한 확인(치즈가 녹으면서 바깥 공간과 연결되는 경우)
				for(int j = 0; j < m; j++) {
					if(map[i][j] == 0) {
						bfs(i, j);
					}
				}
			}
			meltCheese();	// 가장 가장자리 치즈를 녹이기
			result++;	// 횟수 증가
		}
		System.out.println(result);	// 결과 출력
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static void fill() {	// 초기 바깥 공간 채우기
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(0, 0));
		boolean[][] chk = new boolean[n][m];
		map[0][0] = 2;
		chk[0][0] = true; 
		while(!q.isEmpty()) {	// 가장 바깥쪽 테두리는 모두 0이므로 이에 연결된 0들을 모두 2로 바꾸어줌
			Point t = q.poll();
			for(int i = 0; i < 4; i++) {
				Point next = new Point(t.x + dx[i], t.y + dy[i]);
				if(next.x >= 0 && next.x < n && next.y >= 0 && next.y < m
						&& map[next.x][next.y] == 0 && !chk[next.x][next.y]) {
					map[next.x][next.y] = 2;
					q.add(next);
				}
			}
		}
	}
	static void bfs(int r, int c) {	// 치즈가 녹은 뒤 바깥 공간과 연결된 부분을 2로 바꾸기
		boolean isRed = false;
		for(int i = 0; i < 4; i++) {	// 해당 칸에서 4방위를 탐색하여 바깥 공간과 연결된 부분이 있는지 확인
			int nr = r + dx[i];
			int nc = c + dy[i];
			if(nr >= 0 && nr < n && nc >= 0 && nc < m && map[nr][nc] == 2) {
				isRed = true;
				break;
			}
		}
		if(!isRed) {	// 연결된 부분이 없는 경우 : 바깥 공간과 격리된 빈 공간일 경우 돌아감
			return;
		}
		boolean[][] chk = new boolean[n][m];
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		chk[r][c] = true;
		map[r][c] = 2;		// 외부와 연결되었으므로 2로 설정
		while(!q.isEmpty()) {	// bfs를 통해 연결된 빈칸들을 전부 2로 채워줌
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				Point next = new Point(p.x + dx[i], p.y + dy[i]);
				if(next.x >= 0 && next.x < n && next.y >= 0 && next.y < m
						&& map[next.x][next.y] == 0 && !chk[next.x][next.y]) {
					map[next.x][next.y] = 2;
					q.add(next);
				}
			}
		}
	}
	static void meltCheese() {	// 치즈 녹이기
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					int cnt = 0;
					for(int k = 0; k < 4; k++) {
						int nx = i + dx[k];
						int ny = j + dy[k];
						if(nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] == 2) {
							cnt++;
						}
					}
					if(cnt >= 2) {
						list.add(new Point(i, j));
					}
				}
			}
		}
		for(int i = 0; i < list.size(); i++) {	// 모인 치즈의 가장자리 집합들에 대해 2로 변경
			Point p = list.get(i);
			map[p.x][p.y] = 2;
		}
	}
	static boolean chk() {	// 모두 녹았는지 확인 및 마지막으로 녹기 직전 치즈조각이 남아있는 개수 확인
		int cnt = 0;		// 남은 치즈조각 개수 저장
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(map[i][j] == 1) {
					cnt++;
				}
			}
		}
		if(cnt == 0) {		// 남은 치즈조각이 없는 경우 true 반환
			return true;
		}
		return false;
	}
}
