package algorithm;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 수영장 만들기 / 골드1 / 2시간 30분
 * https://www.acmicpc.net/problem/1113
 */

public class BOJ_1113 {
	static int min;
	static int[][] pool;
	static boolean[][] visited;
	static ArrayList<Point> list;
	
	// 인접한 높이가 같은 땅을 하나로 묶고, 최소 높이를 구하는 함수
	static void dfs(int x, int y, int h) {
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; // 상하좌우
		list.add(new Point(x, y));
		
		int dx, dy;
		for(int k = 0; k < 4; k++) { // 상하좌우 탐색
			dx = x + d[k][0];
			dy = y + d[k][1];
			
			if(pool[dx][dy] == 0) { // 인접한 땅이 외곽이면
				min = 0; // 최소 높이는 0
			}
			else {
				if(pool[dx][dy] == h && !visited[dx][dy]) { // 인접한 땅이 현재 높이와 같다면
					visited[dx][dy] = true;
					dfs(dx, dy, h); // dfs 탐색을 통해 하나로 묶음
				}
				else if(pool[dx][dy] != h && min > pool[dx][dy]) { // 인접한 땅이 현재까지의 최소 높이보다 더 높이가 낮을 때
					min = pool[dx][dy]; // 값 갱신
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		pool = new int[n+2][m+2];
		visited = new boolean[n+2][m+2];
		int max = 0;
		
		String sen;
		for(int i = 0; i < n; i++) {
			sen = in.readLine();
			for(int j = 0; j < m; j++) {
				pool[i+1][j+1] = Character.getNumericValue(sen.charAt(j));
				if(pool[i+1][j+1] > max)
					max = pool[i+1][j+1];
			}
		}
		
		int result = 0;
		for(int h = 1; h <= max; h++) { // 높이 1부터 가능한 최대 높이까지 탐색
			// 모든 수영장 땅 탐색
			for(int i = 1; i <= n; i++) {
				for(int j = 1; j <= m; j++) {
					if(pool[i][j] == h && !visited[i][j]) { // 현재 확인하는 높이와 땅의 높이가 같다면
						// 하나로 묶어서 최소 높이를 구함
						list = new ArrayList<>();
						min = 9;
						visited[i][j] = true;
						dfs(i, j, h); // 인접한 높이가 같은 땅을 하나로 묶고, 최소 높이를 구하는 함수
						
						// 구한 최소 높이가 현재 확인하는 높이보다 클 때
						if(min > h) {
							for(Point p: list) { // 구한 인접 정점들에 대하여
								// 값 갱신
								result += min - h;
								pool[p.x][p.y] = min;
								visited[p.x][p.y] = false;
							}
						}
					}
				}
			}
		}
		System.out.println(result); // 결과 출력
	}
}