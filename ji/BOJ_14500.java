package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 테트로미노 / 골드 4 / 45분
 * @author 민정인
 * https://www.acmicpc.net/problem/14500
 */

public class BOJ_14500 {
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
		int result = Math.max(Math.max(line(), square()), Math.max(line3(), line2()));
		System.out.println(result);
	}
	
	static int line() {	// 일직선
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j <= m - 4; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i][j+1] + map[i][j+2] + map[i][j+3]);
			}
		}
		for(int i = 0; i <= n - 4; i++) {
			for(int j = 0; j < m; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i+1][j] + map[i+2][j] + map[i+3][j]);
			}
		}
		return maxVal;
	}
	
	static int square() { // 정사각형
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < n - 1; i++) {
			for(int j = 0; j < m - 1; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i][j+1] + map[i+1][j] + map[i+1][j+1]);
			}
		}
		return maxVal;
	}
	
	static int line3() { // 긴 부분이 3인 모양 ( ㄱ, ㄴ, ㅗ 모양)
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i <= n - 3; i++) {
			for(int j = 0; j < m - 1; j++) {
				int val = map[i][j] + map[i+1][j] + map[i+2][j];
				maxVal = Math.max(maxVal, val + Math.max(map[i][j+1], Math.max(map[i+1][j+1], map[i+2][j+1])));
			}
			for(int j = 1; j < m; j++) {
				int val = map[i][j] + map[i+1][j] + map[i+2][j];
				maxVal = Math.max(maxVal, val + Math.max(map[i][j-1], Math.max(map[i+1][j-1], map[i+2][j-1])));
			}
		}
		for(int j = 0; j <= m - 3; j++) {
			for(int i = 0; i < n - 1; i++) {
				int val = map[i][j] + map[i][j+1] + map[i][j+2];
				maxVal = Math.max(maxVal, val + Math.max(map[i+1][j], Math.max(map[i+1][j+1], map[i+1][j+2])));
			}
			for(int i = 1; i < n; i++) {
				int val = map[i][j] + map[i][j+1] + map[i][j+2];
				maxVal = Math.max(maxVal, val + Math.max(map[i-1][j], Math.max(map[i-1][j+1], map[i-1][j+2])));
			}
		}
		return maxVal;
	}
	
	static int line2() { // 지그재그 형태 모양
		int maxVal = Integer.MIN_VALUE;
		for(int i = 0; i < n - 2; i++) {
			for(int j = 0; j < m - 1; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i+1][j] + map[i+1][j+1] + map[i+2][j+1]);
			}
		}
		for(int i = 1; i < n - 1; i++) {
			for(int j = 0; j < m - 1; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i+1][j] + map[i][j+1] + map[i-1][j+1]);
			}
		}
		for(int i = 0 ; i < n - 1; i++) {
			for(int j = 0; j < m - 2; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i][j+1] + map[i+1][j+1] + map[i+1][j+2]);
			}
		}
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < m - 2; j++) {
				maxVal = Math.max(maxVal, map[i][j] + map[i][j+1] + map[i-1][j+1] + map[i-1][j+2]);
			}
		}
		return maxVal;
	}
}
