package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 로마 숫자 만들기 / 실버 3 / 90분
 * https://www.acmicpc.net/problem/16922
 */
public class BOJ_16992 {
	static int[] arr = {1, 5, 10, 50};					// 로마자 숫자들의 조합에서 나온 값을 얻어내기 위한 배
	static boolean[] outVal = new boolean[1001];		// 최종적으로 도달한 값의 위
	static int ans = 0;
	static void sum(int n, int val, int idx) {
		if(n == 0) {
			if(outVal[val] == false) {
				outVal[val] = true;
				ans++;
			}
			return;
		}
		for(int i = idx; i < 4; i++) {					// 앞선 조합에서 만들어진 조합은 다시 계산할 필요가 없으므로 현재
			sum(n-1, val + arr[i], i);					// 현재 idx부터 시작하도록 설정
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		sum(n, 0, 0);
		System.out.println(ans);
	}
}
