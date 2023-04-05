import java.io.*;
import java.util.*;
/**
 * 
 * 동전 1 / 골드 5 / 50분
 * https://www.acmicpc.net/problem/2293
 * 
 */
public class Main {
	static int[][] apartment;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] coin = new int[n];
		// 각 코인의 가치를 저장하는 배열
		int[] dp = new int[k+1];
		// 각 가치를 구성할 수 있는 개수를 저장하는 배열
		for(int i = 0; i < n; i++) {
			coin[i] = Integer.parseInt(br.readLine());
		}
		dp[0] = 1;
		for(int i = 0; i < n; i++) {
			// n개의 동전만큼 반복
			for(int j = 1; j <= k; j++) {
				// 가치 1부터 k까지 반복
				if(j >= coin[i]) {
					dp[j] = dp[j] + dp[j-coin[i]];
				}
			}
			// 코인의 가치가 현재가치(j)보다 작으면
			// 기존(j)가치를 구성할 수 있는 개수(dp[j])에다가
			// 현재 가치(j)에 해당 코인의 가치(coin[i])를 제외한 가치를 구성하는 개수(dp[j-coin[i]])를 더하면
			// 해당 코인(i번째 코인)을 사용하여 현재 가치(j)를 구성하는 개수를 알 수 있다.
		}
		System.out.println(dp[k]);
	}
}
