package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 2xn 타일링 / 실버3 / 25분
 * https://www.acmicpc.net/problem/11726
 */

public class BOJ_11726 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		if(n < 3) // 3보다 작은 수가 입력됐을 때
			System.out.println(n); // 출력 후 바로 종료
		else {
			int[] dp = new int[n+1]; // n+1 크기의 배열 생성
			dp[1] = 1;
			dp[2] = 2;
			for(int i = 3; i <= n; i++) {
				// 2xn 블록을 채우는 방법은 (n-1)까지 채운 상태에서 2x1 블록을 세로로 채우는 것과 (n-2)까지 채운 상태에서 2x1 블록 2개를 가로로 겹쳐서 쌓는 방법이 있음
				dp[i] = (dp[i-2] + dp[i-1]) % 10007;
			}
			System.out.println(dp[n]);
		}
	}
}