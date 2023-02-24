package net.acmicpc;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * 뱀 / 골드 4 / 118분
 * @author 민정인
 * https://www.acmicpc.net/problem/3190
 */
public class BOJ_3190 {
	static int n, k, l;
	static int[][] map;						// 전체 맵의 정보
	static int[] dx = {0, 1, 0, -1};		// 방향 이동을 위한 배열
	static int[] dy = {1, 0, -1, 0};
	static Map<Integer, Integer> d = new HashMap<>();	// 방향 전환을 위한 맵
	static List<Point> tail = new ArrayList<>();		// 꼬리를 얻기 위한 리스트
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		k = Integer.parseInt(br.readLine());
		map = new int[n+1][n+1];
		StringTokenizer st;
		for(int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;	// 사과의 위치를 1로 저장
		}
		l = Integer.parseInt(br.readLine());
		for(int i = 0; i < l; i++) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			char dir = st.nextToken().charAt(0);
			if(dir == 'L') {	// L일 경우 반시계 방향으로 돌기 때문에 -1
				d.put(time, -1);
			} else {			// D일 경우 시계 방향으로 돌기 때문에 1
				d.put(time, 1);
			}
		}
		Point p = new Point(1, 1);	// 시작좌표 설정
		tail.add(new Point(1, 1));	// 초기 꼬리 좌표 설정
		moving(p, 0);					// 뱀의 이동을 위한 dfs 함수
		System.out.println(cnt);
	}
	static int cnt = 1;				// 이동 시간을 저장
	static void moving(Point p, int dir) {
		int x = p.x + dx[dir];		// 다음 좌표 저장
		int y = p.y + dy[dir];
		Point next = new Point(x, y);	// 다음 좌표로 새로운 포인터 생성
		if(x < 1 || x > n || y < 1 || y > n || tail.indexOf(next) != -1) {	// 범위를 벗어나거나 몸에 부딪히는 경우 종료
			return;
		}
		tail.add(next);				// 다음 좌표를 리스트에 추가(가장 앞은 꼬리 좌표)
		if(map[x][y] == 1) {		// 사과가 있으면 해당 좌표 0으로 설정
			map[x][y] = 0;
		} else {					// 없다면 꼬리 제거
			tail.remove(0);
		}
		if(d.get(cnt) != null) {	// 방향 전환 시간이 오면 앞의 행동이 끝난 후 방향 전환
			dir = (dir + d.get(cnt) + 4) % 4;	// 음수나 4를 넘어가는 경우에 대한 처리
		}
		cnt++;
		moving(next, dir);			// 재귀
	}
}
