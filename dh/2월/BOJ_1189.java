import java.io.*;
import java.util.*;
/**
 * 
 * 컴백홈 / 실버1 / 35분
 * https://www.acmicpc.net/problem/1189
 * 
 */
public class Main {
	static int r, c, k;
	static char[][] map;
	static int answer;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		map = new char[r][];
		boolean[][] visited = new boolean[r][c];
		for(int i = 0; i < r; i++) {
			map[i] = br.readLine().toCharArray();
		}
		dfs(r-1, 0, 1, visited);
		// 왼쪽 아래에서 시작
		System.out.println(answer);
		
		
	}
	static void dfs(int x, int y, int count, boolean[][] visited) {
		if(x == 0 && y == c-1) {
			// 오른쪽 위면 실행
			if(k == count) {
				answer++;
				// 깊이우선탐색중 집에 도착하고 거리가 k면 answer 1증가
			}
		}
		
		for(int i = 0 ; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if(lx >= 0 && lx < r && ly >= 0 && ly < c && visited[lx][ly] == false && map[lx][ly] == '.') {
				visited[x][y] = true;
				dfs(lx, ly, count+1, visited);
				visited[x][y] = false;
				// 해당 칸을 방문체크하고 다음 칸을 탐색 후 방문 회수
			}
		}
		
	}
}
