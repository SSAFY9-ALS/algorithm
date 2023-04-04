import java.io.*;
import java.util.*;

/**
 * 
 * 스도쿠 / 골드4 / 60분
 * https://www.acmicpc.net/problem/2580
 * 
 */

public class Main {

	static int[][] matrix = new int[9][9];
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; st.hasMoreTokens(); j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dfs(0, 0);
	}

	public static void dfs(int row, int col) {

		if (col == 9) {
			dfs(row + 1, 0);
			// 열의 마지막이면 다음 행 첫번쨰 열로 dfs 호출
			return;
		}

		if (row == 9) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					System.out.print(matrix[i][j] + " ");
				}System.out.println();
			}
			System.exit(0);
			// 강제종료
		}

		if (matrix[row][col] == 0) {
			// 0일때 i를 넣어주며 값 찾기
			for (int i = 1; i <= 9; i++) {
				if (check(row, col, i)) {
					matrix[row][col] = i;
					// i 넣어주어 dfs 돌리기
					dfs(row, col + 1);
				}
			}
			matrix[row][col] = 0;
			// 돌아온다는 것은 유효성 검사에 걸렸기 때문
			// 다시 0으로 변환
			return;
		}
		dfs(row, col + 1);
		// 0이 아니면 다음 탐색
	}

	public static boolean check(int row, int col, int value) {

		// 열 검사
		for (int i = 0; i < 9; i++) {
			if (matrix[row][i] == value) {
				return false;
			}
		}

		// 행 검사
		for (int i = 0; i < 9; i++) {
			if (matrix[i][col] == value) {
				return false;
			}
		}

		// 3x3 검사
		int ind_row = (row / 3) * 3;
		int ind_col = (col / 3) * 3;

		for (int i = ind_row; i < ind_row + 3; i++) {
			for (int j = ind_col; j < ind_col + 3; j++) {
				if (matrix[i][j] == value)
					return false;
			}
		}
		return true;
	}

}
