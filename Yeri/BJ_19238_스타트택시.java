package swA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 스타트 택시 / 골드2 / 2시간
 * https://www.acmicpc.net/problem/19238
 */

class Pos implements Comparable<Pos> {
	int r;
	int c;
	int count; // 연료
	int index;

	public Pos(int r, int c, int count, int index) {
		super();
		this.r = r;
		this.c = c;
		this.count = count;
		this.index = index;
	}

	public int getR() {
		return r;
	}

	public void setR(int r) {
		this.r = r;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	public int compareTo(Pos o) {
		if (this.getCount() < o.getCount()) {
			return -1;
		} else if (this.getCount() == o.getCount()) {
			if (this.getR() < o.getR()) {
				return -1;
			} else if (this.getR() == o.getR()) {
				return this.getC() < o.getC() ? -1 : 1;
			}else {
				return 1;
			}
		}else {
			return 1;
		}
//		return -1;
	}
}

public class BJ_19238_스타트택시 {
	/* 지도 */
	static int[][] map;
	static int[][] copyMap;
	static int currR;
	static int currC;
	static int N;

	/* 방향 */
	static int[][] dr = { { -1, 0 }, { 0, -1 }, { 0, 1 }, { 1, 0 } };

	/* 연료 */
	static int fuel;

	/* 성공한 사람 수 */
	static int success;

	/* 전체 승객 수 */
	static int customerNum;

	/* 상태 false : 손님 찾는 중, true 손님 탐 */
	static boolean searchCus = false;

	/* 손님 위치, */
	static ArrayList<Pos> start = new ArrayList<>();
	static ArrayList<Pos> goal = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		map = new int[N + 1][N + 1];
		copyMap = new int[N + 1][N + 1];
		customerNum = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		for (int r = 1; r <= N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 1; c <= N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				copyMap[r][c] = map[r][c];
			}
		}
		st = new StringTokenizer(br.readLine());
		currR = Integer.parseInt(st.nextToken());
		currC = Integer.parseInt(st.nextToken());
		int temp = 1;
		for (int i = 0; i < customerNum; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
//			start.add(new Pos(r, c, 0, temp));
			map[r][c] = -temp; // 손님 위치 기록
			copyMap[r][c] = -temp; // 복사

			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			goal.add(new Pos(r, c, 0, temp));
			temp++;
		}

		// map == 손님 찾기 용
		// copy 목적지 찾기 용
		for (int i = 0; i < customerNum; i++) {
			fuel -= findCus(0);
			if (fuel <= 0) {
				fuel = -1;
				break;
			}
			int cus = map[currR][currC]; // 손님 번호 음수-1~
			copyMap[goal.get(-cus - 1).getR()][goal.get(-cus - 1).getC()] = cus; // 도착지 설정
			copyMap[currR][currC] = 0;
			map[currR][currC] = 0;
			temp = findDestination(0, cus);
			if (fuel - temp < 0) {
				fuel = -1;
				break;
			}
			fuel += temp;
//			map[currR][currC] = 0;
			copyMap[currR][currC] = 0;
		}
		System.out.println(fuel);

	}

	private static int findCus(int count) {
		List<Pos> que = new ArrayList<Pos>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		que.add(new Pos(currR, currC, count, 0));
		visited[currR][currC] = true;
		while (!que.isEmpty()) {
			Pos curr = que.get(0);
			que.remove(0);
			if (map[curr.getR()][curr.getC()] < 0) { // 손님 찾음
				currR = curr.getR();
				currC = curr.getC();
				return curr.getCount();
			}
			for (int[] d : dr) {
				int newR = curr.getR() + d[0];
				int newC = curr.getC() + d[1];
				if (newR > N || newR < 1 || newC > N || newC < 1) {
					continue;
				}
				if (map[newR][newC] != 1 && !visited[newR][newC]) {
					que.add(new Pos(newR, newC, curr.getCount() + 1, 0));
					visited[newR][newC] = true;
				}
			}
			Collections.sort((List<Pos>) que);
//			count++;
		}
		return Integer.MAX_VALUE;
	}

	private static int findDestination(int count, int cus) {
		List<Pos> que = new ArrayList<Pos>();
		boolean[][] visited = new boolean[N + 1][N + 1];
		que.add(new Pos(currR, currC, count, 0));
		visited[currR][currC] = true;
		while (!que.isEmpty()) {
			Pos curr = que.get(0);
			que.remove(0);
			if (copyMap[curr.getR()][curr.getC()] == cus) { // 목적지
				currR = curr.getR();
				currC = curr.getC();
				return curr.getCount();
			}
			for (int[] d : dr) {
				int newR = curr.getR() + d[0];
				int newC = curr.getC() + d[1];
				if (newR > N || newR < 1 || newC > N || newC < 1) {
					continue;
				}
				if (copyMap[newR][newC] != 1 && !visited[newR][newC]) {
					que.add(new Pos(newR, newC, curr.getCount() + 1, 0));
					visited[newR][newC] = true;
				}
			}
			Collections.sort((List<Pos>) que);
		}
		return Integer.MAX_VALUE;
	}
}
