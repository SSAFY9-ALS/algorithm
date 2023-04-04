package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 파티 / 골드 3 / 54분
 * @author 민정인
 * https://www.acmicpc.net/problem/1238
 */

public class BOJ_1238 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int[][] map = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			Arrays.fill(map[i], Integer.MAX_VALUE);
		}
		for(int i = 1; i <= n; i++) {
			map[i][i] = 0;
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			map[from][to] = val;
		}
		// 플로이드-워샬 방법
		for(int k = 1; k <= n; k++) {
			for(int i = 1; i <= n; i++) {
				if(i == k || map[i][k] == Integer.MAX_VALUE) {
					continue;
				}
				for(int j = 1; j <= n; j++) {
					if(i == j || k == j || map[k][j] == Integer.MAX_VALUE) {
						continue;
					}
					map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
				}
			}
		}
		int result = Integer.MIN_VALUE;
		for(int i = 1; i <= n; i++) {
			result = Math.max(map[i][x] + map[x][i], result);
		}
		System.out.println(result);
	}
}
