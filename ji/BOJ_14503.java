package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 로봇 청소기 / 골드 5 / 80분
 * @author 민정인
 * https://www.acmicpc.net/problem/14503
 */

public class BOJ_14503 {
	static int n;
	static int m;
	static int[][] map;
	static int[][] d = {{-1,0},{0,1},{1,0},{0,-1}};	// 북, 동, 남, 서 방향을 나타내는 배열
	static int cnt = 0;	// 총 청소한 칸 수
	static void cleanRoom(int stX, int stY, int dir) {	// 청소 함수
		if(map[stX][stY] == 0) {	// 현재 위치가 0이라면 2로 바꾼 후 횟수 증가
			map[stX][stY] = 2;
			cnt++;
		}
		int chk = 0;
		for(int i = 1; i < 5; i++) {	// 반시계 방향으로 돌면서 해당 위치에 0이 있는지 확인
			int tmp = (dir + 4 - i) % 4;
			if(stX+d[tmp][0] < 0 || stX+d[tmp][0] >= n || stY+d[tmp][1] < 0 || stY+d[tmp][1] >= m) {
				chk++;
				continue;
			}
			if(map[stX+d[tmp][0]][stY+d[tmp][1]] == 0) {	// 있는 경우 해당 위치로 들어감. 최대 횟수를 구하는 것이 아니므로 재귀 탈출시 return
				cleanRoom(stX+d[tmp][0], stY+d[tmp][1], tmp);
				return;
			} else {
				chk++;
			}
		}
		if(chk == 4) {	// 모든 방향에 청소 가능한 곳이 없는 경우
			int tmp = (dir + 2) % 4;	// 임시로 반대 방향을 지정해서 뒤로 후진. 단 벽이 없어야 함
			if(stX+d[tmp][0] < 0 || stX+d[tmp][0] >= n || stY+d[tmp][1] < 0 || stY+d[tmp][1] >= m || map[stX+d[tmp][0]][stY+d[tmp][1]] == 1) {
				return;	// 후진하려는 장소에 벽이 있는 경우 종료
			}
			cleanRoom(stX+d[tmp][0], stY+d[tmp][1], dir);	// 후진에 문제없는 경우 후진 후 다시 재귀 진입
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int stX = Integer.parseInt(st.nextToken());
		int stY = Integer.parseInt(st.nextToken());
		int dir = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cleanRoom(stX, stY, dir);
		System.out.println(cnt);
	}
}
