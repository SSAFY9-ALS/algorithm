package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 수영장 / 모의 SW 역량테스트 / 2시간 30분
 * https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AV5PpFQaAQMDFAUq&&
 */

public class SWEA_1952 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int t = Integer.parseInt(in.readLine()); // 테스트 케이스 개수 입력
		int day, month, quarter, year;
		int[] plan, dp;
		for(int tc = 1; tc <= t; tc++) {
			st = new StringTokenizer(in.readLine());
			day = Integer.parseInt(st.nextToken()); // 1일 이용권 입력
			month = Integer.parseInt(st.nextToken()); // 1달 이용권 입력
			quarter = Integer.parseInt(st.nextToken()); // 3달 이용권 입력
			year = Integer.parseInt(st.nextToken()); // 1년 이용권 입력
			plan = new int[12]; // 이용 계획 담을 배열
			dp = new int[12]; // 1~n월까지의 최소 이용 요금을 담을 배열
			st = new StringTokenizer(in.readLine());
			for(int i = 0; i < 12; i++) {
				plan[i] = Integer.parseInt(st.nextToken()); // 월별 이용 계획 입력
			}
			
			// dp 이용 -> 점화식: dp[i] = min(dp[i-1] + 1일 요금 * 이용 계획, dp[i-1] + 1달 요금, dp[i-3] + 3달 요금)
			// dp[12월] = min(dp[12월], 1년 요금)
			dp[0] = (plan[0] * day > month)? month: plan[0] * day; // 1월 최소 이용 요금을 먼저 구함 -> dp를 위해		
			for(int i = 1; i < 12; i++) {
				dp[i] = Math.min(dp[i-1] + plan[i] * day, dp[i-1] + month);
				if(i < 3)
					dp[i] = Math.min(dp[i], quarter);
				else
					dp[i] = Math.min(dp[i], dp[i-3] + quarter);
			}
			dp[11] = Math.min(dp[11], year);
			System.out.printf("#%d %d\n", tc, dp[11]); // 결과 출력
		}
	}
}
