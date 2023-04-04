import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
 * 적록색약 / 골드5 / 30분
 * https://www.acmicpc.net/problem/10026
 */

public class Main {
	static int n;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static char[][] color;
	static boolean[][] visited;
	// 방문한 기록 저장

	public static void bfs(int x, int y, char c) {
		// 일반사람의 구역 탐색 bfs
		visited[x][y] = true;
		// 방문한 곳은 true로 변경
		for (int i = 0; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if (lx >= 0 && lx < n && ly >= 0 && ly < n && color[lx][ly] == c && visited[lx][ly] == false) {
				// 그리드 안에 (lx,ly)위치의 color가 같고 방문하지 않았으면 lx, ly 인자를 갖고 bfs 호출
				bfs(lx, ly, c);
			}
		}
	}

	public static void redgreen_bfs(int x, int y, char c) {
		// 적록색약인 사람의 구역 탐색 bfs
		visited[x][y] = true;
		for (int i = 0; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if (lx >= 0 && lx < n && ly >= 0 && ly < n && visited[lx][ly] == false) {
				// 그리드 안에 (lx,ly)위치를 방문하지 않았으면 실행
				if (c == 'R' || c == 'G') {
					if (color[lx][ly] == 'R' || color[lx][ly] == 'G') {
						// 현재위치의 색깔이 레드 혹은 그린이고, 
						// lx,ly위치의 색깔 또한 레드 혹은 그린이면 redgreen_bfs함수 호출
						redgreen_bfs(lx, ly, c);
					}
				}
				else {
					if (color[lx][ly] == c) {
						// 현재 위치 색깔이 블루이고 lx,ly의 위치 또한 블루이면 redgreen_bfs함수 호출
						redgreen_bfs(lx, ly, c);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		color = new char[n][n];
		visited = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String s = br.readLine();
			for (int j = 0; j < n; j++) {
				color[i][j] = s.charAt(j);
			}
		}
		int count = 0;

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == false) {
					// 방문하지 않은 위치면 bfs실행하여 같은 구역을 visited함수내에서 true로 변경하기 위해 bfs실행
					bfs(i, j, color[i][j]);
					count++;
				}
			}
		}

		visited = new boolean[n][n];
		int redgreen_count = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == false) {
					// 방문하지 않은 위치면 bfs실행하여 같은 구역을 visited함수내에서 true로 변경하기 위해 redgreen_bfs실행
					redgreen_bfs(i, j, color[i][j]);
					redgreen_count++;
				}
			}
		}
		System.out.println(count + " " + redgreen_count);

	}
}
