import java.io.*;
import java.util.*;
/**
 * 
 * 퇴사 / 실버3 / 60분
 * https://www.acmicpc.net/problem/14501
 * 
 */
public class Main {
	static int n;
	static int[] time;
	static int[] cost;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		time = new int[n];
		cost = new int[n];
		StringTokenizer st = null;
		for(int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			time[i] = Integer.parseInt(st.nextToken());
			cost[i] = Integer.parseInt(st.nextToken());
		}
		int[] dp = new int[n + 1];
		// 종료가 된 날의 비용을 구하기 위해 크기를 n+1로 생성
		for(int i = 0; i < n; i++) {
			if(i + time[i] <= n) {
				// 상담을 종료할 때 기간 내에 있으면 실행
				dp[i + time[i]] = Math.max(dp[i + time[i]], dp[i] + cost[i]);
				// 현재 날짜에서 상담을 진행했을 때 이후의 시간의 비용 = 현재 날짜까지의 비용 + 현재 날짜에서 진행했던 상담 비용
			}
			dp[i+1] = Math.max(dp[i], dp[i+1]);
			// 다음 비용이 0일수도 있으므로 이전의 비용으로 대체
		}
		System.out.println(dp[n]);
	}
}