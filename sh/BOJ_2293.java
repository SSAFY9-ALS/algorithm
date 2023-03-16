import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 동전1 / 골드 5 / 1시간
 * https://www.acmicpc.net/problem/2293
 */
public class BJ_2293_동전1 {
	static int[] coins;
	static int n, k;
	static int[] dp; 
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		// 종류 : n, 가치의 합 : k
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		dp = new int[k+1];
		
		dp[0] = 1;
		// 동전의 종류
		coins = new int[n];
		for (int i = 0; i < n; i++) {
			coins[i] = Integer.parseInt(br.readLine());
		}
		
		for (int i = 0; i < n; i++) {
			for (int j = 1; j <= k ; j++) {
				if(j >= coins[i]) { 
					dp[j] = dp[j] + dp[j-coins[i]];
				}
			}
		}
		
		System.out.println(dp[k]);
	}
}
