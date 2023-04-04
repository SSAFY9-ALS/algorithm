import java.io.*;
import java.util.*;

/**
 * 
 * 동전 2 / 골드 5 / 40분
 * https://www.acmicpc.net/problem/2294
 * 
 */

public class Main {
	static int n;
	static int k;
	static int[] money;
	static int[] dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		money = new int[n];
		dp = new int[k + 1];
		for(int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}
		Arrays.fill(dp, Integer.MAX_VALUE-1);
		dp[0] = 0;
		for(int index = 0; index < n; index++) {
			for(int i = money[index]; i <= k; i++) {
					dp[i] = Math.min(dp[i-money[index]] + 1, dp[i]);
					// 현재 가치에서 현재 동전 가치를 뺀 가치를 만들 수 있는 동전의 개수 + 1과 현재 가치를 만들수 있는 개수를 비교하여 최솟값을 배열에 넣는다.
			}
		}
		if(dp[k] == Integer.MAX_VALUE-1) {
			System.out.println(-1);
		} else {
			System.out.println(dp[k]);
		}
	}

}
