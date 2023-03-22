package net.acmicpc;

import java.io.*;
import java.util.*;

/**
 * 동전 2 / 골드 5 / 23분
 * @author 민정인
 * https://www.acmicpc.net/problem/2294
 */

public class BOJ_2294 {
	static int[] money;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		money = new int[n];
		for(int i = 0; i < n; i++) {
			money[i] = Integer.parseInt(br.readLine());
		}
		int[] rec = new int[10001];
		Arrays.fill(rec, Integer.MAX_VALUE - 1);
		rec[0] = 0;	// 입력한 동전 값일 경우 해당 위치는 1로 고정됨(+1이 들어가므로 0으로 초기화)
		for(int i = 0; i < n; i++) {	// 동전값이 작은 값부터 넣어보기
			for(int j = money[i]; j <= k; j++) {	// k원까지 순회하면서 각 위치에 들어가는 동전 최소개수 갱신(더하기 이전 값의 개수 + 1)
				rec[j] = Math.min(rec[j], rec[j-money[i]] + 1);
			}
		}
		System.out.println(rec[k] >= Integer.MAX_VALUE - 1 ? -1 : rec[k]);
	}
}
