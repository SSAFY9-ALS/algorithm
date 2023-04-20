package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * 달리기 / 실패 / 2시간+
 * https://www.acmicpc.net/problem/16930
 */

public class BOJ_16930 {
	static int n, m, k;
	static char[][] map;
	
	static int findMinRoute(int startX, int startY, int goalX, int goalY) {
		int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {startX, startY, 0, -1});
		map[startX][startY] = 'X';
		
		int dx, dy;
		int[] temp;
		while(!queue.isEmpty()) {
			temp = queue.poll();
			for(int c = 0; c < 4; c++) {
				dx = temp[0];
				dy = temp[1];
				for(int i = 1; i <= k; i++) {
					dx += d[c][0];
					dy += d[c][1];
					if(dx < 0 || dx >= n || dy < 0 || dy >= m || map[dx][dy] == '#')
						break;
					else if(map[dx][dy] == 'X' && temp[3] == c)
						break;
					else if(map[dx][dy] == 'X')
						continue;
					if(dx == goalX && dy == goalY)
						return temp[2] + 1;
					queue.offer(new int[] {dx, dy, temp[2]+1, c});
					map[dx][dy] = 'X';
				}
			}
		}
		return -1;
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[n][m];
		
		String sen;
		for(int i = 0; i < n; i++) {
			sen = in.readLine();
			for(int j = 0; j < m; j++)
				map[i][j] = sen.charAt(j);
		}
		
		st = new StringTokenizer(in.readLine());
		
		int startX = Integer.parseInt(st.nextToken()) - 1;
		int startY = Integer.parseInt(st.nextToken()) - 1;
		int goalX = Integer.parseInt(st.nextToken()) - 1;
		int goalY = Integer.parseInt(st.nextToken()) - 1;
		
		System.out.println(findMinRoute(startX, startY, goalX, goalY));
	}
}