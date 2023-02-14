package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 랜선 자르기 / 실버 2 / 90분
 * @author 민정인
 * https://www.acmicpc.net/problem/1654
 */
public class BOJ_1654 {
	static int n;
	static int k;
	static long [] arr;
	static long val;
	static long max = Integer.MIN_VALUE;
	static void findVal() {
		long mid = 0;
		long min = 1;
		while(min <= max) {		// 이분탐색 진행
			long result = 0;
			mid = (max + min) / 2;
			for(int i = k - 1; i >= 0; i--) {
				result += arr[i] / mid;
			}
			if(result < n) {
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		val = (min + max) / 2;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		arr = new long[k];
		for(int i = 0; i < k; i++) {
			arr[i] = Long.parseLong(br.readLine());
			max = Math.max(max, arr[i]);
		}
		findVal();
		System.out.println(val);
	}
}
