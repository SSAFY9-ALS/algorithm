package com.ssafy.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 1로 만들기 / 실버1 / 20분
 * https://www.acmicpc.net/problem/1463
 */
public class BJ_1463_1로만들기 {

	static int N;
	static int[] dp;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		dp = new int [1000001];
		dp[1] = 0;
		dp[2] = 1;
		dp[3] = 1;
		System.out.println(find(N));
	}
	
	private static int find(int n) {
		if(n==1 || dp[n]!=0) {
			return dp[n];
		}else {
			int tmp = N;
			int tmp2 = N;
			int tmp3 = N;
			if(n%3==0) {
				tmp3=find(n/3);
			}
			if(n%2==0) {
				tmp2 = find(n/2);
			}
			
			dp[n] = Math.min(find(n-1), Math.min(tmp2, tmp3))+1;
			return dp[n];
		}
	}


}
