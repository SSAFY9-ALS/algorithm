import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 팰린드롬 / 골드4 / 40분
 * https://www.acmicpc.net/problem/10942
 */
public class BJ_10924_팰린드롬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		// 수열의 크기 : N
		int N = Integer.parseInt(br.readLine());
		
		// 홍준이가 칠판에 적은 수
		int[] num = new int[N+1];
		// dp
		int[][] dp = new int[N+1][N+1];
		st = new StringTokenizer(br.readLine());
		for (int n = 1; n <= N; n++) {
			num[n] = Integer.parseInt(st.nextToken());
			dp[n][n] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 1; j < N; j++) {
				if(num[j] == num[j+1]) dp[j][j+1] = 1;
				if(i+j > N) break;
				if(num[j] == num[i+j]) dp[j][i+j] = dp[j+1][i+j-1];
				else dp[j][i+j] = 0;
			}
		}
		
		// 홍준이가 한 질문의 개수 : M
		int M = Integer.parseInt(br.readLine());
		
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			
			sb.append(dp[s][e]).append('\n');
		}
		
		System.out.println(sb);
	}
}
