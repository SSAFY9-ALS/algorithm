package algorithm;

/* 
 * 1, 2, 3 더하기 / 실버3 / 70분
 * https://www.acmicpc.net/problem/9095
 */
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] dp = new int[11];
		dp[1] = 1;
		dp[2] = 2;
		dp[3] = 4;
		// 1, 2, 3은 명시적 초기화
		for (int i = 4; i < 11; i++) {
			dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
		}
		// 4를 보면
		// 1을 1, 2, 3의 합으로 나타낸 방법에 3을 더하면 되고
		// 2를 1, 2, 3의 합으로 나타낸 방법에 2를 더하면 되고
		// 3을 1, 2, 3의 합으로 나타낸 방법에 1을 더하면 되어
		// dp[4] = dp[1] + dp[2] + dp[3] 이 된다.

		// 5를 보면
		// 2를 1, 2, 3의 합으로 나타낸 방법에 3을 더하면 되고
		// 3을 1, 2, 3의 합으로 나타낸 방법에 2를 더하면 되고
		// 4를 1, 2, 3의 합으로 나타낸 방법에 1을 더하면 되어
		// dp[5] = dp[2] + dp[3] + dp[4] 이 된다.
		
		// (n은 양수이며 11보다 작다.)
		// dp[i] = dp[i - 3] + dp[i - 2] + dp[i - 1];
		for (int i = 0; i < n; i++) {
			int index = sc.nextInt();
			System.out.println(dp[index]);
			// 입력받은 값과 같은 dp배열의 인덱스의 값을 출력한다.
		}
	}
}
