package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 달리기 / 플레 3 / 105분(실패)
 * @author 민정인
 * https://www.acmicpc.net/problem/16930
 * 
 * 현재 막히는 부분 : 96% 시간초과에 대해 최적화할 방법에 대해 확인이 필
 */

public class BOJ_16930 {
	static int n, m, k;
	static char[][] map;
	static Point p1, p2;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n+2][m+2];
		Arrays.fill(map[0], '#');
		Arrays.fill(map[n+1], '#');
		for(int i = 1; i <= n; i++) {
			map[i][0] = '#';
			map[i][m+1] = '#';
		}
		for(int i = 1; i <= n; i++) {
			String s = br.readLine();
			for(int j = 1; j <= m; j++) {
				map[i][j] = s.charAt(j - 1);
			}
		}
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());
		p1 = new Point(x1, y1);
		p2 = new Point(x2, y2);
//		for(int i = 0; i < n + 2; i++) {
//			for(int j = 0; j < m + 2; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
		System.out.println(bfs());
	}
	static class Pair {
		Point p;
		int cnt;
		public Pair(Point p, int cnt) {
			this.p = p;
			this.cnt = cnt;
		}
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int bfs() {
		Queue<Pair> q = new ArrayDeque<>();
		q.add(new Pair(p1, 0));
		boolean[][] chk = new boolean[n+2][m+2];
		chk[p1.x][p1.y] = true;
		while(!q.isEmpty()) {
			Pair pr = q.poll();
			Point p = pr.p;
			if(p.equals(p2)) {
				return pr.cnt;
			}
			for(int i = 0; i < 4; i++) {
				int nx = p.x;
				int ny = p.y;
				for(int j = 1; j <= k; j++) {
					nx += dx[i];
					ny += dy[i];
					if(map[nx][ny] == '#') {
						break;
					}
					if(chk[nx][ny]) {
						continue;
					}
					chk[nx][ny] = true;
					if(nx == p2.x && ny == p2.y) {
						return pr.cnt + 1;
					}
					int lx = nx + dx[(i + 1) % 4];
					int ly = ny + dy[(i + 1) % 4];
					int rx = nx + dx[(i + 3) % 4];
					int ry = ny + dy[(i + 3) % 4];
					if(map[lx][ly] == '#' && map[rx][ry] == '#') {
//						System.out.println(nx + " " + ny + " " + i);
						if(j < k) {
							continue;							
						}
					}
					q.add(new Pair(new Point(nx, ny), pr.cnt + 1));
				}
			}
		}
		return -1;
	}
}

