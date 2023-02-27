package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.awt.*;

/**
 * 스타트 택시  / 골드 2 / 109
 * @author 민정인
 * https://www.acmicpc.net/problem/19238
 */

public class BOJ_19238 {
	static int n, m, fuel;
	static int[][] map;			// 맵 정보 저장
	static boolean[][] chk;		// 방문 여부 저장
	static Point start;			// 택시의 출발지점
	static Map<Point, Point> pasList = new HashMap<>();	// 승객의 위치와 목적지 위치 저장
	static int[] dx = {-1, 0, 1, 0};	// 탐색 방향
	static int[] dy = {0, -1, 0, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new int[n+1][n+1];
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		start = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Point pass = (new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			Point dest = (new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			pasList.put(pass, dest);
		}
		while(!fuelEmpty && passenger < m) {	// 연료가 다 떨어지거나 모든 승객을 다 태운 경우 탈출하는 반복문
			chk = new boolean[n+1][n+1];
			findPassenger();
		}
		if(fuelEmpty) {							// 연료가 다 떨어졌다면 -1, 아니라면 남은 연료 출력
			System.out.println(-1);
		} else {			
			System.out.println(fuel);
		}
	}
	static class Cost implements Comparable<Cost>{	// 위치와 해당 위치까지 비용을 저장하는 클래스
		Point p;
		int cost;
		public Cost() {}
		public Cost(Point p, int cost) {
			this.p = p;
			this.cost = cost;
		}
		@Override
		public int compareTo(Cost o) {				// 정렬을 위한 기준
			if(this.cost == o.cost) {				// 1. 비용이 낮은 순으로
				if(this.p.x == o.p.x) {				// 2. 비용이 같다면 행이 낮은순
					return this.p.y - o.p.y;		// 3. 행까지 같다면 열이 낮은순
				}
				return this.p.x - o.p.x;
			}
			return this.cost - o.cost;
		}
	}
	static boolean fuelEmpty;						// 연료 소진여부
	static int passenger;							// 태운 승객 수
	static void findPassenger() {
		Queue<Cost> q = new LinkedList<>();
		q.add(new Cost(start, 0));					// 현재 위치에서 bfs 진행
		chk[start.x][start.y] = true;
		ArrayList<Cost> list = new ArrayList<>();
		int limit = fuel;							// 최소 비용끼리 비교해야 하므로 제한값 지정. 초기에는 남은 연료로
		while(!q.isEmpty()) {
			Cost tmp = q.poll();
			Point tp = tmp.p;
			if(pasList.get(tp) != null) {			// 현재 위치에 승객이 있다면
				if(list.isEmpty()) {				// 최소 거리의 승객을 처음 넣는다면 해당 비용을 제한값으로 둠
					limit = tmp.cost;
				}
				list.add(tmp);
				continue;
			}
			if(tmp.cost > limit) {					// 제한 비용보다 해당 위치까지의 비용이 크다면 생략(초기에는 남은 연료지만
				continue;							// 최소 비용이 지정된다면 해당 값으로 변경됨
			}
			for(int i = 0; i < 4; i++) {
				if(tp.x + dx[i] <= 0 || tp.x + dx[i] > n || tp.y + dy[i] <= 0 || tp.y + dy[i] > n
						|| chk[tp.x + dx[i]][tp.y + dy[i]] || map[tp.x + dx[i]][tp.y + dy[i]] == 1){
					continue;						// 범위 밖 or 이미 방문 or 벽이라면 생략
				}
				Point next = new Point(tp.x + dx[i], tp.y + dy[i]);
				chk[next.x][next.y] = true;
				q.add(new Cost(next, tmp.cost + 1));
			}
		}
		if(list.isEmpty()) {						// 최소 거리의 승객이 없다는 것은 남은 연료로 태울 수 있는 승객이 없으므로 연료 소진
			fuelEmpty = true;
		} else {
			chk = new boolean[n+1][n+1];			
			Collections.sort(list);					// 그게 아니라면 방문여부 초기화 및 최소 거리 및 최소 좌표 승객
			fuel -= list.get(0).cost;				// 비용을 연료에서 빼고 해당 승객을 기준으로 목적지까지 bfs
			bfs(list.get(0).p);
		}
		
	}
	
	static void bfs(Point p) {
		Queue<Cost> q = new LinkedList<>();
		Point dest = pasList.get(p);
		q.add(new Cost(p, 0));
		while(!q.isEmpty()) {
			Cost tmp = q.poll();
			Point tp = tmp.p;
			if(tp.equals(dest)) {					// 목적지까지 도착하면 해당 비용만큼 더해주기
				start = tp;
				pasList.remove(p);
				passenger++;
				fuel += tmp.cost;
				return;
			}
			if(tmp.cost == fuel) {					// 목적지 도달 전 연료가 떨어지면 생략
				continue;
			}
			for(int i = 0; i < 4; i++) {
				if(tp.x + dx[i] <= 0 || tp.x + dx[i] > n || tp.y + dy[i] <= 0 || tp.y + dy[i] > n
						|| chk[tp.x + dx[i]][tp.y + dy[i]] || map[tp.x + dx[i]][tp.y + dy[i]] == 1){
					continue;
				}
				Point next = new Point(tp.x + dx[i], tp.y + dy[i]);
				chk[next.x][next.y] = true;
				q.add(new Cost(next, tmp.cost + 1));
			}
			
		}
		fuelEmpty = true;							// 목적지까지 도달하지 못한 경우 연료 소진
	}
}
