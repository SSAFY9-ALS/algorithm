package swA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 퇴사 / 실버3 / 30분 / 3월 2일
 * https://www.acmicpc.net/problem/14501
 */
public class BJ_14501_퇴사_이예리 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		int[] time = new int[N];
		int[] price = new int[N];
		int[] dp = new int[N + 1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			price[i] = Integer.parseInt(st.nextToken());
		}

//		for (int i = 1; i < N + 1; i++) {
//			if (time[i] <= N - i) {
//
//			}
//		}

		int max = 0;
//		if(time[N-1]==1)
//			dp[N]=price[N-1];
		for (int i = N - 1; i >= 0; i--) {
			// 마감일
			int finish = time[i]+i;
			if (finish <= N ) {
				max = Math.max(max, dp[finish]+price[i]);		//기존 최댓값 : 지금 하는 일 + 마감일부터 할수 있는 일 
//				dp[i] = Math.max(a, b);
			}
			
			dp[i] = max;
		}
		System.out.println(dp[0]);
	}
}
