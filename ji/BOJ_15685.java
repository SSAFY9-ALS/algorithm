package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Integer.parseInt;

/**
 * 드래곤 커브 / 골드 4 / 101분
 * @author 민정인
 * https://www.acmicpc.net/problem/15685
 */

public class BOJ_15685 {
	static int[] dx = {1, 0, -1, 0};		// 방향
	static int[] dy = {0, -1, 0, 1};
	static List<Integer> result = new ArrayList<>();
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = parseInt(br.readLine());
		StringTokenizer st;
		int[][] map = new int[101][101];	// 맵 배열
		for(int i = 0; i < n; i++) {
			result = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			int x = parseInt(st.nextToken());
			int y = parseInt(st.nextToken());
			int dir = parseInt(st.nextToken());
			int gen = parseInt(st.nextToken());
			Stack<Integer> dirStk = new Stack<>();	// 방향 스택
			dirStk.add(dir);
			dragon(gen, dirStk);					// 전체 방향 함수
			map[x][y] = 1;							// 해당 위치 값 배정
			for(int j = 0; j < result.size(); j++) {	// 방향 리스트 내용을 좌표에 적용
				x += dx[result.get(j)];
				y += dy[result.get(j)];
				map[x][y] = 1;
			}
		}
		int cnt = 0;
		for(int i = 0; i < 100; i++) {
			for(int j = 0; j < 100; j++) {
				if(map[i][j] == 1 && map[i][j+1] == 1 && map[i+1][j] == 1 && map[i+1][j+1] == 1) {
					cnt++;	// 정사각형 여부 확인 및 맞으면 카운트 증가
				}
			}
		}
		System.out.println(cnt);
	}
	static void dragon(int gen, Stack<Integer> stk) {
		if(gen == 0) {
			while(!stk.isEmpty()) {
				result.add(stk.pop());
			}
			Collections.reverse(result);
			return;
		}
		Stack<Integer> tmp = new Stack<>();
		List<Integer> tList = new ArrayList<>();		// 기존 방향 스택을 리스트에 별도 저장
		List<Integer> ttList = new ArrayList<>();		// 기존 방향 스택에서 새롭게 정의된 방향 리스트
		while(!stk.isEmpty()) {
			int t = stk.pop();
			tList.add(t);
			ttList.add((t + 1) % 4);
		}
		Collections.reverse(tList);
		for(int i = 0; i < tList.size(); i++) {
			tmp.add(tList.get(i));
		}
		for(int i = 0; i < ttList.size(); i++) {
			tmp.add(ttList.get(i));
		}
		dragon(gen - 1, tmp);
	}
}
