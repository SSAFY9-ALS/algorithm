package algorithm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 연구소 / 골드4 / 2시간
 * https://www.acmicpc.net/problem/14502
 */

public class BOJ_14502 {
	static int n, m, max = Integer.MIN_VALUE;
	static char[][] map, check;
	static ArrayDeque<Point> queue = new ArrayDeque<Point>();
	static ArrayList<Point> virus = new ArrayList<Point>();
	
	// 연구소 범위 안인지 판별하는 메서드
	static boolean isRange(int x, int y, int xSize, int ySize) {
		if(x >= 0 && x < xSize && y >= 0 && y < ySize)
			return true;
		return false;
	}
	
	// 주어진 좌표에 바이러스가 확산될 수 있는지 판별하는 메서드
	static boolean isInfection(int x, int y) {
		if(isRange(x, y, n, m) && check[x][y] == '0')
			return true;
		return false;
	}
	
	// 바이러스를 확산시키는 메서드
	static void findInfection() {
		while(!queue.isEmpty()) {
			Point p = queue.remove();
			int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
			for(int i = 0; i < 4; i++) {
				if(isInfection(p.x + d[i][0], p.y + d[i][1])) {
					check[p.x + d[i][0]][p.y + d[i][1]] = '2';
					queue.add(new Point(p.x + d[i][0], p.y + d[i][1]));
				}
			}
		}
	}
	
	// 안전 영역 크기를 세는 메서드
	static int countSafeSection() {
		int sum = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(check[i][j] == '0')
					sum++;
			}
		}
		return sum;
	}
	
	// 세울 수 있는 벽 3개의 조합을 구하는 메서드
	static void findCombination(int r, int idxX, int idxY, int cnt) {
		if(r == 0) {
			for(int i = 0; i < n; i++)
				check[i] = map[i].clone();
			for(int i = 0; i < virus.size(); i++) {
				Point p = virus.get(i);
				queue.add(new Point(p.x, p.y));
			}
			findInfection();
			int sum = countSafeSection();
			if(max < sum)
				max = sum;
			return;
		}
		for(int i = idxX; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(i == idxX && j <= idxY)
					continue;
				if(map[i][j] == '0') {
					map[i][j] = '1';
					findCombination(r-1, i, j, cnt+1);
					map[i][j] = '0';
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		check = new char[n][];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0; j < m; j++) {
				map[i][j] = st.nextToken().charAt(0);
				if(map[i][j] == '2')
					virus.add(new Point(i, j));
			}
		}
		findCombination(3, 0, -1, 0);
		System.out.println(max);
	}
}
