package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 수영장 만들기 / 골드 1 / 50분
 * @author 민정인
 * https://www.acmicpc.net/problem/1113
 */

public class BOJ_1113 {
	static int n, m;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			String s = br.readLine();
			for(int j = 0; j < m; j++) {
				map[i][j] = s.charAt(j) - '0';
			}
		}
		for(int k = 1; k < 9; k++) {
			getVal(k);
		}
		System.out.println(result);
	}
	static int result;
	static void getVal(int v) {	// 맵을 순환하면서 방문하지 않았으면서 현재 물을 채우려는 높이인 경우 찾기
		boolean[][] chk = new boolean[n][m];
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(!chk[i][j] && map[i][j] == v) {
					ArrayList<Point> tmp = bfs(i, j, chk);	// 채울 수 있는 좌표에 대한 리스트 받기
					if(tmp != null) {
						list.addAll(tmp);
					}
				}
			}
		}
		for(int i = 0; i < list.size(); i++) {	// 채울 수 있는 좌표에 대해 높이 증가 및 결과값 갱신
			map[list.get(i).x][list.get(i).y]++;
		}
		result += list.size();
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static ArrayList<Point> bfs(int r, int c, boolean[][] chk) {	// bfs 진행
		chk[r][c] = true;
		ArrayList<Point> list = new ArrayList<>();
		list.add(new Point(r, c));
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(r, c));
		boolean fill = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {	// 범위 밖으로 나가거나 자신보다 작은 값이 옆에 있는경우(물을 채울 수 없는 위치이므로 boolean 갱신)
				int nr = p.x + dx[i];
				int nc = p.y + dy[i];
				if(nr < 0 || nr >= n || nc < 0 || nc >= m || map[nr][nc] < map[r][c]) {
					fill = false;
					continue;
				}
				if(!chk[nr][nc] && map[nr][nc] == map[r][c]) {
					chk[nr][nc] = true;
					list.add(new Point(nr, nc));
					q.add(new Point(nr, nc));
				}
			}
		}
		if(fill) {	// 채울 수 있는 경우에는 리스트 리턴
			return list;
		} else {	// 채울 수 없는 경우에는 null 리턴
			return null;
		}
	}
}
