package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 행복 유치원 / 골드 5 / 80분(솔루션 참고)
 * @author 민정인
 * https://www.acmicpc.net/problem/13164
 */

public class BOJ_13614 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		int[] arr = new int[n];
		int[] tmp = new int[n - 1];	// 바로 다음 값과의 차이 저장
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0; i < n - 1; i++) {
			tmp[i] = arr[i + 1] - arr[i];
		}
		Arrays.sort(tmp);
		int result = 0;
		for(int i = 0; i < n - k; i++) {	// k개 조를 만든다 -> 1개씩 있는 n개의 조에서 n - k번 합친다
			result += tmp[i];
		}
		System.out.println(result);
	}
}
