package algorithm;

/* 
 * 카드 구매하기 / 실버1 / 40분
 * https://www.acmicpc.net/problem/11052
 */
import java.util.Scanner;
import java.util.Stack;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			arr[i] = sc.nextInt();
		}
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				dp[i] = Math.max(dp[i - j] + arr[j], dp[i]);
				// 카드개수 1 ~ n
				// --> 카드개수 i 일때
				// dp[i-1] + arr[1]
				// dp[i-2] + arr[2] ... (1부터 i까지)
			}
		}
		System.out.println(dp[n]);
	}
}


