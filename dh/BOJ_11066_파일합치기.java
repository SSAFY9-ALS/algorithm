import java.util.*;
import java.io.*;

/**
 * 
 * 파일 합치기 / 골드 3 / 120+a분
 * https://www.acmicpc.net/problem/11066
 * 
 */
public class Main {
	static int k;
	static int[] arr;
	static int[] sum;
	static int[][] dp;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int testcase = 1; testcase <= T; testcase++) {
			k = Integer.parseInt(br.readLine());
			arr = new int[k];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			sum = new int[k];
			sum[0] = arr[0];
			for (int i = 1; i < k; i++) {
				sum[i] = sum[i - 1] + arr[i];
			}
			int[][] dp = new int[k][k];

			for (int i = 0; i < k - 1; i++) {
				dp[i][i + 1] = arr[i] + arr[i + 1];
				// 각 두장의 합 따로 구하기
			}

			for (int j = 2; j < k; j++) { // 합칠 파일 수
				for (int i = 0; i + j < k; i++) {
					for (int l = i; l < i + j; l++) {
						if (dp[i][i + j] == 0) {
							dp[i][i + j] = dp[i][l] + dp[l + 1][i + j] + sub(sum, i, i + j);
						} else {
							dp[i][i + j] = Math.min(dp[i][i + j], dp[i][l] + dp[l + 1][i + j] + sub(sum, i, i + j));
						} // 점화식 dp[i][j] = dp[i][l] + dp[l+1][j] + sum[i+j] - sum[i-1]
					}
				}
			}
			System.out.println(dp[0][k - 1]);
		}

	}

	private static int sub(int[] sum, int start, int end) {
		if (start == 0) {
			return sum[end];
		}

		return sum[end] - sum[start - 1];
	}
}
