import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/**
 * 
 * 꽃길 / 실버2 / 55분
 * https://www.acmicpc.net/problem/14620
 * 
 */
public class Main {
	static int n;
	static int[][] matrix;
	static int sum;
	static int answer = Integer.MAX_VALUE;
	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		matrix = new int[n][n];
		boolean[][] isSelected = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				matrix[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		comb(0, 1, isSelected);
		// 2차원 조합
		System.out.println(answer);
	}

	static void comb(int count, int start, boolean[][] isSelected) {
		if (count == 3) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (isSelected[i][j] == true) {
						sum += matrix[i][j];
						// 꽃 3개를 심으면 각 자리 값 저장
					}
				}
			}
			answer = Math.min(answer, sum);
			// 최소값 구하기
			sum = 0;
			return;
		}

		for (int i = start; i < n - 1; i++) {
			for (int j = 1; j < n - 1; j++) {
				if (isSelected[i][j] 
						|| isSelected[i + dx[0]][j + dy[0]]
						|| isSelected[i + dx[1]][j + dy[1]]
						|| isSelected[i + dx[2]][j + dy[2]]
						|| isSelected[i + dx[3]][j + dy[3]]
								// 꽃잎과 꽃술 부분이 선택되었으면 continue
								) {
					continue;
				}
				isSelected[i][j] = true;
				for (int k = 0; k < 4; k++) {
					isSelected[i + dx[k]][j + dy[k]] = true;
				}
				// 꽃잎과 꽃술 부분이 선택
				comb(count + 1, i, isSelected);

				isSelected[i][j] = false;
				for (int k = 0; k < 4; k++) {
					isSelected[i + dx[k]][j + dy[k]] = false;
					// 꽃잎과 꽃술 부분이 선택 회수
				}
			}
		}
	}
}