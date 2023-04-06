import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/* 
 * 영역 구하기 / 실버1 / 40분
 * https://www.acmicpc.net/problem/2583
 */

public class Main {
	static int n;
	static int m;
	static int k;
	static int dx[] = { -1, 0, 1, 0 };
	static int dy[] = { 0, 1, 0, -1 };
	static int[][] matrix;
	static boolean[][] visited;
	static int count;
	// 방문한 기록 저장

	public static void bfs(int x, int y) {
		visited[x][y] = true;
		count++;
		// 해당 값은 0이므로 한 영역의 1의 넓이를 가지고 있으므로 count 1증가
		for (int i = 0; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if (lx >= 0 && lx < m && ly >= 0 && ly < n && visited[lx][ly] == false && matrix[lx][ly] == 0) {
				// lx, ly 위치가 방문하지 않았고 값이 0이면 bfs 실행
				bfs(lx, ly);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] s = br.readLine().split(" ");
		n = Integer.parseInt(s[0]);
		m = Integer.parseInt(s[1]);
		k = Integer.parseInt(s[2]);

		matrix = new int[m][n];
		visited = new boolean[m][n];

		for (int index = 0; index < k; index++) {
			s = br.readLine().split(" ");
			int x1 = Integer.parseInt(s[0]);
			int y1 = Integer.parseInt(s[1]);
			int x2 = Integer.parseInt(s[2]);
			int y2 = Integer.parseInt(s[3]);

			for (int i = x1; i < x2; i++) {
				for (int j = y1; j < y2; j++) {
					// 직사각형의 모양만큼 좌표상에서 1로 표시
					// 기존의 있던 넓이는 0으로 표시되므로 구분됨
					matrix[i][j] = 1;
				}
			}
		}

		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (visited[i][j] == false && matrix[i][j] == 0) {
					// 방문하지 않은 위치면서 matrix값이 0이면 bfs실행
					bfs(i, j);
					list.add(count);
					count = 0;
					// 리스트에 count 저장한 후 초기화
				}
			}
		}

		Collections.sort(list);
		// 오름차순으로 정렬

		System.out.println(list.size());
		for (int tmp : list) {
			System.out.print(tmp + " ");
		}

	}
}