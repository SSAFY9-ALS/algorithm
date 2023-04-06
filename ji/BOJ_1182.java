package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 부분수열의 합 / 실버 2 / 39분
 * @author 민정인
 * https://www.acmicpc.net/problem/1182
 */

public class BOJ_1182 {
	static int n;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		int s = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];		// 배열 정보
		int[] idx = new int[n];		// 부분수열의 원소 선택 정보
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int leftSum = 0;
		int result = 0;
		for(int i = 1; i <= n; i++) {
			Arrays.fill(idx, 0);				// 선택 정보를 위한 배열 초기화
			Arrays.fill(idx, n - i, n, 1);		// 선택하려는 원소 수 만큼 1로 채움
			do {								// np 호출
				leftSum = 0;
				for(int j = 0; j < n; j++) {
					if(idx[j] == 1) {			// 선택한 원소의 값을 더함
						leftSum  += arr[j];
					}
				}
				if(leftSum == s) {				// 해당 값의 합이 s면 결과 + 1
					result++;
				}
			} while(np(idx));
		}
		System.out.println(result);				// 결과 출력
	}
	
	static boolean np(int[] idx) {
		int i = n - 1;
		while(i > 0 && idx[i-1] >= idx[i]) --i;
		if(i == 0) return false;
		int j = n - 1;
		while(idx[i-1] >= idx[j]) --j;
		swap(idx, i-1, j);
		int k = n - 1;
		while(i < k) swap(idx, i++, k--);
		return true;
	}
	
	static void swap(int[] idx, int i, int j) {
		int tmp = idx[i];
		idx[i] = idx[j];
		idx[j] = tmp;
	}
}
