package net.acmicpc;

import java.io.*;
import java.util.*;
import java.awt.*;

/**
 * 주사위 굴리기 / 골드 4 / 37분
 * @author 민정인
 * https://www.acmicpc.net/problem/14499
 */

public class BOJ_14499 {
	static int n, m;
	static int[][] map;
	static Point cur;
	static int[] dx = {0, 0, 0, -1, 1};
	static int[] dy = {0, 1, -1, 0, 0};
	static int[][] dice = {		// 주사위 전개도
		{0, 0, 0},	// 가운데만 사용
		{0, 0, 0},	// 세칸 전부 사용
		{0, 0, 0},	// 가운데만 사용
		{0, 0, 0}	// 가운데만 사용
	};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new int[n][m];
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		cur = new Point(x, y);
		int k = Integer.parseInt(st.nextToken());
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int cmd = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < k; i++) {
			cmd = Integer.parseInt(st.nextToken());	// 이동 명령
			move(cmd);								// 이동 및 출력
		}
	}

	static void move(int cmd) {
		if(cur.x + dx[cmd] < 0 || cur.x + dx[cmd] >= n || cur.y + dy[cmd] < 0 || cur.y + dy[cmd] >= m) {
			return;		// 범위 밖으로 나가는 명령은 생략
		}
		int nextX = cur.x + dx[cmd];	// 다음 좌표 저장
		int nextY = cur.y + dy[cmd];
		int tmp = 0;
		switch(cmd) {					// 명령 방향에 따라 전개도 내부 값 swap. 각 값들에서 방향에 따라 4개의 값이 바뀜
		case 1:
			tmp = dice[1][0];
			dice[1][0] = dice[3][1];
			dice[3][1] = dice[1][2];
			dice[1][2] = dice[1][1];
			dice[1][1] = tmp;
			break;
		case 2:
			tmp = dice[1][2];
			dice[1][2] = dice[3][1];
			dice[3][1] = dice[1][0];
			dice[1][0] = dice[1][1];
			dice[1][1] = tmp;
			break;
		case 3:
			tmp = dice[0][1];
			dice[0][1] = dice[1][1];
			dice[1][1] = dice[2][1];
			dice[2][1] = dice[3][1];
			dice[3][1] = tmp;
			break;
		case 4:
			tmp = dice[3][1];
			dice[3][1] = dice[2][1];
			dice[2][1] = dice[1][1];
			dice[1][1] = dice[0][1];
			dice[0][1] = tmp;
			break;
		}
		System.out.println(dice[1][1]);	// 이동 후 위쪽 면의 값 출력
		if(map[nextX][nextY] == 0) {	// 해당 칸의 값에 따라 값을 복사하거나 맵에 값을 입력
			map[nextX][nextY] = dice[3][1];
		} else {
			dice[3][1] = map[nextX][nextY];
			map[nextX][nextY] = 0;
		}
		cur = new Point(nextX, nextY);
	}
}
