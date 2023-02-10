/**
 * 연속합 / 실버 2 / 60분
 */
package com.ssafy.a_basic.array;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import static java.lang.Integer.parseInt;


public class BOJ_1912 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int num = parseInt(br.readLine()); // 정수 개수 입력 받음
		
		int[] arr = new int[num];
		int[] dp = new int[num+1];
		int max = Integer.MIN_VALUE; // 최대값
		
		String[] tmp = br.readLine().split(" ");
		for(int i=0;i<num;i++) {
			arr[i]=parseInt(tmp[i]); // n개의 정수로 이루어진 임의의 수열 주어진 값으로 초기화
		}
		
		dp[0]=arr[0]; // 처음 필요한 값 초기화
		max = dp[0]; // 최대값 초기화
		for(int i=1;i<num;i++) {
			dp[i] = Math.max(arr[i], dp[i-1]+arr[i]);
			// dp[i] 는 i번째 포함하는 연속합중 가장 큰 값
			// 현재 값과 그 전 인덱스를포함하느 연속합중 더 큰값을 넣음
			max = Math.max(max, dp[i]); // 최대값 업데이트
		}
			
			
		System.out.println(max);
	}


}
