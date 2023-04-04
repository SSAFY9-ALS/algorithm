package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 여행 가자 / 골드 4 / 23분
 * @author 민정인
 * https://www.acmicpc.net/problem/1976
 */

public class BOJ_1976 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		StringTokenizer st;
		int[][] map = new int[n][n];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int[] path = new int[m];
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < m; i++) {
			path[i] = Integer.parseInt(st.nextToken());
		}
		for(int k = 0; k < n; k++) {	// 플로이드-워샬 알고리즘 사용(경유지를 통해 이동 가능한 경우 별도 최소값 없이 이동 가능하다는 의미로 1 저장)
			for(int i = 0; i < n; i++) {
				if(i == k || map[i][k] == 0) {
					continue;
				}
				for(int j = 0; j < n; j++) {
					if(i == j || j == k || map[k][j] == 0) {
						continue;
					}
					map[i][j] = 1;
				}
			}
		}
		for(int i = 0; i < n; i++) {	// 자기 자신으로 가는 방법도 존재하므로 map[i][i]에 1 저장
			map[i][i] = 1;
		}
		boolean chk = true;
		for(int i = 0; i < m - 1; i++) {	// 전체 경로를 돌며 연결되지 않은 경우 chk를 false로 저장하고 break
			if(map[path[i]-1][path[i+1]-1] == 0) {
				chk = false;
				break;
			}
		}
		System.out.println(chk ? "YES" : "NO");
	}
}
