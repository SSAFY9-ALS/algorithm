/**
 *  2 x n 타일링 2 / 실버 3 / 20분
 */
package algorithm_with_java.dynamic_programming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BOJ_11727 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1001]; // num 까지의 가짓 수를 저장할 배열
		
		// 점화식 : dp[n] = dp[n-1] + (dp[n-2] * 2)
		// i번째 타일을 완성하는 방법에는
		// i-1번째 타일을 채우고 2 x 1 타일을 채우는 경우
		// i-2번째 타일을 채우고  2 x 2, 1 x 2(2개)를 채우는 경우
		dp[1] = 1;
		dp[2] = 3; // 기본 값 초기화
		for(int i=3;i<=num;i++) {
			dp[i] = (dp[i-1] + (dp[i-2] * 2)) % 10007;
		}
		
		System.out.println(dp[num]);
	}

}
