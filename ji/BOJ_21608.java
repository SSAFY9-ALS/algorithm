package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 상어 초등학교 / 골드 5 / 37분
 * @author 민정인
 * https://www.acmicpc.net/problem/21608
 */

public class BOJ_21608 {
	static int n;
	static int[][] map;
	static Map<Integer, ArrayList<Integer>> m = new HashMap<>();
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		ArrayList<Integer> list = new ArrayList<>();
		for(int i = 0; i < n * n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			ArrayList<Integer> tmp = new ArrayList<>();
			for(int j = 0; j < 4; j++) {
				tmp.add(Integer.parseInt(st.nextToken()));
			}
			m.put(num, tmp);	// 상어의 번호와 해당 상어가 좋아하는 상어 정보 저장
			list.add(num);		// 번호 순으로 저장
		}
		for(int i = 0; i < list.size(); i++) {	// 번호 순으로 배치
			pushVal(list.get(i));
		}
		int result = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				int v = map[i][j];
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					if(i + dx[k] >= 0 && i + dx[k] < n && j + dy[k] >= 0 && j + dy[k] < n) {
						if(m.get(v).indexOf(map[i+dx[k]][j+dy[k]]) != -1) {
							cnt++;
						}
					}
				}
				if(cnt == 0) {			// 좋아하는 상어가 없는 경우 더하지 않음
					continue;
				}
				result += Math.pow(10, cnt - 1);	// 좋아하는 상어와 인접한 경우의 값 저장
			}
		}
		System.out.println(result);		// 결과 출력
	}
	static void pushVal(int v) {		// 상어 배치
		int maxCnt = Integer.MIN_VALUE;
		int maxBnk = Integer.MIN_VALUE;
		int posX = -1;
		int posY = -1;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				if(map[i][j] == 0) {
					int cnt = 0;
					int blank = 0;
					for(int k = 0; k < 4; k++) {
						if(i + dx[k] >= 0 && i + dx[k] < n && j + dy[k] >= 0 && j + dy[k] < n) {
							if(m.get(v).indexOf(map[i + dx[k]][j + dy[k]]) != -1) {
								cnt++;
							} else if(map[i + dx[k]][j + dy[k]] == 0) {
								blank++;
							}
						}
					}
					if(maxCnt < cnt) {	// 최대값보다 큰 값이 있는 경우 최대값 및 빈칸의 값, 좌표 갱신
						maxCnt = cnt;
						maxBnk = blank;
						posX = i;
						posY = j;
					} else if(maxCnt == cnt && maxBnk < blank) {	// 같은 값인 경우 빈칸이 많은 것으로 갱신
						maxBnk = blank;
						posX = i;
						posY = j;
					}
				}
			}
		}
		map[posX][posY] = v;	// 행이 작은 순부터, 열이 작은 순부터 탐색하였으므로 탐색에 따른 좌표에 값 배정
	}
}
