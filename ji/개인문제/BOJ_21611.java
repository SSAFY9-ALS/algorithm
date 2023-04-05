package net.acmicpc;

import java.io.*;
import java.util.*;
/**
 * 마법사 상어와 블리자드 / 골드 1 / 170분
 * @author 민정인
 * https://www.acmicpc.net/problem/21611
 */
public class BOJ_21611 {
	static int n, m;
	static int idx;
	static int tIdx;
	static long result;
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {-1, 0, 1, 0};
	static int[][] map;
	static int[][] tMap;
	static ArrayList<Integer> list;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][n];	// 구슬 정보 담을 맵
		tMap = new int[n][n];	// 위치별 번호 담을 맵
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int stX = n / 2;	// 중심좌표
		int stY = n / 2;
		for(int i = 0; i < m; i++) {
			// 방향, 거리 블리자드 실행
			st = new StringTokenizer(br.readLine());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			list = new ArrayList<>();
			idx = 1;
			tIdx = 0;
			fillMap(stX, stY, 1, 0);
			ArrayList<Integer> index = new ArrayList<>();
			switch(d) {	// 명령에 따른 방향
			case 1:
				for(int j = 1; j <= s; j++) {
					index.add(tMap[stX-j][stY]);
				}
				break;
			case 2:
				for(int j = 1; j <= s; j++) {
					index.add(tMap[stX+j][stY]);
				}
				break;
			case 3:
				for(int j = 1; j <= s; j++) {
					index.add(tMap[stX][stY-j]);
				}
				break;
			case 4:
				for(int j = 1; j <= s; j++) {
					index.add(tMap[stX][stY+j]);
				}
				break;
			}
			for(int j = index.size() - 1; j >= 0; j--) {	// 리스트에서 해당 번호의 칸에 있는 구슬들 제거
				list.remove(index.get(j) - 1);
			}
			boom();	// 구슬 폭파
			grouping();	// 그룹으로 구슬 번호 갱신
			filling(stX, stY, 1, 0);	// 갱신된 리스트 맵에 반영
		}
		System.out.println(result);
	}
	static void grouping() {	// 그룹화
		if(!list.isEmpty()) {	// 리스트를 돌며 (연속된 갯수, 해당 구슬의 번호) 순으로 리스트를 새롭게 갱신
			ArrayList<Integer> tmp = new ArrayList<>();
			int cnt = 1;
			int cur = list.get(0);
			for(int i = 1; i < list.size(); i++) {
				if(list.get(i) == 0) {	// 0 이후로는 전부 0이므로 반복 종료
					break;
				}
				if(cur == list.get(i)) {// 연속된 구슬의 경우 연속된 갯수 + 1
					cnt++;
				} else {
					tmp.add(cnt);		// 다른 구슬이 나올 경우 지금까지의 갯수와 번호 저장 후 갯수 1로 초기화 및 구슬은 이번 구슬로 초기화
					tmp.add(cur);
					cnt = 1;
					cur = list.get(i);
				}
			}
			tmp.add(cnt);				// 마지막 위치에 있는 구슬은 정보에 추가되지 못하므로 추가
			tmp.add(cur);
			list = tmp;
		}
	}
	static void boom() {	// 4개 이상 연속된 구슬 폭파
		boolean chk = false;
		while(!chk && !list.isEmpty()) {
			ArrayList<Integer> tmp = new ArrayList<>();	// 갱신할 정보를 담는 임시 리스트
			int[] arr = new int[list.size()];			// 폭파대상 구슬 정보를 담기 위한 배열
			chk = true;
			int from = 0;
			int cur = list.get(0);
			for(int i = 1; i < list.size(); i++) {		// 반복문을 돌며 일치하지 않는 위치에서 체크
				if(cur != list.get(i)) {
					int to = i - 1;						// 연속된 마지막 위치
					if(to - from >= 3) {				// 둘의 차이가 3(= 길이는 4) 이상일 경우 다음에도 반복문 확인해야하므로 chk = false
						chk = false;					// 및 반복문을 돌며 해당 위치의 구슬들을 폭파 대상으로 선정
						for(int j = from; j <= to; j++) {
							arr[j] = 1;
						}
					}
					from = i;							// 현재 위치가 일치하지 않는 구슬이었으므로 연속된 구슬 시작점을 i로 갱신 및 구슬번호 갱신
					cur = list.get(from);
				}
			}
			if(cur == list.get(list.size() - 1)) {
				if(from + 3 < list.size()) {
					chk = false;
					for(int j = from; j < list.size(); j++) {
						arr[j] = 1;
					}
				}
			}
			if(chk) {	// 4개 이상 연속된 경우가 없다면 바로 탈출
				break;
			}
			for(int i = 0; i < list.size(); i++) {	// 배열에 담긴 정보를 토대로 임시 리스트에 터지지 않은 구슬만 담기(터진 구슬값은 결과에 추가)
				if(list.get(i) == 0) {
					break;
				}
				if(arr[i] == 0) {
					tmp.add(list.get(i));
				} else {
					result += list.get(i);
				}
			}
			list = tmp;	// 리스트 갱신
		}
	}
	static void filling(int x, int y, int val, int d) {	// 해당 명령에서 갱신된 리스트를 맵에 갱신
		if(x == 0 && y == 0) {
			return;
		}
		boolean exit = false;
		if(val >= n) {
			exit = true;
			val--;
		}
		for(int i = 0; i < val; i++) {
			x += dx[d];
			y += dy[d];
			map[x][y] = tIdx >= list.size() ? 0 : list.get(tIdx++);
		}
		if(exit) {
			return;
		}
		d++;
		for(int i = 0; i < val; i++) {
			x += dx[d];
			y += dy[d];
			map[x][y] = tIdx >= list.size() ? 0 : list.get(tIdx++);
		}
		filling(x, y, val + 1, (d + 1) % 4);
	}
	
	static void fillMap(int x, int y, int val, int d) {	// 맵 전체를 돌며 나선방향으로 리스트에 정보 담기
		if(x == 0 && y == 0) {
			return;
		}
		boolean exit = false;
		if(val >= n) {
			exit = true;
			val--;
		}
		for(int i = 0; i < val; i++) {
			x += dx[d];
			y += dy[d];
			list.add(map[x][y]);
			tMap[x][y] = idx++;
		}
		if(exit) {
			return;
		}
		d++;
		for(int i = 0; i < val; i++) {
			x += dx[d];
			y += dy[d];
			list.add(map[x][y]);
			tMap[x][y] = idx++;
		}
		fillMap(x, y, val + 1, (d + 1) % 4);
	}
}
