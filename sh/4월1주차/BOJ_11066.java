import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 파일합치기 / 골드3 / 2시간 + a
 * https://www.acmicpc.net/problem/11066
 */
public class BJ_11066_파일합치기 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		// 테스트 케이스 : T
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 0; t < T; t++) {
			// 소설을 구성하는 장의 수 : K
			int K = Integer.parseInt(br.readLine());
			
			// 소설
			int[] novel = new int[K+1];
			int[] sum = new int[K+1];
			int[][] dp = new int[K+1][K+1];
			
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= K; i++) {
				novel[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + novel[i];
			}
			
			for (int n = 1; n <= K; n++) {
				for (int from = 1; from + n <= K; from++) {
					int to = from + n;
					dp[from][to] = Integer.MAX_VALUE;
					for (int divide = from; divide < to; divide++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to] + sum[to] - sum[from-1]);
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
	}
}
