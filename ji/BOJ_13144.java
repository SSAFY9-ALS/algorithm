package net.acmicpc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * List of Unique Numbers / 골드 4 / 124분
 * @author 민정인
 * https://www.acmicpc.net/problem/13144
 */
public class BOJ_13144 {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		boolean[] chk = new boolean[100001];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int start = 0;
		int end = 0;
		long result = 0;
		while(end < n) {
			while(chk[arr[end]]) {
				result += end - start;
				chk[arr[start++]] = false;
			}
			chk[arr[end++]] = true;
		}
		result += (long) (end - start) * (long) (end - start + 1) / 2;
		System.out.println(result);
	}
}
