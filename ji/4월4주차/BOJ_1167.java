package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/** 트리의 지름 / 골드 2 / 70분 
 * @author 민정인
 * https://www.acmicpc.net/problem/1167
 */

public class BOJ_1167 {
	static int v;
	static ArrayList<Point>[] list;
	static boolean[] chk;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		v = Integer.parseInt(br.readLine());
		chk = new boolean[v];
		list = new ArrayList[v];
		for(int i = 0; i < v; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < v; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = -1;
			while(to != -1) {
				val = Integer.parseInt(st.nextToken());
				list[from-1].add(new Point(to-1, val));
				list[to-1].add(new Point(from-1, val));
				to = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);	// 0번에서 가장 먼 곳까지의 거리와 해당 정점 구하기
		chk = new boolean[v];
		dfs(next, 0);	// 가장 먼 곳이었던 위치에서 가장 먼 곳까지의 거리와 해당 정점 구하기
		System.out.println(result);	// 최대값 출력
	}
	static int result = Integer.MIN_VALUE;
	static int next = -1;
	static void dfs(int pos, int val) {
		if(val > result) {
			result = val;
			next = pos;
		}
		chk[pos] = true;
		for(int i = 0; i < list[pos].size(); i++) {
			if(!chk[list[pos].get(i).x]) {
				dfs(list[pos].get(i).x, val + list[pos].get(i).y);
				chk[list[pos].get(i).x] = true;
			}
		}
	}
}
