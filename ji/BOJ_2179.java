package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * 비슷한 단어 / 골드 4 / 33분
 * @author 민정인
 * https://www.acmicpc.net/problem/2179
 */
public class BOJ_2179 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		String[] arr = new String[n];
		for(int i = 0; i < n; i++) {
			arr[i] = br.readLine();
		}
		int maxVal = Integer.MIN_VALUE;	// 최대값 저장
		int resultx = 0;				// 최대값의 좌표
		int resulty = 0;
		for(int i = 0; i < n; i++) {	// 전체 순회하면서 최대값 확인
			for(int j = i + 1; j < n; j++) {
				int val = 0;
				for(int k = 0; k < Math.min(arr[i].length(), arr[j].length()); k++) {
					if(arr[i].charAt(k) != arr[j].charAt(k)) {
						break;
					}
					val++;
				}
				if(maxVal < val) {
					maxVal = val;
					resultx = i;
					resulty = j;
				}
			}
		}
		System.out.println(arr[resultx]);
		System.out.println(arr[resulty]);
	}
}
