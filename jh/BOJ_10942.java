package algorithm_mar;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 팰린드롬? / 골드4 / 2시간
 * https://www.acmicpc.net/problem/10942
 */

public class BOJ_10942 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder result = new StringBuilder();
        int n = Integer.parseInt(in.readLine()); // n 입력
        int[] nums = new int[n+1]; // 수열을 저장할 배열
        StringTokenizer st = new StringTokenizer(in.readLine());
        for(int i = 1; i <= n; i++)
            nums[i] = Integer.parseInt(st.nextToken()); // 수열 입력
        
        boolean[][] dp = new boolean[n+1][n+1]; // dp[i][j] => i부터 j까지의 팰린드롬 여부를 담는 배열
        for(int i = 1; i <= n; i++)
        	dp[i][i] = true; // i부터 i까지는 무조건 팰린드롬 -> true로 변경
        for(int i = 1; i < n; i++) {
        	if(nums[i] == nums[i+1])
        		dp[i][i+1] = true; // i부터 i+1까지는 두 값이 같으면 팰린드롬 -> true로 변경
        }
        // 점화식: dp[i][j]가 팰린드롬이려면? => dp[i+1][j-1]이 팰린드롬이면서 i == j
        for(int i = n-2; i >= 1; i--) { // i가 있을 때 i+1을 체크하기 때문에 뒤에서부터 탐색
        	for(int j = i + 2; j <= n; j++) { // j는 i+2 위치부터 n까지 가능
        		if(dp[i+1][j-1] && nums[i] == nums[j]) // 점화식 조건을 만족하면
        			dp[i][j] = true; // true로 변경
        	}
        }
        
        int m = Integer.parseInt(in.readLine()); // m 입력
        int start, end;
        for(int i = 0; i < m; i++) {
        	st = new StringTokenizer(in.readLine());
        	start = Integer.parseInt(st.nextToken()); // start 입력
        	end = Integer.parseInt(st.nextToken()); // end 입력
        	// 결과값 출력을 위한 처리
        	if(dp[start][end])
        		result.append(1 + "\n");
        	else
        		result.append(0 + "\n");
        }
        System.out.println(result); // 결과 출력
    }
}