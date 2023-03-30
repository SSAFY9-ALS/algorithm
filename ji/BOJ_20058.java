package net.acmipc;

import java.awt.Point;
import java.io.*;
import java.util.*;

/**
 * 마법사 상어와 파이어스톰 / 골드 3 / 154분
 * @author 민정인
 * https://www.acmicpc.net/problem/20058
 */

public class BOJ_20058 {
	static int size, q;
	static int[][] map;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		size = (int) Math.pow(2, n);
		map = new int[size][size];
		for(int i = 0; i < size; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < size; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < q; i++) {
			tmp = new int[size][size];
			int l = Integer.parseInt(st.nextToken());
			if(l > 0) {				
				int limit = (int) Math.pow(2, l);
				for(int si = 0; si < size; si += limit) {
					for(int sj = 0; sj < size; sj += limit) {
						rolling(si, sj, limit);
					}
				}
				map = tmp;
			}
			melt();
		}
		System.out.println(getVal());
		System.out.println(getCount() == Integer.MIN_VALUE ? 0 : getCount());
	}
	static int[][] tmp;
	static void rolling(int r, int c, int l) {
		ArrayList<Integer>[] arr = new ArrayList[l];
		for(int i = 0; i < l; i++) {
			arr[i] = new ArrayList<>();
		}
		for(int i = r; i < r + l; i++) {
			for(int j = c; j < c + l; j++) {
				arr[i-r].add(map[i][j]);
			}
		}
		int idxr = l;
		for(int i = c; i < c + l; i++) {
			idxr--;
			int idx = 0;
			for(int j = r; j < r + l; j++) {
				tmp[j][i] = arr[idxr].get(idx++);
			}
		}
	}
	static Deque<Integer> findDq(int r, int c, int limit){
		Deque<Integer> dq = new ArrayDeque<>();
		for(int i = r; i < r + limit; i++) {
			for(int j = c; j < c + limit; j++) {
				dq.add(map[i][j]);
			}
		}
		return dq;
	}
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	static void melt() {
		ArrayList<Point> list = new ArrayList<>();
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[i][j] > 0) {					
					int cnt = 4;
					for(int k = 0; k < 4; k++) {
						if(i + dx[k] < 0 || i + dx[k] >= size || j + dy[k] < 0 || j + dy[k] >= size
								|| map[i + dx[k]][j + dy[k]] == 0) {
							cnt--;
						}
					}
					if(cnt < 3) {
						list.add(new Point(i, j));
					}
				}
			}
		}
		for(int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			map[p.x][p.y]--;
		}
	}
	static int getVal() {
		int sum = 0;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	
	static int getCount() {
		chk = new boolean[size][size];
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(map[i][j] > 0 && !chk[i][j]) {
					chk[i][j] = true;
					maxVal = Math.max(maxVal, bfs(i, j));
				}
			}
		}
		return maxVal;
	}
	static boolean[][] chk;
	static int bfs(int r, int c) {
		int cnt = 0;
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c));
		while(!q.isEmpty()) {
			Point p = q.poll();
			cnt++;
			for(int i = 0; i < 4; i++) {
				if(p.x + dx[i] >= 0 && p.x + dx[i] < size && p.y + dy[i] >= 0 & p.y + dy[i] < size
						&& map[p.x + dx[i]][p.y + dy[i]] > 0 && !chk[p.x + dx[i]][p.y + dy[i]]) {
					Point next = new Point(p.x + dx[i], p.y + dy[i]);
					chk[next.x][next.y]= true;
					q.add(next);
				}
			}
		}
		return cnt;
	}
}
