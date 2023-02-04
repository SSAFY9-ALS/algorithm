package ssafy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 피보나치 함수 / 실버3 / 20분
 * https://www.acmicpc.net/problem/1003
 */

public class BOJ_1003 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(br.readLine());
		int n;
		Integer[] numA, numB;
		ArrayList<Integer[]> dp = new ArrayList<Integer[]>();
		dp.add(new Integer[] {1, 0}); // 0, 1 값을 미리 넣어줌
		dp.add(new Integer[] {0, 1});
		for(int tc = 0; tc < t; tc++) {
			n = Integer.parseInt(br.readLine());
			for(int i = dp.size(); i <= n; i++) { // n의 결과가 아직 dp에 저장되지 않았을 때 값을 채워주기 위함
				numA = dp.get(i-2);
				numB = dp.get(i-1);
				// dp[n] = dp[n-2] + dp[n-1] 공식 사용 (피보나치 공식이랑 같음)
				dp.add(new Integer[] {numA[0] + numB[0], numA[1] + numB[1]});
			}
			sb.append(String.format("%d %d\n", dp.get(n)[0], dp.get(n)[1]));
		}
		System.out.println(sb);
	}
}
