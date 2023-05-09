import java.io.*;
import java.util.*;

/**
 * 
 * 문자열 제거 / 골드 4 / 100분
 * https://www.acmicpc.net/problem/21941
 * 
 */

public class Main {
	static String s;
	static int m;
	static Map<String, Integer> map;
	static int[] dp;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		s = br.readLine();
		m = Integer.parseInt(br.readLine());
		map = new HashMap<>();
		int len = s.length();
		dp = new int[len + 1];
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			String tempkey = st.nextToken();
			int tempvalue = Integer.parseInt(st.nextToken());
			if (tempkey.length() >= tempvalue)
				continue;
			map.put(tempkey, tempvalue);
		}

		for (int i = 1; i <= len; i++) {
			for (int j = 1; j <= i; j++) {
				String temp = s.substring(j - 1, i);
				// 모든 부분 문자열 비교
				if (map.containsKey(temp)) {
					dp[i] = Math.max(dp[i], dp[j - 1] + map.get(temp));
					// 현재 dp값과
					// temp문자열 길이 이전의 dp(점수)값과 일치하는 문자열의 점수를 더한 값을 비교
				} else {
					dp[i] = Math.max(dp[i], dp[i - 1] + 1);
					// 그 외 경우에는 1 더한값 저장
				}

			}
		}
		System.out.println(dp[len]);

	}

}
