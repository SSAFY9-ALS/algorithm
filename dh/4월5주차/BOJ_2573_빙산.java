import java.util.*;
import java.awt.Point;
import java.io.*;

/**
 * 
 * 빙산 / 골드 4 / 40분
 * https://www.acmicpc.net/problem/2573
 * 
 */

public class Main {
	static int n, m;
	static int[][] ice;
	static boolean[][] visited;
	static int[][] touched;
	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		ice = new int[n][m];
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				ice[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		int answer = 0;
		while(true) {
			// 빙산이 있는지 탐색
			if(noIce()) {
				System.out.println(0);
				return;
			}
			// 두 덩어리 탐색
			visited = new boolean[n][m];
			if(separ()) {
				System.out.println(answer);
				return;
			}
			melting();
			answer++;
			// 빙산 녹인 시간 체크
		}
	}
	
	public static void melting() {
		visited = new boolean[n][m];
		touched = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(ice[i][j] == 0 && !visited[i][j]) {
					bfs(i, j);
				}
			}
		}
		delete();
		
	}

	public static void delete() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(touched[i][j] >= 1) {
					ice[i][j] -= touched[i][j];
					// 닿은 만큼 줄이기
					if(ice[i][j] < 0)
						ice[i][j] = 0;
					// 줄인 결과가 0 미만이면 0으로 저장
				}
			}
		}
	}
	public static void bfs(int x, int y) {
		Queue<Point> q = new ArrayDeque<>();
		q.add(new Point(x,y));
		visited[x][y] = true;
		while(!q.isEmpty()) {
			Point p = q.poll();
			for(int i = 0; i < 4; i++) {
				int lx = p.x + dx[i];
				int ly = p.y + dy[i];
				if(lx >= 0 && lx < n && ly >= 0 && ly < m && !visited[lx][ly]) {
					if(ice[lx][ly] >= 1) {
						touched[lx][ly]++;
						// 빙산과 닿으면 해당 touched 값 1증가
					}
					else {
						q.add(new Point(lx, ly));
						visited[lx][ly] = true;
						// 다음 물 탐색
					}
				}
			}
		}
	}
	
	public static boolean separ() {
		int check = 0;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(ice[i][j] >= 1 && !visited[i][j]) {
					check++;
					// 한 덩어리 체크
					if(check > 1) {
						return true;
						// 덩어리가 2개 이상 나오면 true 리턴
					}
					Queue<Point> q = new ArrayDeque<>();
					q.add(new Point(i,j));
					visited[i][j] = true;
					while(!q.isEmpty()) {
						Point p = q.poll();
						for(int k = 0; k < 4; k++) {
							int lx = p.x + dx[k];
							int ly = p.y + dy[k];
							if(lx >= 0 && lx < n && ly >= 0 && ly < m && !visited[lx][ly]) {
								if(ice[lx][ly] >= 1) {
									q.add(new Point(lx, ly));
									visited[lx][ly] = true;
									// 덩어리들 탐색
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	public static boolean noIce() {
		for(int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if(ice[i][j] >= 1)
					// 빙산이 존재하면 false 리턴
					return false;
			}
		}
		return true;
	}
}