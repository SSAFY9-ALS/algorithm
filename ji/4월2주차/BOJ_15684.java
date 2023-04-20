package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

public class BOJ_15684 {
	static int n, m, h;
	static int[][] map;
	static ArrayList<Point> list = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		map = new int[h][n];
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int height = Integer.parseInt(st.nextToken());
			int from = Integer.parseInt(st.nextToken());
			map[height-1][from-1] = from;
			map[height-1][from] = from;
		}
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < n - 1; j++) {
				if(map[i][j] == 0) {
					if(j + 1 < n && map[i][j+1] > 0) {
						continue;
					}
					list.add(new Point(i,j));
				}
			}
		}
//		for(int i = 0; i < h; i++) {
//			for(int j = 0; j < n; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		for(int i = 0; i < list.size(); i++) {
//			System.out.println(list.get(i));
//		}
//		System.out.println();
		powerSet(0, 0);
//		for(int i = 0; i < n; i++) {
//			chkEnd(i);
//		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}
	static int result = Integer.MAX_VALUE;
	static void powerSet(int idx, int cnt) {
		if(cnt > 3) {
			return;
		}
		if(result <= cnt) {
			return;
		}
		if(chkEnd()) {
			result = Math.min(result, cnt);
			return;
		}
		for(int i = idx; i < list.size(); i++) {
			Point p = list.get(i);
			if(map[p.x][p.y] > 0) {
				continue;
			}
			if(p.y + 1 < n && map[p.x][p.y + 1] > 0) {
				continue;
			}
			map[p.x][p.y] = p.y+1;
			map[p.x][p.y+1] = p.y+1;
			powerSet(i+1, cnt+1);
			map[p.x][p.y] = 0;
			map[p.x][p.y+1] = 0;
		}
	}
	static boolean chkEnd() {
//		for(int i = 0; i < h; i++) {
//			for(int j = 0; j < n; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println();
		for(int start = 0; start < n; start++) {			
			int tmp = start;
			for(int i = 0; i < h; i++) {
				if(map[i][start] > 0) {
					if(map[i][start] == start) {
						start--;
					} else {
						start++;
					}
				}
			}
//			System.out.println(tmp + " " + start);
			if(start != tmp) {
//				System.out.println();
				return false;
			}
		}
		return true;
	}
}
