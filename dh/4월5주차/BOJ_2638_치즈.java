import java.util.*;
import java.awt.Point;
import java.io.*;

/**
 * 
 * 치즈 / 골드 3 / 40분
 * https://www.acmicpc.net/problem/2638
 * 
 */

public class Main {
	static int n, m;
	static int[][] cheese;
	static boolean[][] visited;
	static int[][] touched;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		cheese = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		while(true) {
			if(noCheese())
				break;
			// 치즈가 없으면 탈출
			melting();
			// 치즈 녹이기
			answer++;
			// 치즈 녹인 시간 체크
		}
		System.out.println(answer);
	}
	public static void melting() {
		visited = new boolean[n][m];
		touched = new int[n][m];
		bfs();
		// 녹일 치즈 탐색 및 체크
		delete();
		// 치즈 녹이기
		
	}

	public static void delete() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(touched[i][j] >= 2) {
					cheese[i][j] = 0;
					// 두번 이상 탐색된 치즈들은 삭제
				}
			}
		}
	}
	public static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(0,0));
		visited[0][0] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int lx = p.x + dx[i];
				int ly = p.y + dy[i];
				if(lx >= 0 && lx < n && ly >= 0 && ly < m && !visited[lx][ly]) {
					if(cheese[lx][ly] == 1) {
						touched[lx][ly]++;
						// 치즈면 탐색된 횟수를 증가
					}
					else {
						q.add(new Point(lx, ly));
						visited[lx][ly] = true;
						// 치즈가 아니면 큐에 넣어 다음 탐색
					}
				}
			}
		}
	}

	public static boolean noCheese() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(cheese[i][j] == 1)
					return false;
				// 치즈가 남아있는지 확인하는 함수
			}
		}
		return true;
	}
}