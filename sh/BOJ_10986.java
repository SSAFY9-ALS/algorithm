package com.tngus2sh.baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 나머지 합 / 골드 3 / 1시간
 * https://www.acmicpc.net/problem/10986
 *
 */

public class q10986 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		long[] sum = new long[N];
		long[] div = new long[M];
		long result = 0;
		
		st = new StringTokenizer(br.readLine());
		sum[0] = Integer.parseInt(st.nextToken());
		for(int i = 1; i < N; i++) {
			sum[i] = sum[i-1] + Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < N; i++) {
			int remainder = (int)(sum[i] % M);
			
			// 구간 합 구할 때 (s(i) - s(i-1)) % M == 0 인 경우는
			// 각각 M으로 나누었을 때 나머지에서 뺀 것과 같다.
			// 합 배열에서 M으로 나누었을 때 나머지가 같으면 0부터 해당 곳까지 더했을 때
			// M으로 떨어진다는 얘기와 같으므로 결과에 더해준다.
			if(remainder == 0) {
				result++;
			}
			// 나머지 개수 더해주기
			div[remainder]++;
		}
		
		for(int i = 0; i < M; i++) {
			long cnt = div[i];
			if(cnt > 1) {
				result += ((cnt * (cnt - 1))/ 2);
			}
		}
		
		System.out.println(result);
	}
}
