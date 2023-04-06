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
 * 벌꿀채취 / _ / 200분
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5V4A46AdIDFAWu
 *
 */

public class Honey {
	static int n;
	static int m;
	static int c;
	static int[][] honey;
	static int[] money; // money[2]
	static int[][] worker; // 각 사람이 선택한 배열을 저장하는 2차원 배열 [2][m]
	static boolean[] selected;
	static int result; // max

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			// 벌통의 크기
			m = Integer.parseInt(st.nextToken());
			// 벌통의 개수
			c = Integer.parseInt(st.nextToken());
			// 꿀의 최대 양
			honey = new int[n][n];
			boolean[][] visited = new boolean[n][n];
			// 2차원 배열에서 방문여부 저장 배열
			selected = new boolean[m];
			// 벌통에서 최댓값 구할 때 방문 여부 저장 배열
			worker = new int[2][m];
			// 각 사람이 선택한 벌통
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					honey[i][j] = Integer.parseInt(st.nextToken());
					// 허니 채우기
				}
			}

			result = 0;
			// 테스트케이스 마다 result 초기화
			comb(0, 0, visited);
			// 0번째 선택하고 0에서 출발
			System.out.println("#" + test_case + " " + result);

		}
	}

	public static void comb(int count, int start, boolean[][] visited) {
		if (count == 2) {
			// 
			money = new int[2];
			for (int person = 0; person < 2; person++) {
				combHoney(person, 0, 0);
				// 한 사람 마다 꿀통 제곱의 최댓값
			}
			int total = money[0] + money[1];
			// 두 사람의 최댓값 더하기
			result = Math.max(total, result);
			// 가장 큰 값 구하기
			
			return;
		}
		// 조합
		for(int i = start; i < n; i++) {
			// 행 탐색
			out:for(int j = 0; j < n; j++) {
				// 열 탐색
				if(j+m-1 >= n) {
					// 가로에서 구역을 벗어 났을때 continue;
					continue;
				}
				
				for(int k = 0; k < m; k++) {
					if(visited[i][j+k]) {
						// 0 ~ m-1 까지의 벌통중에서 한 곳이라도 선택되었으면 다음 for문 실행
						continue out;
					}
				}
				
				for(int k = 0; k < m; k++) {
					visited[i][j+k] = true;
					// 방문 여부 체크
					worker[count][k] = honey[i][j+k];
					// 두 배열 모으기
				}
				
				comb(count + 1, start, visited);
				// 카운트만을 증가하고 start부분은 행 값이므로 for문에 의해 증가하여 start값 그대로 인자 설정
				
				for(int k = 0; k < m; k++) {
					visited[i][j+k] = false;
					// 방문 여부 회수
				}
			}
		}

	}

	public static void combHoney(int person, int sum, int pow_sum) {
		money[person] = Math.max(money[person], pow_sum);
		// 지금까지 제곱한 값을 더한 값과 한 사람이 제곱한 값 중 최댓값 저장

		for (int i = 0; i < m; i++) {
			if (selected[i] == true)
				// 선택 했으면 continue;
				continue;
			if (sum + worker[person][i] <= c) {
				// 지금까지 더한 값에 한 사람의 한 칸 값을 더한것이 c보다 작으면 선택
				selected[i] = true;
				combHoney(person, sum + worker[person][i], pow_sum + +worker[person][i]*worker[person][i]);
				// 몇번째 사람인지의 인자, 지금까지 더한 값, 지금까지 제곱하여 더한 값 인자로 설정
				selected[i] = false;
			}
		}

	}

}