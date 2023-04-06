package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 경사로 / 골드 3 / 39분
 * @author 민정인
 * https://www.acmicpc.net/problem/14890
 */

public class BOJ_14890 {
		public class Main {
			static int n, l;
			static int[][]map;
			public static void main(String[] args) throws IOException {
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				StringTokenizer st = new StringTokenizer(br.readLine());
				n = Integer.parseInt(st.nextToken());
				l = Integer.parseInt(st.nextToken());
				map = new int[n][n];
				for(int i = 0; i < n; i++) {
					st = new StringTokenizer(br.readLine());
					for(int j = 0; j < n; j++) {
						map[i][j] = Integer.parseInt(st.nextToken());
					}
				}
				findRWay();
				findCWay();
				System.out.println(result);
			}
			static int result;
			static void findRWay() {
				int idx;		// 경사로 놓는 위치(열)
				int cur;		// 현재 높이
				boolean isPossible;	// 길이 가능한지
				for(int i = 0; i < n; i++) {	// 행 반복
					idx = 0;	// 위치 0
					cur = 0;	// 높이 0
					isPossible = true;
					for(int j = 0; j < n; j++) {
						if(j == 0) {	// 열이 0이면 현재 높이는 해당 위치의 높이로
							cur = map[i][j];
							continue;	// 이후는 생략
						}
						if(Math.abs(map[i][j] - cur) > 1) {	// 높이 차이가 2 이상이면 실패
							isPossible = false;
							break;
						} else if(Math.abs(map[i][j] - cur) == 1) {	// 높이가 1이면
							if(map[i][j] > cur) {			// 어느쪽이 높이가 높은지(뒤가 높다면)
								if(j - idx >= l) {			// 경사로 놓는 위치 + 길이가 경사로 길이보다 길다면
									idx = j;				// 놓을 수 있음 -> 이후 경사로 놓을 수 있는 위치는 j부터 가능
									cur = map[i][j];		// 높이 갱신
									continue;				// 다음 좌표로
								} else {					// 더 짧으면 불가능
									isPossible = false;
									break;
								}
							} else {						// 뒤가 낮다면(내리막): 낮은곳 위치가 j
								idx = j;					// 경사로 가능위치 : 현위치 바로 뒤(낮은곳)
								if(idx + l > n) {			// 이게 지도 밖으로 나가면 불가능
									isPossible = false;
									break;
								} else {					// 지도 안에 가능하면
									for(int k = idx; k < idx + l; k++) {	// 반복(높이 변화 없도록)
										if(map[i][k] != map[i][j]) {// 현재위치랑 비교해서 다르면 불가능
											isPossible = false;
											break;
										}
									}
									if(!isPossible) {
										break;
									}
								}
								idx += l;
								cur = map[i][j];
							}
						}
					}
					if(isPossible) {
						result++;
					}
				}
			}
			static void findCWay() {
				int idx;
				int cur;
				boolean isPossible;
				for(int i = 0; i < n; i++) {	// 열
					idx = 0;
					cur = 0;
					isPossible = true;
					for(int j = 0; j < n; j++) {	// 행
						if(j == 0) {
							cur = map[j][i];
							continue;
						}
						if(Math.abs(map[j][i] - cur) > 1) {
							isPossible = false;
							break;
						} else if(Math.abs(map[j][i] - cur) == 1) {
							if(map[j][i] > cur) {
								if(j - idx >= l) {
									idx = j;
									cur = map[j][i];
									continue;
								} else {
									isPossible = false;
									break;
								}
							} else {
								idx = j;
								if(idx + l > n) {
									isPossible = false;
									break;
								} else {
									for(int k = idx; k < idx + l; k++) {
										if(map[k][i] != map[j][i]) {
											isPossible = false;
											break;
										}
									}
									if(!isPossible) {
										break;
									}
								}
								idx += l;
								cur = map[j][i];
							}
						}
					}
					if(isPossible) {
						result++;
					}
				}
			}
		}
}
