import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 
 * 로봇 청소기 / 골드5 / 120분
 * https://www.acmicpc.net/problem/14503
 *
 */
public class Main {
	static int n;
	static int m;
	static int count;
	// 청소 한 횟수
	static int[][] matrix;
	// 청소 안된 구역 : 0, 청소 된 구역 : 2, 벽 : 1
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void clean(int x, int y, int dir) {
		if (matrix[x][y] == 0) {
			// 해당 값이 청소가 되지 않았으면 청소 후에 횟수 증가
			matrix[x][y] = 2;
			count++;
		}
		int check = 0;
		for (int i = 0; i < 4; i++) {
			int lx = x + dx[i];
			int ly = y + dy[i];
			if (matrix[lx][ly] == 0) {
				check = 1;
				break;
				// 4방향 중 한방향이라도 청소가 안되어 있으면 break;
			}
		}
		if (check != 1) {
			// 후진
			int lx = x - dx[dir];
			int ly = y - dy[dir];
			if (lx >= 1 && lx < n - 1 && ly >= 1 && ly < m - 1 && matrix[lx][ly] != 1) {
				// 벽이 아니면 후진
				clean(lx, ly, dir);
			} else {
				return;
			}
		} else {
			// 전진
			dir--;
			if (dir == -1)
				dir = 3;
			// 반시계 방향으로 90 회전
			int lx = x + dx[dir];
			int ly = y + dy[dir];
			if (lx >= 1 && lx < n - 1 && ly >= 1 && ly < m - 1 && matrix[lx][ly] == 0) {
				// 청소가 안되어 있으면 전진
				clean(lx, ly, dir);
			} else {
				// 청소가 되어있으면 같은 자리에서 방향만 회전한 채로 clean 실행
				clean(x, y, dir);
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		matrix = new int[n][m];
		st = new StringTokenizer(br.readLine());
		int start_x = Integer.parseInt(st.nextToken());
		int start_y = Integer.parseInt(st.nextToken());
		int start_dir = Integer.parseInt(st.nextToken());
		// 0인 경우 북쪽, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		clean(start_x, start_y, start_dir);
		// 시작 좌표와 시작 방향으로 clean 시작
		System.out.println(count);

	}
}