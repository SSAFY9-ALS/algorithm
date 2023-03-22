package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 운동 / 골드 4 / 98분
 * @author 민정인
 * https://www.acmicpc.net/problem/1956
 */

public class BOJ_1956 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int[][] arr = new int[v][v];
		for(int i = 0; i < v; i++) {
			Arrays.fill(arr[i], Integer.MAX_VALUE);
		}
		for(int i = 0; i < e; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken()) - 1;
			int end = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			arr[start][end] = val;
		}
		for(int i = 0; i < v; i++) {	// 플로이드-워샬 기법으로 전체 위치들에 대한 최소거리 진행
			for(int j = 0; j < v; j++) {
				if(i == j || arr[i][j] == Integer.MAX_VALUE) {
					continue;
				}
				for(int k = 0; k < v; k++) {
					if(arr[j][k] != Integer.MAX_VALUE) {
						arr[i][k] = Math.min(arr[i][k], arr[i][j] + arr[j][k]);
					}
				}
			}
		}
		int result = Integer.MAX_VALUE;
		for(int i = 0; i < v; i++) {	// 자기 자신 위치로 오는 값들 중 최소값
			result = Math.min(result, arr[i][i]);
		}
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);	// 경로 유무에 따라 값 출력
	}
}
