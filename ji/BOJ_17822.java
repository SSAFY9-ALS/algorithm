package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 원판 돌리기 / 골드 3 / 68분
 * @author 민정인
 * https://www.acmicpc.net/problem/17822
 */

public class BOJ_17822 {
	static int n, m;
	static ArrayList<Integer>[] list;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		list = new ArrayList[n];
		for(int i = 0; i < n; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				list[i].add(Integer.parseInt(st.nextToken()));
			}
		}
		for(int i = 0; i < t; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			rolling(x, d, k);	// 입력받은 회전 진행
			int cnt = 0;		// 평균값 계산을 미리 준비
			int sum = 0;
			ArrayList<Point> pList = new ArrayList<>();	// 인접한 값과 동일한 위치들 리스트
			for(int j = 0; j < n; j++) {
				for(int l = 0; l < m; l++) {
					int val = list[j].get(l);
					sum += val;
					if(val > 0) {	// val이 0인 경우 지워진 값이므로 확인하지 않음
						cnt++;
						for(int o = 0; o < 4; o++) {
							if(j + dx[o] < 0 || j + dx[o] >= n) {	// 가장 안쪽 원판이나 바깥쪽 원판에서 필터링을 위함
								continue;
							}
							if(list[j + dx[o]].get(l + dy[o] < 0 ? m - 1 :	// 1번 위치나 M번 위치에 대한 인접 위치 확인용
								l + dy[o] >= m ? 0 : l + dy[o]) == val){
								Point p = new Point(j, l);
								if(pList.indexOf(p) == -1) {		// 현재 리스트에 없는 경우 추가
									pList.add(p);
								}
								p = new Point(j + dx[o], l + dy[o] < 0 ? m - 1 : 
									l + dy[o] >= m ? 0 : l + dy[o]);	// 현재 리스트에 없는 경우 추가
								if(pList.indexOf(p) == -1) {
									pList.add(p);
								}
							}
						}
					}
				}
			}
			for(int j = 0; j < pList.size(); j++) {	// 원판별 동일값이 존재하는 위치들에 대해 0으로 변환
				Point p = pList.get(j);
				list[p.x].set(p.y, 0);
			}
			if(pList.isEmpty()) {					// 저장된 위치 정보가 없는 경우 인접한 위치에 동일값이 없다는 의미이므로 평균값으로 더하거나 빼줌
				double avg = (double) sum / cnt;
				for(int j = 0; j < n; j++) {
					for(int l = 0; l < m; l++) {
						if(list[j].get(l) == 0) {
							continue;
						}
						if(list[j].get(l) > avg) {
							list[j].set(l, list[j].get(l) - 1);
						} else if(list[j].get(l) < avg) {
							list[j].set(l, list[j].get(l) + 1);
						}
					}
				}
			}
		}
		int result = 0;
		for(int i = 0; i < n; i++) {	// 연산이 종료된 후 결과값에 남은 값들을 더하고 결과 출력
			for(int j = 0; j < m; j++) {
				result += list[i].get(j);
			}
		}
		System.out.println(result);
	}
	
	static void rolling(int x, int d, int k) {	// 회전 방향에 따라 회전
		for(int i = x - 1; i < n; i += x) {
			for(int j = 0; j < k; j++) {
				if(d == 0) {
					Collections.reverse(list[i]);
					list[i].add(list[i].remove(0));
					Collections.reverse(list[i]);
				} else {
					list[i].add(list[i].remove(0));
				}
			}
		}
	}
}
