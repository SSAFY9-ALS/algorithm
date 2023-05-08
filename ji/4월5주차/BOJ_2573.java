package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 빙산 / 골드 4 / 32
 * @author 민정인
 * https://www.acmicpc.net/problem/2573
 */

public class BOJ_2573 {
	static int n, m;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<>();
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
				if(map[i][j] > 0) {
					list.add(new Point(i, j));
				}
			}
		}
		int result = 0;
		while(isOne()) {
			if(list.isEmpty()) {
				result = 0;
				break;
			}
			melt();
			result++;
		}
		System.out.println(result);
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static boolean isOne() {
		if(list.isEmpty()) {
			return true;
		}
		Queue<Point> q = new ArrayDeque<>();
		q.add(list.get(0));
		boolean[][] chk = new boolean[n][m];
		chk[list.get(0).x][list.get(0).y] = true;
		int cnt = 1;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				Point next = new Point(p.x + dx[i], p.y + dy[i]);
				if(map[next.x][next.y] > 0 && !chk[next.x][next.y]) {
					chk[next.x][next.y] = true;
					q.add(next);
					cnt++;
				}
			}
		}
		if(cnt != list.size()) {
			return false;
		}
		return true;
	}
	static void melt() {
		ArrayList<Integer> count = new ArrayList<>();
		for(int i = 0; i < list.size(); i++) {
			int cnt = 0;
			for(int j = 0; j < 4; j++) {
				if(map[list.get(i).x + dx[j]][list.get(i).y + dy[j]] == 0) {
					cnt++;
				}
			}
			count.add(cnt);
		}
		for(int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			if(map[p.x][p.y] <= count.get(i)) {
				map[p.x][p.y] = 0;
				list.remove(i);
				count.remove(i);
				i--;
			} else {
				map[p.x][p.y] -= count.get(i); 
			}
		}
	}
}
