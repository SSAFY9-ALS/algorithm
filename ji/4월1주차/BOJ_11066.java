package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 파일 합치기 / 골드 3 / 117분(솔루션 참고)
 * @author 민정인
 * https://www.acmicpc.net/problem/11066
 */

public class BOJ_11066 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t = 1; t <= test; t++) {
			int n = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] arr = new int[n+1];	// 입력받은 파일들의 용량
			int[] sum = new int[n+1];	// 파일 위치별 누적합
			for(int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + arr[i];
			}
			int[][] dp = new int[n+1][n+1];	// 파일들 합친 용량의 최소값을 저장하는 dp
			/**
			 * dp[i][j] -> i부터 j까지 최소값
			 * -> dp[i][i] + dp[i+1][j] ~ dp[i][j-1] + dp[j][j] 까지 각각의 합 중 최소값
			 * 		+ i부터 j까지 각 원소의 합
			 */
			for(int i = 1; i <= n; i++) {	// 합칠 파일의 수
				for(int j = 1; i + j <= n; j++) {	// 시작 인덱스
					int k = i + j;			// 범위 인덱스
					dp[j][k] = Integer.MAX_VALUE;
					for(int l = j; l < k; l++) {	// dp[j][j] + dp[j+1][k] ~ dp[j][k-1] + dp[k][k]까지 최소값 확인
						dp[j][k] = Math.min(dp[j][k], dp[j][l] + dp[l+1][k] + sum[k] - sum[j-1]);
					}
				}
			}
//			for(int i = 1; i <= n; i++) {
//				for(int j = 1; j <= n; j++) {
//					System.out.print(dp[i][j] + " ");
//				}
//				System.out.println();
//			}
			System.out.println(dp[1][n]);	// 최종적으로 1번 파일부터 n번 파일까지의 합 중 최소값 출력
		}
	}
}
