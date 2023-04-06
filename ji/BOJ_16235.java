package net.acmicpc;

import java.util.*;
import java.io.*;
import java.awt.*;

/**
 * 나무 재테크 / 골드 3 / 231분
 * @author 민정인
 * https://www.acmicpc.net/problem/16235
 */

public class BOJ_16235 {
	static int n, m;
	static int[][] val, a;
	static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};	// 8방 탐색
	static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	static Deque<Integer>[][] map;
	static ArrayList<Point> list = new ArrayList<>();
	static class Dead {	// 죽은 나무의 나이 정보 저장
		Point p;
		int age;
		public Dead(Point p, int age) {
			this.p = p;
			this.age = age;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		val = new int[n+1][n+1];
		a = new int[n+1][n+1];
		map = new ArrayDeque[n+1][n+1];
		for(int i = 0; i <= n; i++) {
			for(int j = 0; j <= n; j++) {
				val[i][j] =  5;
				map[i][j] = new ArrayDeque<Integer>();
			}
		}
		for(int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			list.add(new Point(x, y));
			map[x][y].add(age);
		}
		for(int i = 0; i < k; i++) {
			seasons();
		}
		
		int result = 0;
		for(int i = 1; i <= n; i++) {		// 각 위치에서 덱의 사이즈 합 = 나무의 수
			for(int j = 1; j <= n; j++) {
				result += map[i][j].size();
			}
		}
		System.out.println(result);
	}
	static void seasons() {
		ArrayList<Dead> deadTree = new ArrayList<>();	// 죽은 나무 정보
		ArrayList<Point> nextTree = new ArrayList<>();	// 번식할 나무 정보
		// spring
		for(int i = 0; i < list.size(); i++) {
			Point p = list.get(i);
			Deque<Integer> tmp = new ArrayDeque<>();
			while(!map[p.x][p.y].isEmpty()){
				int t = map[p.x][p.y].poll();
				if(val[p.x][p.y] >= t) {
					val[p.x][p.y] -= t;
					tmp.add(t + 1);
					if((t + 1) % 5 == 0) {		// 올해 나이가 5의 배수인 경우 정보 저장
						nextTree.add(p);
					}
				} else {
					deadTree.add(new Dead(p, t / 2));	// 양분을 더 섭취하지 못할 경우 나이를 2로 나눠서 저장
				}
			}
			map[p.x][p.y].addAll(tmp); 			// 전체 순회 후 남은 정보들 저장
		}
		// summer
		for(int i = 0; i < deadTree.size(); i++) {	// 죽은 나무의 양분 추가
			Point p = deadTree.get(i).p;
			int age = deadTree.get(i).age;
			val[p.x][p.y] += age; 
		}
		// fall
		for(int i = 0; i < nextTree.size(); i++) {	// 번식할 나무들 각 장소에 추가
			Point p = nextTree.get(i);
			for(int k = 0; k < 8; k++) {
				if(p.x + dx[k] <= 0 || p.x + dx[k] > n
						|| p.y + dy[k] <= 0 || p.y + dy[k] > n) {
					continue;
				}
				map[p.x + dx[k]][p.y + dy[k]].addFirst(1);	// 항상 1인 나무들 추가이므로 가장 앞에 추가
				Point next = new Point(p.x + dx[k], p.y + dy[k]);
				if(list.indexOf(next) == -1) {
					list.add(next);
				}
			}
		}
		// winter
		for(int i = 1; i <= n; i++) {		// 각 좌표에 양분 추가
			for(int j = 1; j <= n; j++) {
				val[i][j] += a[i][j];
			}
		}
	}
}
