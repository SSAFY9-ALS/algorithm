package algorithm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * 문자열 제거 / 골드4 / 1시간 30분
 * https://www.acmicpc.net/problem/21941
 */

public class BOJ_21941 {
	
	public static void main(String[] args) throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String sen = in.readLine();
		int m = Integer.parseInt(in.readLine());
		
		String check;
		int value;
		HashMap<String,Integer> score = new HashMap<>();
		for (int i = 0; i < m; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			check = st.nextToken();
			value = Integer.parseInt(st.nextToken());
			if(!score.containsKey(check) || score.get(check) < value) {
				score.put(check, value);
			}
		}
		
		String sub;
		int[] dp = new int[sen.length()+1]; // dp[i] -> i번째 문자열까지 가질 수 있는 최대 점수
		for(int i = 1; i <= sen.length(); i++) {
			for(int j = 1; j <= i; j++) {
				sub = sen.substring(j-1, i);
				if(score.containsKey(sub)) // 만약 부분 문자열이 지울 수 있는 문자열이라면
					dp[i] = Math.max(dp[i], dp[j-1] + score.get(sub)); // 점화식을 이용해 값 갱신
				else // 아니라면
					dp[i] = Math.max(dp[i], dp[i-1] + 1); // 기존의 값과 바로 직전 위치에서 현재 위치 문자열을 삭제했을 때 얻게 되는 값 비교
			}
		}
		
		System.out.println(dp[sen.length()]);
	}
}
