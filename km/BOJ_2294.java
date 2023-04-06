/**
 * 동전 2 / 골드 5 / 80분
 * https://www.acmicpc.net/problem/2294
 */
package baekjoon;

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;

public class BOJ_2294 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] tmp = br.readLine().split(" ");
		
		int n = parseInt(tmp[0]);
		int k = parseInt(tmp[1]);
		
	
		int[] dp = new int[k+1];
		int[] coin = new int[n];
		
		for(int i=0;i<n;i++) {
			coin[i] = parseInt(br.readLine());
		}
		
		Arrays.fill(dp, 100001); // 가장 큰 값이 1원 짜리고 100000을 만드는 경우이므로 +1해서 초기화
		
		dp[0]=0;
		
		for(int i=0;i<n;i++) {
			for(int j=coin[i];j<k+1;j++) {
				// 점화식
				dp[j] = Math.min(dp[j], dp[j-coin[i]]+1);
			}
		}
		
		if(dp[k]==100001) {
			System.out.println(-1);
		}
		else {
			System.out.println(dp[k]);
		}
	}
}
