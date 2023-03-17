package net.acmicpc;

import java.util.*;
import java.io.*;

/**
 * 팰린드롬 ? / 골드 4 / 154분
 * @author 민정인
 * https://www.acmicpc.net/problem/10942
 */

public class BOJ_10942 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		int[] chk1 = new int[n+1];		// 홀수의 중심좌표에 따른 범위
		int[] chk2 = new int[n+1];		// 짝수의 중심 중 왼쪽 좌표에 따른 범위
		Arrays.fill(chk1, -1);
		Arrays.fill(chk2, -1);
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 1; i <= n; i++) {	// 중심을 기준으로 범위를 늘려가며 팰린드롬이 깨지는 시점의 범위 저장
			for(int j = 1; j < Math.min(i, n - i + 1); j++) {
				if(arr[i - j] != arr[i + j]) {
					chk1[i] = j;
					break;
				}
			}
		}
		for(int i = 1; i < n; i++) {
			for(int j = 0; j < Math.min(i, n - i); j++) {
				if(arr[i - j] != arr[i + j + 1]) {
					chk2[i] = j;
					break;
				}
			}
		}
		int m = Integer.parseInt(br.readLine());
		for(int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			if(s == e) {
				sb.append(1 + "\n");
				continue;
			}
			int mid = (s + e) / 2;
			if((e - s + 1) % 2 != 0) {
				if(chk1[mid] == -1 || chk1[mid] > mid - s) {	// 깨진 팰린드롬의 범위보다 작거나 -1인 경우(모든 범위에서 팰린드롬)
					sb.append(1 + "\n");	// 1 저장
					continue;
				}
			} else {
				if(chk2[mid] == -1 || chk2[mid] > mid - s) {
					sb.append(1 + "\n");
					continue;
				}
			}
			sb.append(0 + "\n");			// 위의 경우에 모두 해당되지 않으면 0 저장
		}
		System.out.println(sb.toString());	// 저장된 결과 한번에 출력
	}
}
